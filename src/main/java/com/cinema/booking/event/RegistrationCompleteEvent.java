package com.cinema.booking.event;

import com.cinema.booking.entities.User;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;


@Getter
@Setter
public class RegistrationCompleteEvent extends ApplicationEvent {

    private User user;
    //it's
    private String applicationUrl;
    //is url we have to create for user, when we send the email to click


    public RegistrationCompleteEvent(User user,String applicationUrl) {
        super(user);
        this.user = user;
        this.applicationUrl= applicationUrl;
    }
}
