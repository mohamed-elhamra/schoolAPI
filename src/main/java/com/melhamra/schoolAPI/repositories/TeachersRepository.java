package com.melhamra.schoolAPI.repositories;

import com.melhamra.schoolAPI.models.Teachers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeachersRepository extends JpaRepository<Teachers, Long> {
}
