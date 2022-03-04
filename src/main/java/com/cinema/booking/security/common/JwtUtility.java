package com.cinema.booking.security.common;

import com.cinema.booking.security.service.MyUserDetail;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtility {


    public String generateToken(Authentication authentication){
        MyUserDetail principal = (MyUserDetail)
                authentication.getPrincipal();

        String secretKey = "securesecuresecuresecuresecuresecuresecuresecuresecuresecuresecuresecuresecuresecure";
        String build = Jwts.builder()
                .setSubject((principal.getUsername()))
                .claim("authorities", principal.getAuthorities())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + 90000))
                .signWith(Keys.hmacShaKeyFor(secretKey.getBytes()))
                .compact();
        String token = "Bearer "+build;
        return token;


    }

}
