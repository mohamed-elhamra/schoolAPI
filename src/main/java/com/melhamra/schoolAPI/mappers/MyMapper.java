package com.melhamra.schoolAPI.mappers;


import com.melhamra.schoolAPI.entities.*;
import com.melhamra.schoolAPI.models.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MyMapper {

    GroupsDto toGroupsDto(Groups groups);

    Groups toGroups(GroupsDto groupsDto);

    @Mapping(target = "students", source = "students.id")
    @Mapping(target = "subjects", source = "subjects.id")
    MarksDto toMarksDto(Marks marks);

    @Mapping(target = "students", source = "students", ignore = true)
    @Mapping(target = "subjects", source = "subjects", ignore = true)
    Marks toMarks(MarksDto marksDto);

    @Mapping(target = "groups", source = "groups.id")
    StudentsDto toStudentsDto(Students students);

    @Mapping(target = "groups", source = "groups", ignore = true)
    Students toStudents(StudentsDto studentsDto);

    @Mapping(source = "subjects.id", target = "subjects")
    @Mapping(source = "teachers.id", target = "teachers")
    @Mapping(source = "groups.id", target = "groups")
    SubjectsGroupsTeachersLinkDto toSubjectsGroupsTeachersLinkDto(SubjectsGroupsTeachersLink subjectsGroupsTeachersLink);

    @Mapping(source = "subjects", target = "subjects", ignore = true)
    @Mapping(source = "teachers", target = "teachers", ignore = true)
    @Mapping(source = "groups", target = "groups", ignore = true)
    SubjectsGroupsTeachersLink toSubjectsGroupsTeachersLink(SubjectsGroupsTeachersLinkDto subjectsGroupsTeachersLinkDto);

    @Mapping(source = "subjects.id", target = "subjects")
    @Mapping(source = "teachers.id", target = "teachers")
    TeachersSubjectsDto toTeacherSubjectDto(SubjectsGroupsTeachersLink subjectsGroupsTeachersLink);

    @Mapping(source = "subjects.id", target = "subjects")
    @Mapping(source = "groups.id", target = "groups")
    SubjectsGroupsDto toSubjectsGroupsDto(SubjectsGroupsTeachersLink subjectsGroupsTeachersLink);

    @Mapping(source = "teachers.id", target = "teachers")
    @Mapping(source = "groups.id", target = "groups")
    GroupsTeachersDto toGroupsTeachersDto(SubjectsGroupsTeachersLink subjectsGroupsTeachersLink);

    SubjectsDto toSubjectsDto(Subjects subjects);

    Subjects toSubjects(SubjectsDto subjectsDto);

    TeachersDto toTeachersDto(Teachers teachers);

    Teachers toTeachers(TeachersDto teachersDto);

    List<TeachersDto> toTeachersDtoList(List<Teachers> teachers);

    List<SubjectsDto> toSubjectsDtoList(List<Subjects> subjects);

    List<GroupsDto> toGroupsDtoList(List<Groups> all);

    List<StudentsDto> toStudentsDtoList(List<Students> all);

    List<MarksDto> toMarksDtoList(List<Marks> all);

    List<TeachersSubjectsDto> toTeachersSubjectsDtoList(List<SubjectsGroupsTeachersLink> subjectsGroupsTeachersLink);

    List<SubjectsGroupsDto> toSubjectsGroupsDtoList(List<SubjectsGroupsTeachersLink> subjectsGroupsTeachersLink);

    List<GroupsTeachersDto> toGroupsTeachersDtoList(List<SubjectsGroupsTeachersLink> subjectsGroupsTeachersLink);

    List<SubjectsGroupsTeachersLinkDto> SubjectsGroupsTeachersLinkDto(List<SubjectsGroupsTeachersLink> all);
}
