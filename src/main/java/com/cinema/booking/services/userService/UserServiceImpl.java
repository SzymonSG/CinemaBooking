package com.cinema.booking.services.userService;
import com.cinema.booking.entities.VerificationToken;
import com.cinema.booking.security.jwt.JwtUtility;
import com.cinema.booking.entities.PasswordResetToken;
import com.cinema.booking.entities.User;
import com.cinema.booking.model.LoginModel;
import com.cinema.booking.model.response.JwtModelResponse;
import com.cinema.booking.repository.PasswordResetTokenRepository;
import com.cinema.booking.repository.UserRepository;
import com.cinema.booking.repository.VerificationTokenRepository;
import com.cinema.booking.security.service.MyUserDetail;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final VerificationTokenRepository verificationTokenRepository;

    private final PasswordResetTokenRepository passwordResetTokenRepository;

    private final AuthenticationManager authenticationManager;

    private final JwtUtility jwtUtility;



    @Override
    public User registerUser(User userModel)  {
        User user = new User();
        user.setEmail(userModel.getEmail());
        user.setFirstName(userModel.getFirstName());
        user.setLastName(userModel.getLastName());
        user.setRole("USER");
        user.setPassword(passwordEncoder.encode(userModel.getPassword()));
        userRepository.save(user);
        return user;

    }

    @Override
    public ResponseEntity<?> authenticateUser(LoginModel loginModel) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginModel.getEmail(),
                        loginModel.getPassword()
                ));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtUtility.generateToken(authentication);

        MyUserDetail principal = (MyUserDetail) authentication.getPrincipal();

        List<String> roles = principal.getAuthorities().stream()
                .map(role -> role.getAuthority())
                .collect(Collectors.toList());

        JwtModelResponse jwtModelResponse = JwtModelResponse.builder()
                .id(principal.getId())
                .email(principal.getUsername())
                .firstName(principal.getFirstName())
                .roles(roles)
                .token(token)
                .build();
        return ResponseEntity.ok(jwtModelResponse);

    }


    @Override
    public void saveVerificationTokenForUser(String token, User user) {
        VerificationToken verificationToken =
                new VerificationToken(user,token);

        verificationTokenRepository.save(verificationToken);

    }

    @Override
    public String validateVerifactionToken(String token) {
        VerificationToken verificationToken =
                verificationTokenRepository.findByToken(token);
        if (verificationToken==null){
            return "invalid";
        }

        User user = verificationToken.getUser();
        Calendar cal = Calendar.getInstance();

        if ((verificationToken.getExpirationTime().getTime() -cal.getTime().getTime()) <=0){
            verificationTokenRepository.delete(verificationToken);
            return "expired";
        }
        user.setEnabled(true);
        userRepository.save(user);
        return "valid";
    }
    // when we got
    @Override
    public VerificationToken generateNewVerificationToken(String oldtoken) {
        VerificationToken verificationToken =
                verificationTokenRepository.findByToken(oldtoken);
        verificationToken.setToken(UUID.randomUUID().toString());
        verificationTokenRepository.save(verificationToken);
        return verificationToken;
    }
    @Override
    public String getTokenStringFromUIDD(VerificationToken verificationToken){
        String legitToken = verificationToken.getToken();
        return legitToken;
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void createPasswordResetTokenForUser(User user, String token) {
        PasswordResetToken passwordResetToken =
                new PasswordResetToken(user,token);

        passwordResetTokenRepository.save(passwordResetToken);

    }

    @Override
    public String validatePasswordResetToken(String token) {
        PasswordResetToken passwordResetToken =
                passwordResetTokenRepository.findByToken(token);

        if (passwordResetToken==null){
            return "invalid";
        }

        User user = passwordResetToken.getUser();
        Calendar calendar = Calendar.getInstance();

        if ((passwordResetToken.getExpirationTime().getTime()
                -calendar.getTime().getTime()) <=0){
            passwordResetTokenRepository.delete(passwordResetToken);
            return "expired";
        }
        return "valid";
    }

    @Override
    public Optional<User> getUserByPasswordResetToken(String token) {
        Optional<User> user = Optional.ofNullable(passwordResetTokenRepository.findByToken(token).getUser());
        return user;
    }

    @Override
    public void changePassword(User user, String newPassword) {
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }

    @Override
    public boolean checkValidOldPassword(User user, String oldPassword) {
        return passwordEncoder.matches(oldPassword,user.getPassword());
    }

}
