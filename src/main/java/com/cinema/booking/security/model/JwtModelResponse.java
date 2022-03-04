package com.cinema.booking.security.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class JwtModelResponse {
    private Long id;
    private String token;
    private String email;
    private String firstName;
    private List<String> roles;

    public JwtModelResponse(Long id, String token, String email, String firstName, List<String> roles) {
        this.id = id;
        this.token = token;
        this.email = email;
        this.firstName = firstName;
        this.roles = roles;
    }
}
