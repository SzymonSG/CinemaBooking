package com.cinema.booking.sacurity.configuration;

import com.cinema.booking.common.PermissionCheck;
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

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


//    @Bean
//    public UserDetailsService userDetailsService(){
//        return new CustomUserDetialsServiceImpl();
//    }
    private final UserDetailsService userDetailsService;

    //co to jest bean jak spring tym zarządza i wgl doczytaj
    //różnice miedzy wstrzyknięciem a beanem.
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

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
                .antMatchers("/save/**","/h2-console/**","/encode","/userid/**/roleid/**","/roleid/**/permiid/**",
                        "/movieid/**/cinemaid/**","/movieid/**/propertyid/**","/cinemaName/**/movieName/**/date",
                        "/cinemaName/**/movieName/**","/findByDate").permitAll()
                .antMatchers("/hello").hasAuthority(PermissionCheck.READ_PERM.name())
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
                //.formLogin()
                //.and()
                //.logout();

    }
}
