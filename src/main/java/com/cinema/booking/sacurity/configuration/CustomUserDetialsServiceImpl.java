package com.cinema.booking.sacurity.configuration;

import com.cinema.booking.sacurity.repository.UserRepository;
import com.cinema.booking.sacurity.securityEntites.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class CustomUserDetialsServiceImpl implements UserDetailsService {

    @Autowired
    private  UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User userEmail = userRepository.findRealEmail(email);
        if (userEmail==null){
            throw new UsernameNotFoundException("This  user email not found"+email);
        }else{
            return new MyUserDetails(userEmail);
        }

    }
}
