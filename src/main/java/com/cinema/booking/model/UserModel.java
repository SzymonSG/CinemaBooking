package com.cinema.booking.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
//wsumie mozna usunąć
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserModel {

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
