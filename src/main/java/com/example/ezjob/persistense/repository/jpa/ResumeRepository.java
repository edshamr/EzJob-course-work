package com.example.ezjob.persistense.repository.jpa;

import com.example.ezjob.persistense.entity.Resume;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResumeRepository extends JpaRepository<Resume, Long> {
}
