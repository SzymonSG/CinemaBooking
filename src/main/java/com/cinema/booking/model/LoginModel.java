package com.cinema.booking.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;


@Getter
@Setter
@NoArgsConstructor
public class LoginModel {
    @Email(
            message ="1.Hyphen “-” and dot “.” isn't allowed at the start and end of the domain-part'\n'" +
                    "2.No consecutive dots,'\n'"+
                    "3.Singnature: @ is requierd before domain part",
            regexp = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$"
    )
    private String email;
    private String password;


}
