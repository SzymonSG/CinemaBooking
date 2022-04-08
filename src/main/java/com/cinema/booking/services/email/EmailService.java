package com.cinema.booking.services.email;

import javax.mail.MessagingException;

public interface EmailService {

    void sendEmailVerificationToken(String toEmail, String body, String subject)throws MessagingException;
}
