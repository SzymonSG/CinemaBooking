package com.cinema.booking.common;

import com.cinema.booking.entities.User;
import com.cinema.booking.entities.authUserEntitiesSupport.VerificationToken;

import javax.servlet.http.HttpServletRequest;

public interface RegisterUtils {

    String applicationUrl(HttpServletRequest request);

    void resendVerificationTokenEmail(User user, String applicationUrl, VerificationToken verificationLink);

    String passwordResetTokenEmail(User user, String applicationUrl, String token);
}
