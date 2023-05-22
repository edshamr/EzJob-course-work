package com.example.ezjob.service.impl;

import com.example.ezjob.persistense.entity.Resume;
import com.example.ezjob.persistense.entity.Vacancy;
import com.example.ezjob.persistense.repository.jpa.ResumeRepository;
import com.example.ezjob.persistense.repository.jpa.VacancyRepository;
import com.example.ezjob.service.EmailService;
import com.example.ezjob.service.ResponseToVacancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ResponseToVacancyServiceImpl implements ResponseToVacancyService {
    private final ResumeRepository resumeRepository;
    private final VacancyRepository vacancyRepository;
    private final EmailService emailService;
    @Override
    public void responseToVacancy(Resume resume, Vacancy vacancy) {
        resume.getVacancies().add(vacancy);
        vacancy.getResumes().add(resume);

        resumeRepository.save(resume);
        vacancyRepository.save(vacancy);

        final var userEmail = resume.getAuthUser().getEmail();
        final var companyEmail = vacancy.getCompany().getAuthUser().getEmail();

        emailService.notifyUserOfSuccessfulResponse(userEmail);
        emailService.notifyCompanyOfAnswerOnVacancy(companyEmail);
    }
}
