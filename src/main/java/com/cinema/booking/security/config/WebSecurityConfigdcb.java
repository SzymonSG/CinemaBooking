package com.cinema.booking.security.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

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

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(WHITE_LIST_URLS).permitAll()
                .antMatchers("/movies").authenticated().and().httpBasic();

        //.antMatchers("/movies").hasRole("USER").anyRequest().authenticated();//.and().httpBasic();
        //.antMatchers("/hello").hasAuthority(PermissionCheck.READ_PERM.name())
        //.anyRequest()
        //.authenticated()
        //and()
        // .httpBasic();
    }


//    @Bean
//    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//          http
//                .cors()
//                .and()
//                .csrf()
//                .disable()
//                .authorizeHttpRequests().antMatchers(WHITE_LIST_URLS).permitAll();
//
//        return http.build();
//    }

}