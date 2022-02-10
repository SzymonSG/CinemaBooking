package com.cinema.booking.sacurity.securityEntites;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;



@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name = "User_Application")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    //chyba bez ?
    //private String username;
    private String email;
    private String password;
    private boolean active;

    @ManyToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    @JoinTable(
            name = "UserWithRoles",
            joinColumns = @JoinColumn(
                    name = "user_ID",
                    referencedColumnName = "userId"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "role_ID",
                    referencedColumnName = "roleId"
            )
    )
    private List<Role> roles = new ArrayList<>();


    public void enrolledRole(Role role) {
        roles.add(role);
    }
}
