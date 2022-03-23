package com.cinema.booking.controllers;

import com.cinema.booking.model.LoginModel;
import com.cinema.booking.services.userService.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class SignInController {

    private final UserService userService;

    @PostMapping("/logging")
    public ResponseEntity<?> login(@Valid @RequestBody LoginModel loginModel){
        return userService.authenticateUser(loginModel);
    }

}
