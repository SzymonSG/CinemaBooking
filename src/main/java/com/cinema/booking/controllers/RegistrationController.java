package com.cinema.booking.controllers;

import com.cinema.booking.common.RegisterUtils;
import com.cinema.booking.entities.User;
import com.cinema.booking.entities.authUserEntitiesSupport.VerificationToken;
import com.cinema.booking.event.RegistrationCompleteEvent;
import com.cinema.booking.exceptions.ConstraintViolationException;
import com.cinema.booking.mappers.UserMapper;
import com.cinema.booking.dtos.userDto.UserModelDto;
import com.cinema.booking.model.PasswordModel;
import com.cinema.booking.services.userService.UserService;
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
@RequestMapping("/api")
public class RegistrationController {


    private final UserService userService;
    private final ApplicationEventPublisher publisher;
    private final UserMapper userMapper;
    private final RegisterUtils registerUtils;


    @PostMapping("/register")
    public String registerUser( @RequestBody UserModelDto userModelDto,
                                final HttpServletRequest request) throws ConstraintViolationException {
        User userModel = userMapper.dtoToUser(userModelDto);
        User user = userService.registerUser(userModel);
        // application event to send email to user after registraion to attach
        publisher.publishEvent(new RegistrationCompleteEvent(
                user,
                registerUtils.applicationUrl(request)
        ));

        return "Succes Registartion!";
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
        registerUtils.resendVerificationTokenEmail(user,registerUtils.applicationUrl(request), verificationToken);
        return "Verification Link Send";

    }


    @PostMapping("/resetPassword")
    public String resetPassword(@RequestBody PasswordModel passwordModel, HttpServletRequest request) {
        User user = userService.findUserByEmail(passwordModel.getEmail());
        String url = "";
        if (user != null) {
            String token = UUID.randomUUID().toString();
            userService.createPasswordResetTokenForUser(user, token);
            url = registerUtils.passwordResetTokenEmail(user, registerUtils.applicationUrl(request), token);
        }
        return url;
    }

    @PostMapping("/savePassword")
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



    @PostMapping("/changePassword")
    public String changePassword(@RequestBody PasswordModel passwordModel){
        User user = userService.findUserByEmail(passwordModel.getEmail());
        if (!userService.checkValidOldPassword(user,passwordModel.getOldPassword())){
            return "Invalid Old Password";
        }
        userService.changePassword(user,passwordModel.getNewPassword());
        //Save new password
        return "Password Change Successfully";
    }


}
