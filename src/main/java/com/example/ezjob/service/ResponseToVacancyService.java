package com.example.ezjob.service;

import com.example.ezjob.persistense.entity.Resume;
import com.example.ezjob.persistense.entity.Vacancy;

public interface ResponseToVacancyService {
    void responseToVacancy(Resume resume, Vacancy vacancy);
}
