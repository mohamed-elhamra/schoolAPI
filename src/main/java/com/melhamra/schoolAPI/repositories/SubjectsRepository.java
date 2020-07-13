package com.melhamra.schoolAPI.repositories;

import com.melhamra.schoolAPI.models.Subjects;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectsRepository extends JpaRepository<Subjects, Long> {
}
