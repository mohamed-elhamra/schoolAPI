package com.melhamra.schoolAPI.controllers;


import com.melhamra.schoolAPI.entities.GroupsTeachersDto;
import com.melhamra.schoolAPI.entities.SubjectsGroupsDto;
import com.melhamra.schoolAPI.entities.SubjectsGroupsTeachersLinkDto;
import com.melhamra.schoolAPI.entities.TeachersSubjectsDto;
import com.melhamra.schoolAPI.mappers.MyMapper;
import com.melhamra.schoolAPI.models.SubjectsGroupsTeachersLink;
import com.melhamra.schoolAPI.repositories.GroupsRepository;
import com.melhamra.schoolAPI.repositories.SubjectsGroupsTeachersLinkRepository;
import com.melhamra.schoolAPI.repositories.SubjectsRepository;
import com.melhamra.schoolAPI.repositories.TeachersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/schoolAPI")
public class SubjectsGroupsTeachersLinkController {

    @Autowired
    private SubjectsGroupsTeachersLinkRepository subjectsGroupsTeachersLinkRepository;
    @Autowired
    private TeachersRepository teachersRepository;
    @Autowired
    private GroupsRepository groupsRepository;
    @Autowired
    private SubjectsRepository subjectsRepository;
    @Autowired
    private MyMapper myMapper;


    @PostMapping("/subject_teacher")
    public Map<String, Boolean> saveSubjectsGroupsTeachersLink(@RequestBody SubjectsGroupsTeachersLinkDto
                                                               subjectsGroupsTeachersLinkDto){
        SubjectsGroupsTeachersLink subjectsGroupsTeachersLink =
                myMapper.toSubjectsGroupsTeachersLink(subjectsGroupsTeachersLinkDto);

        subjectsGroupsTeachersLink.setSubjects(subjectsRepository.findById(
                subjectsGroupsTeachersLinkDto.getSubjects()
        ).orElse(null));
        subjectsGroupsTeachersLink.setGroups(groupsRepository.findById(
                subjectsGroupsTeachersLinkDto.getGroups()
        ).orElse(null));
        subjectsGroupsTeachersLink.setTeachers(teachersRepository.findById(
                subjectsGroupsTeachersLinkDto.getTeachers()
        ).orElse(null));
        subjectsGroupsTeachersLinkRepository.save(subjectsGroupsTeachersLink);

        Map<String, Boolean> response = new HashMap<>();
        response.put("added", Boolean.TRUE);
        return response;
    }

    @GetMapping("/subject_teacher/groups/{id}")
    public List<TeachersSubjectsDto> getAllGroupsSubjects(@PathVariable long id){
        List<SubjectsGroupsTeachersLink> subjectsGroupsTeachersLink =
                subjectsGroupsTeachersLinkRepository.findByGroups_Id(id);
        List<TeachersSubjectsDto> list =
                myMapper.toTeachersSubjectsDtoList(subjectsGroupsTeachersLink);
        return list;
    }

    @GetMapping("/subject_teacher/teachers/{id}")
    public List<SubjectsGroupsDto> getAllTeachersGroups(@PathVariable long id){
        List<SubjectsGroupsTeachersLink> subjectsGroupsTeachersLink =
                subjectsGroupsTeachersLinkRepository.findByTeachers_Id(id);
        List<SubjectsGroupsDto> list =
                myMapper.toSubjectsGroupsDtoList(subjectsGroupsTeachersLink);
        return list;
    }

    @GetMapping("/subject_teacher/subjects/{id}")
    public List<GroupsTeachersDto> getAllSubjectsGroups(@PathVariable long id){
        List<SubjectsGroupsTeachersLink> subjectsGroupsTeachersLink =
                subjectsGroupsTeachersLinkRepository.findBySubjects_Id(id);
        List<GroupsTeachersDto> list =
                myMapper.toGroupsTeachersDtoList(subjectsGroupsTeachersLink);
        return list;
    }

    @GetMapping("/subject_teacher")
    public List<SubjectsGroupsTeachersLinkDto> subjectsGroupsTeachersLinkDtoList(){
        return myMapper.SubjectsGroupsTeachersLinkDto(
                subjectsGroupsTeachersLinkRepository.findAll()
        );
    }

    @PatchMapping("/subject_teacher/{id}")
    public SubjectsGroupsTeachersLinkDto updateSubjectsGroupsTeachersLink(@PathVariable long id,
                                                                 @RequestBody SubjectsGroupsTeachersLinkDto subjectsGroupsTeachersLinkDto){
        SubjectsGroupsTeachersLink subjectsGroupsTeachersLink =
                subjectsGroupsTeachersLinkRepository.findById(id).orElse(null);

        subjectsGroupsTeachersLink.setSubjects(subjectsRepository.findById(
                subjectsGroupsTeachersLinkDto.getSubjects()
        ).orElse(null));
        subjectsGroupsTeachersLink.setGroups(groupsRepository.findById(
                subjectsGroupsTeachersLinkDto.getGroups()
        ).orElse(null));
        subjectsGroupsTeachersLink.setTeachers(teachersRepository.findById(
                subjectsGroupsTeachersLinkDto.getTeachers()
        ).orElse(null));

        final SubjectsGroupsTeachersLink updatedSubjectsGroupsTeachersLink =
                subjectsGroupsTeachersLinkRepository.save(subjectsGroupsTeachersLink);

        return myMapper.toSubjectsGroupsTeachersLinkDto(updatedSubjectsGroupsTeachersLink);
    }

    @DeleteMapping("/subject_teacher/{id}")
    public Map<String, Boolean> deleteSubjectsGroupsTeachersLink(@PathVariable long id){
        SubjectsGroupsTeachersLink subjectsGroupsTeachersLink =
                subjectsGroupsTeachersLinkRepository.findById(id).orElse(null);
        subjectsGroupsTeachersLinkRepository.delete(subjectsGroupsTeachersLink);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    @GetMapping("/subject_teacher/{id}")
    public ResponseEntity<SubjectsGroupsTeachersLinkDto> getSgTByID(@PathVariable long id){
        SubjectsGroupsTeachersLink subjectsGroupsTeachersLink =
                subjectsGroupsTeachersLinkRepository.findById(id).orElse(null);
        return ResponseEntity.ok().body(
          myMapper.toSubjectsGroupsTeachersLinkDto(subjectsGroupsTeachersLink)
        );
    }
}
