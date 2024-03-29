package com.example.ezjob.persistense.repository.jpa;

import com.example.ezjob.persistense.entity.Vacancy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VacancyRepository extends JpaRepository<Vacancy, Long> {
    @Query("SELECT v FROM Vacancy v WHERE (:title IS NULL OR v.title LIKE %:title%) " +
            "AND (:city IS NULL OR v.city LIKE %:city%)")
    List<Vacancy> findVacancyByFilters(@Param("title") String title,
                                       @Param("city") String city);

}
