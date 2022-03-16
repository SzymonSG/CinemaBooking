package com.cinema.booking.entities;

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


    @Column(unique = true)
    private String email;


    @Column(length = 60)
    private String password;


    private String role;
    private boolean enabled = false;
}
