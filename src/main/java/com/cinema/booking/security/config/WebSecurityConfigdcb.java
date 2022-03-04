package com.cinema.booking.security.config;
import com.cinema.booking.security.jwt.EntryPointAuth;
import com.cinema.booking.security.jwt.JwtTokenVerifier;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static com.cinema.booking.security.common.Utilize.WHITE_LIST_URLS;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class WebSecurityConfigdcb extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(11);
    }
    private final UserDetailsService userDetailsService;

    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


    @Bean
    public JwtTokenVerifier verifierJwtTokenFilter(){
        return new JwtTokenVerifier();
    }

    @Autowired
    private EntryPointAuth unauthorizedHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http

                .csrf().disable()
                .exceptionHandling()
                    .authenticationEntryPoint(unauthorizedHandler).and()
                .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(WHITE_LIST_URLS).permitAll()
                .antMatchers("/movies").hasAuthority("USER").anyRequest().authenticated();


        http.addFilterBefore(verifierJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);

///                .and()
//                .httpBasic();

    }



}