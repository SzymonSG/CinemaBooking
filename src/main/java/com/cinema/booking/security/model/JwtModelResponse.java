package com.cinema.booking.security.model;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class JwtModelResponse {

    private String token="Bearer";
    private Long id;
    private String email;
    private String firstName;
    private List<String> roles;

    public JwtModelResponse(String token, Long id, String email, String firstName, List<String> roles) {
        this.token = token;
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.roles = roles;
    }
}
