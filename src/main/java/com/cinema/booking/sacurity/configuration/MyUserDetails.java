package com.cinema.booking.sacurity.configuration;

import com.cinema.booking.sacurity.securityEntites.Permission;
import com.cinema.booking.sacurity.securityEntites.Role;
import com.cinema.booking.sacurity.securityEntites.User;
import org.springframework.security.core.GrantedAuthority;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MyUserDetails implements UserDetails {

    private User user;
    public MyUserDetails(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<Role> roles = user.getRoles();
        return getGrantedAuthorities(getPermissions(roles));
    }

    private List<GrantedAuthority> getGrantedAuthorities(List<String> permissions) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String permission:permissions){
            authorities.add(new SimpleGrantedAuthority(permission));
        }
        return authorities;
    }

    private List<String> getPermissions(List<Role> roles) {
        ArrayList<String> permissions = new ArrayList<>();
        ArrayList<Permission> permissionsObjects = new ArrayList<>();

        for(Role role: roles){
            permissions.add(role.getName());
            permissionsObjects.addAll(role.getPermissions());
        }
        for(Permission perm: permissionsObjects){
            permissions.add(perm.getName());
        }
        return permissions;
    }


    @Override
    public String getPassword() {
        return user.getPassword();
    }

    //tutaj do sprawdzenia
    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return user.isActive();
    }
}
