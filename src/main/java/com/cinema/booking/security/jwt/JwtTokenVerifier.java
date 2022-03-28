package com.cinema.booking.security.jwt;
import com.cinema.booking.security.config.JwtConfig;
import com.cinema.booking.security.service.CustomUserDetailService;
import com.google.common.base.Strings;
import io.jsonwebtoken.JwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class JwtTokenVerifier extends OncePerRequestFilter {

//    @Value("${cinema.jwt.secretKey}")
//    private String secretKey;
//    @Value("${cinema.jwt.tokenPrefix}")
//    private String tokenPrefix;
// private final JwtConfig jwtConfig;
    private final JwtConfig jwtConfig;

    public JwtTokenVerifier(JwtConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
    }

    @Autowired
    private JwtUtility jwtUtility;
    @Autowired
    private CustomUserDetailService userDetailService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        String authorizationHeader = request.getHeader("Authorization");
        String authorizationHeader = request.getHeader(jwtConfig.getAuthorizationHeader());

        if (Strings.isNullOrEmpty(authorizationHeader) || !authorizationHeader.startsWith(jwtConfig.getTokenPrefix())) {
            filterChain.doFilter(request, response);
            return;
        }
        String email = null;
        String token = null;
        try {
            token = authorizationHeader.replace(jwtConfig.getTokenPrefix(), "");
            email = jwtUtility.getUsernameFromToken(token);
            if (email != null && SecurityContextHolder.getContext().getAuthentication()==null){
                UserDetails userDetails = userDetailService
                        .loadUserByUsername(email);
                if (jwtUtility.validateToken(token,userDetails)){
                    Authentication authentication = new UsernamePasswordAuthenticationToken(
                            email,
                            null,
                            userDetails.getAuthorities()
                    );
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }

        } catch (JwtException e) {
            throw new IllegalStateException(String.format("The token %s is fake!", token));
        }

        filterChain.doFilter(request,response);
    }
}
