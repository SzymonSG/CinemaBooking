package com.cinema.booking.event;


import com.cinema.booking.entities.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.context.ApplicationEvent;

@ToString
@Getter
@Setter
public class ResendVerifactionTokenEvent extends ApplicationEvent {

    private User user;
    private String applicationUrl;
    private String token;

    public ResendVerifactionTokenEvent(User user, String applicationUrl, String token){
        super(user);
        this.user=user;
        this.applicationUrl=applicationUrl;
        this.token = token;
    }


}


