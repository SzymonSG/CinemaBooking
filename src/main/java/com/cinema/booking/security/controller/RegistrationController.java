package com.cinema.booking.security.controller;

import com.cinema.booking.security.entity.User;
import com.cinema.booking.security.entity.VerificationToken;
import com.cinema.booking.security.event.RegistrationCompleteEvent;
import com.cinema.booking.security.model.PasswordModel;
import com.cinema.booking.security.model.UserModel;
import com.cinema.booking.security.service.serviceInterfaces.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@RestController
public class RegistrationController {


    private final UserService userService;

    private final ApplicationEventPublisher publisher;

    @PostMapping("/register")
    public String registerUser(@RequestBody UserModel userModel, final HttpServletRequest request) {
        User user = userService.registerUser(userModel);
        // application event to send email to user after registraion to attach
        publisher.publishEvent(new RegistrationCompleteEvent(
                user,
                applicationUrl(request)
        ));//this publis the event be we must also create listner for that publish event

        return "Succes Registartion!";
    }

    private String applicationUrl(HttpServletRequest request) {
        return "http://" +
                request.getServerName() +
                ":" +
                request.getServerPort() +
                request.getContextPath();
    }

    @GetMapping("/verifyRegistration")
    public String verifyRegistration(@RequestParam("token") String token) {
        String result = userService.validateVerifactionToken(token);
        if (result.equalsIgnoreCase("valid")) {
            return "User Verifies Successfully";
        }
        return "Verifaction failed";

    }

    @GetMapping("/resendVerifyToken")
    public String resendVerificationToken(@RequestParam("token") String oldtoken,
                                          HttpServletRequest request) {
        VerificationToken verificationToken =
                userService.generateNewVerificationToken(oldtoken);

        User user = verificationToken.getUser();
        resendVerificationTokenEmail(user, applicationUrl(request), verificationToken);
        return "Verification Link Send";

    }

    private void resendVerificationTokenEmail(User user, String applicationUrl, VerificationToken verificationToken) {

        String url = applicationUrl +
                "/verifyRegistration?token="
                + verificationToken.getToken();

        log.info("Click link to verify account: {}", url);

    }

    @PostMapping("/resetPassword")
    public String resetPassword(@RequestBody PasswordModel passwordModel, HttpServletRequest request) {
        User user = userService.findUserByEmail(passwordModel.getEmail());
        String url = "";
        if (user != null) {
            String token = UUID.randomUUID().toString();
            userService.createPasswordResetTokenForUser(user, token);
            url = passwordResetTokenEmail(user, applicationUrl(request), token);
        }
        return url;
    }

    @PostMapping("savePassword")
    public String savePassword(@RequestParam("token")String token,
                               @RequestBody PasswordModel passwordModel){
        String result = userService.validatePasswordResetToken(token);
        if (!result.equalsIgnoreCase("valid")){
            return "Invalid Token";
        }
        Optional<User> user= userService.getUserByPasswordResetToken(token);
        if (user.isPresent()){
            userService.changePassword(user.get(),passwordModel.getNewPassword());
            return "Password Reset Succesfully";
        }else {
            return "Invalid Token";
        }
    }

    private String passwordResetTokenEmail(User user, String applicationUrl, String token) {
        String url =
                applicationUrl +
                        "/savePassword?token="
                        + token;

        log.info("Click link to Reset password: {}", url);
        return url;
    }

    @PostMapping("changePassword")
    public String changePassword(@RequestBody PasswordModel passwordModel){
        User user = userService.findUserByEmail(passwordModel.getEmail());
        if (!userService.checkIfValidOldPassword(user,passwordModel.getOldPassword())){
            return "Invalid Old Password";
        }
        userService.changePassword(user,passwordModel.getNewPassword());
        //Save new password
        return "Password Change Successfully";
    }

    @GetMapping("/login")
    public String checkJwt(){
        return "BONDZIORNIANIO!!!!!";
    }

}
