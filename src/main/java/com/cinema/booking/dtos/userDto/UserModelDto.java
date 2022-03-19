package com.cinema.booking.dtos.userDto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class UserModelDto {

    @NotBlank(message = "First Name is requierd field")
    private String firstName;
    @NotBlank(message = "Last Name requierd field")
    private String lastName;
            @NotBlank(message = "Email is requierd field")
    @Email(
            message ="1.Hyphen “-” and dot “.” isn't allowed at the start and end of the domain-part'\n'" +
                    "2.No consecutive dots,'\n'"+
                    "3.Singnature: @ is requierd before domain part",
            regexp = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$"
    )
    private String email;
    @NotBlank(message = "Password is requierd field")
    private String password;
   // private String matchingPassword;
}

