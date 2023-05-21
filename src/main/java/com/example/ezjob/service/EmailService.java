package com.example.ezjob.service;

import jakarta.annotation.Nonnull;

public interface EmailService {
    void notifyUserOfSuccessfulResponse (@Nonnull final String to);
    void notifyCompanyOfAnswerOnVacancy (@Nonnull final String to);
}
