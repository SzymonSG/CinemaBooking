package com.cinema.booking.dtos.userDto;

import lombok.Data;

import javax.validation.constraints.Email;

@Data
public class UserModelDto {

    private String firstName;
    private String lastName;

    @Email(
            message ="1.Hyphen “-” and dot “.” isn't allowed at the start and end of the domain-part'\n'" +
                    "2.No consecutive dots,'\n'"+
                    "3.Singnature: @ is requierd before domain part",
            regexp = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$"
    )
    private String email;
    private String password;
    private String matchingPassword;
}

