package com.example.ezjob.persistense.repository.jpa;

import com.example.ezjob.persistense.entity.Resume;
import com.example.ezjob.persistense.entity.Vacancy;
import jakarta.annotation.Nonnull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ResumeRepository extends JpaRepository<Resume, Long> {
    @Query("SELECT r FROM Resume r WHERE (:position IS NULL OR r.position LIKE %:position%)")
    List<Resume> findResumeByPosition(@Nonnull @Param("position") String position);
}
