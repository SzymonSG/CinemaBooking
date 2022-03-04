package com.cinema.booking.security.service.serviceInterfaces;


import com.cinema.booking.security.entity.User;
import com.cinema.booking.security.entity.VerificationToken;
import com.cinema.booking.security.model.LoginModel;
import com.cinema.booking.security.model.UserModel;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface UserService {
    User registerUser(UserModel userModel);

    void saveVerificationTokenForUser(String token, User user);

    String validateVerifactionToken(String token);

    VerificationToken generateNewVerificationToken(String oldtoken);

    User findUserByEmail(String email);

    void createPasswordResetTokenForUser(User user, String token);

    String validatePasswordResetToken(String token);

    Optional<User> getUserByPasswordResetToken(String token);

    void changePassword(User user, String newPassword);

    boolean checkIfValidOldPassword(User user, String oldPassword);

    ResponseEntity<?> authUser(LoginModel loginModel) throws RuntimeException;
}
