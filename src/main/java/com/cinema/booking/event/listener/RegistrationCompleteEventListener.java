package com.cinema.booking.event.listener;


import com.cinema.booking.entities.User;
import com.cinema.booking.event.RegistrationCompleteEvent;
import com.cinema.booking.event.ResendVerifactionTokenEvent;
import com.cinema.booking.services.email.EmailService;
import com.cinema.booking.services.userService.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.UUID;

@Component
@Slf4j
@RequiredArgsConstructor
public class RegistrationCompleteEventListener implements ApplicationListener<RegistrationCompleteEvent> {

    private final UserService userService;
    private final JavaMailSender javaMailSender;
    @Value("${cinema.mail.verifier.urlText}")
    private String urlText;
    @Value("${cinema.mail.verifier.urlPrefix}")
    private String urlPrefix;
    @Value("${cinema.mail.verifier.subject}")
    private String subject;

    private final EmailService emailService;




    @Override
    public void onApplicationEvent(RegistrationCompleteEvent event) {
        //Create the verification Token for the user with link
        User user = event.getUser();
        String token = UUID.randomUUID().toString();

        userService.saveVerificationTokenForUser(token,user);

        String url =urlText+
                event.getApplicationUrl() +
                "/verifyRegistration?token="
                + token;
        //JavaSendMessged implement
        log.info("Click link to verify account: {}",url) ;
        //Send Mail to user
//        String subject = "Active Account";
        try {
//            sendEmailVerificationToken(user.getEmail(),url,subject);
            emailService.sendEmailVerificationToken(user.getEmail(),url,subject);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }


    @Async
    public void sendEmailVerificationToken(String toEmail,
                                           String body,
                                           String subject)throws MessagingException {

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,true);

        mimeMessageHelper.setFrom("cinema.booking8@gmail.com");
        mimeMessageHelper.setTo(toEmail);
        mimeMessageHelper.setText(body);
        mimeMessageHelper.setSubject(subject);
        javaMailSender.send(mimeMessage);
        log.info("Mail Send...");
    }

}
