package com.cinema.booking.services.userService;


import com.cinema.booking.entities.User;
import com.cinema.booking.entities.VerificationToken;
import com.cinema.booking.exceptions.ConstraintViolationException;
import com.cinema.booking.model.LoginModel;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface UserService {
    User registerUser(User userModel) throws ConstraintViolationException;

    void saveVerificationTokenForUser(String token, User user);

    String validateVerifactionToken(String token);

    VerificationToken generateNewVerificationToken(String oldtoken);

    User findUserByEmail(String email);

    void createPasswordResetTokenForUser(User user, String token);

    String validatePasswordResetToken(String token);

    Optional<User> getUserByPasswordResetToken(String token);

    void changePassword(User user, String newPassword);

    boolean checkValidOldPassword(User user, String oldPassword);

    ResponseEntity<?> authenticateUser(LoginModel loginModel);

    String getTokenStringFromUIDD(VerificationToken verificationToken);

}
