package com.cinema.booking.security.jwt;

import com.cinema.booking.security.service.MyUserDetail;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;


import java.io.Serializable;
import java.util.Date;
import java.util.function.Function;

@Component
public class JwtUtility implements Serializable {

    private static final long serialVersionUID = 234234523523L;
    @Value("${cinema.jwt.secretKey}")
    private String secretKey;
    @Value("${cinema.jwt.tokenPrefix}")
    private String tokenPrefix;

    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {
//        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
        return Jwts.parser()
                .setSigningKey(Keys.hmacShaKeyFor(secretKey.getBytes()))
                .parseClaimsJws(token).getBody();
    }

    //retrieve expiration date from jwt token
    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    //validate token
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }


    public String generateToken(Authentication authentication){
        MyUserDetail principal = (MyUserDetail)
                authentication.getPrincipal();

        String build = Jwts.builder()
                .setSubject((principal.getUsername()))
                .claim("authorities", principal.getAuthorities())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + 900000))
                .signWith(Keys.hmacShaKeyFor(secretKey.getBytes()))
                .compact();
        String token = tokenPrefix+build;
        return token;

    }

}
