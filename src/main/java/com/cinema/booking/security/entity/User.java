package com.cinema.booking.security.entity;

import lombok.Data;
import org.aspectj.lang.annotation.Before;


import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;



@Entity
@Data
@Table(name = "Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;

    @Email(
            message ="1.Hyphen “-” and dot “.” isn't allowed at the start and end of the domain-part'\n'" +
                     "2.No consecutive dots,'\n'"+
                     "3.Singnature: @ is requierd before domain part",
            regexp = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$"
    )
    @Column(unique = true)
    private String email;


    @Column(length = 60)
    private String password;


    private String role;
    private boolean enabled = false;
}
