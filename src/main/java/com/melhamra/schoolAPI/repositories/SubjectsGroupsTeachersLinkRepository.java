package com.melhamra.schoolAPI.repositories;

import com.melhamra.schoolAPI.models.SubjectsGroupsTeachersLink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectsGroupsTeachersLinkRepository extends JpaRepository<com.melhamra.schoolAPI.models.SubjectsGroupsTeachersLink, Long> {

    List<SubjectsGroupsTeachersLink> findByGroups_Id(long id);
    List<SubjectsGroupsTeachersLink> findBySubjects_Id(long id);
    List<SubjectsGroupsTeachersLink> findByTeachers_Id(long id);

}
