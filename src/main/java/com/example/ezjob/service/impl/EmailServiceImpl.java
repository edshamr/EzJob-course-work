package com.example.ezjob.service.impl;

import com.example.ezjob.common.ApplicationConstants.EmailConfirmReplyAttributes.UserEmailAttributes;
import com.example.ezjob.common.ApplicationConstants.EmailConfirmReplyAttributes.CompanyEmailAttributes;
import com.example.ezjob.service.EmailService;
import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {
    private final JavaMailSender emailSender;

    public void notifyUserOfSuccessfulResponse (@Nonnull final String to) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(UserEmailAttributes.SUBJECT);
        message.setText(UserEmailAttributes.MESSAGE_BODY);
        emailSender.send(message);
    }

    public void notifyCompanyOfAnswerOnVacancy (@Nonnull final String to) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(CompanyEmailAttributes.SUBJECT);
        message.setText(CompanyEmailAttributes.MESSAGE_BODY);
        emailSender.send(message);
    }
}
