package com.cinema.booking.common;



import com.cinema.booking.entities.User;
import com.cinema.booking.entities.authUserEntitiesSupport.VerificationToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
@Slf4j
public class RegistraionUtilize implements RegisterUtils {

    public String applicationUrl(HttpServletRequest request) {
        return "http://" +
                request.getServerName() +
                ":" +
                request.getServerPort() +
                request.getContextPath();
    }

    public void resendVerificationTokenEmail(User user, String applicationUrl, VerificationToken verificationLink) {

        String url = applicationUrl +
                "/verifyRegistration?token="
                + verificationLink.getToken();

        log.info("Click link to verify account: {}", url);

    }

    public String passwordResetTokenEmail(User user, String applicationUrl, String token) {
        String url =
                applicationUrl +
                        "/savePassword?token="
                        + token;

        log.info("Click link to Reset password: {}", url);
        return url;
    }
}
