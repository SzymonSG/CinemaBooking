package com.cinema.booking.errors;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;



@Data
@NoArgsConstructor
public class ErrorMessage {

    private HttpStatus status;
    private String message;
    //List<String> listErrors;

    public ErrorMessage(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}
