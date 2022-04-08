package com.cinema.booking.event.listener;

import com.cinema.booking.entities.User;
import com.cinema.booking.entities.authUserEntitiesSupport.VerificationToken;
import com.cinema.booking.event.ResendVerifactionTokenEvent;
import com.cinema.booking.repository.UserRepository;
import com.cinema.booking.repository.VerificationTokenRepository;
import com.cinema.booking.services.email.EmailService;
import com.cinema.booking.services.email.EmailServiceImpl;
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

@Component
@Slf4j
@RequiredArgsConstructor
public class ResendVerficationEvent implements ApplicationListener<ResendVerifactionTokenEvent> {

    private final JavaMailSender javaMailSender;
    private final UserRepository userRepository;
    @Value("${cinema.mail.verifier.urlText}")
    private String urlText;
    private final VerificationTokenRepository verificationTokenRepository;
    private final EmailService emailService;


    @Override
    public void onApplicationEvent(ResendVerifactionTokenEvent event) {
        User user = event.getUser();


        String url =urlText+
                event.getApplicationUrl() +
                "/verifyRegistration?token="
                + event.getToken();
        //JavaSendMessged implement
        log.info("Click link to verify account: {}",url) ;
        //Send Mail to user
        String subject = "ReActive Account";
        try {
            emailService.sendEmailVerificationToken(user.getEmail(),url,subject);
//            sendEmailVerificationToken(user.getEmail(),url,subject);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
    //test


//    @Async
//    public void sendEmailVerificationToken(String toEmail,
//                                           String body,
//                                           String subject)throws MessagingException {
//
//        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
//        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,true);
//
//        mimeMessageHelper.setFrom("cinema.booking8@gmail.com");
//        mimeMessageHelper.setTo(toEmail);
//        mimeMessageHelper.setText(body);
//        mimeMessageHelper.setSubject(subject);
//        javaMailSender.send(mimeMessage);
//        log.info("Mail Send...");
//    }
}
