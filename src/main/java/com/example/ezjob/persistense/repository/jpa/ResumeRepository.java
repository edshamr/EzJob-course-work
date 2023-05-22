package com.example.ezjob.persistense.repository.jpa;

import com.example.ezjob.persistense.entity.Resume;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ResumeRepository extends JpaRepository<Resume, Long> {
    @Query("SELECT r FROM Resume r WHERE " +
            "(:position IS NULL OR r.position LIKE %:position%) " +
            "AND (:country IS NULL OR r.country LIKE %:country%) " +
            "AND (:experience IS NULL OR r.experience >= :experience OR :experience = 0)")
    List<Resume> findResumeByFilters(@Param("position") String position,
                                        @Param("country") String country,
                                        @Param("experience") Integer experience);


}
