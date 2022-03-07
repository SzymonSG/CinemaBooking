package com.cinema.booking.security.jwt;

import com.google.common.net.HttpHeaders;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;



@NoArgsConstructor
@Getter
@Setter
@ConfigurationProperties(prefix = "cinema.jwt")
public class JwtConfig {

    private String secretKey;
    private String tokenPrefix;
    private Integer tokenExpirationTime;


    public String getAuthorizationHeader(){
        return HttpHeaders.AUTHORIZATION;
    }

}
