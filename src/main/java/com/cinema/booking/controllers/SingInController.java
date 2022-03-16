package com.cinema.booking.controllers;


import com.cinema.booking.model.LoginModel;
import com.cinema.booking.services.authService.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
public class SingInController {

    private final UserService userService;
    //authenticate z auth bo to co innego
    @PostMapping("/singup")
    public ResponseEntity<?> login(@Valid @RequestBody LoginModel loginModel){
        return userService.authUser(loginModel);
    }

}