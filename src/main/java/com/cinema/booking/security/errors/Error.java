package com.cinema.booking.security.errors;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;



@Data
@NoArgsConstructor
public class Error {

    private HttpStatus status;
    private String message;
    public Error(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}

