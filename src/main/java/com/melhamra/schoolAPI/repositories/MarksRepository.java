package com.melhamra.schoolAPI.repositories;

import com.melhamra.schoolAPI.models.Marks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MarksRepository extends JpaRepository<Marks, Long> {
}
