package com.cinema.booking.sacurity.securityEntites;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roleId;

    private String name;

    @JsonIgnore
    @ManyToMany(mappedBy = "roles")
    private Set<User> users = new HashSet<>();


    @ManyToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    @JoinTable(name = "RolesWithPermissions",
            joinColumns =
            @JoinColumn(name = "role_ID",
                        referencedColumnName = "roleId"),
            inverseJoinColumns =
            @JoinColumn(name = "permission_ID",
                        referencedColumnName = "permissionId")
    )
    private List<Permission> permissions = new ArrayList<>();

    public void enrolledPermission(Permission permission) {
        permissions.add(permission);
    }
}
