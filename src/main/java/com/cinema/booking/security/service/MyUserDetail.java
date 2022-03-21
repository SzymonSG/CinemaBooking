package com.cinema.booking.security.service;

import com.cinema.booking.entities.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@Builder
public class MyUserDetail implements UserDetails {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    @JsonIgnore
    private String password;
    private boolean enabled;
    private Collection<? extends GrantedAuthority> authorities;


    public MyUserDetail(Long id, String firstName, String lastName, String email, String password,boolean enabled,  Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.enabled = enabled;
        this.authorities = authorities;
    }

    //private final User user;
   // public MyUserDetail(User user) {
//        this.user = user;
//    }
    public static MyUserDetail build(User user){
        String roles = user.getRole();
        List<GrantedAuthority> authorities = getGrantedAuthorities(List.of(roles));

        MyUserDetail myUser = MyUserDetail.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .email(user.getEmail())
                .password(user.getPassword())
                .enabled(user.isEnabled())
                .authorities(authorities)
                .build();

        return myUser;
    }

    private static List<GrantedAuthority> getGrantedAuthorities(List<String> roles) {
        List<GrantedAuthority>authorities = new ArrayList<>();
        for (String role : roles){
            authorities.add(new SimpleGrantedAuthority(role));
        }
        return authorities;
    }



//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        String roles = user.getRole();
//        return getGrantedAuthorities(List.of(roles));
//    }
//
//    private List<? extends GrantedAuthority> getGrantedAuthorities(List<String> roles) {
//        List<GrantedAuthority>authorities = new ArrayList<>();
//        for (String role:roles){
//            authorities.add(new SimpleGrantedAuthority(role));
//        }
//        return authorities;
//
//    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
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


}
