package com.cinema.booking.security.controller;


import com.cinema.booking.security.model.LoginModel;
import com.cinema.booking.security.service.serviceInterfaces.UserService;
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

    @PostMapping("/singup")
    public ResponseEntity<?> login(@Valid @RequestBody LoginModel loginModel){
        return userService.authUser(loginModel);
    }

}
