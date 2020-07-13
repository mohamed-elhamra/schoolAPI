package com.melhamra.schoolAPI.controllers;

import com.melhamra.schoolAPI.entities.StudentsDto;
import com.melhamra.schoolAPI.mappers.MyMapper;
import com.melhamra.schoolAPI.models.Groups;
import com.melhamra.schoolAPI.models.Students;
import com.melhamra.schoolAPI.repositories.GroupsRepository;
import com.melhamra.schoolAPI.repositories.StudentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@CrossOrigin
@RestController
@RequestMapping("/schoolAPI")
public class StudentsController {

    @Autowired
    private StudentsRepository studentsRepository;
    @Autowired
    private GroupsRepository groupsRepository;
    @Autowired
    private MyMapper myMapper;

    @PostMapping("/students")
    public Map<String, Boolean> saveStudents(@RequestBody StudentsDto studentsDto){
        Students students = myMapper.toStudents(studentsDto);
        Groups groups = groupsRepository.findById(studentsDto.getGroups()).orElse(null);
        students.setGroups(groups);
        studentsRepository.save(students);
        Map<String, Boolean> response = new HashMap<>();
        response.put("added",Boolean.TRUE);
        return response;
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<StudentsDto> getStudentsByID(@PathVariable Long id){
        Students students = studentsRepository.findById(id).orElse(null);
        StudentsDto studentsDto = myMapper.toStudentsDto(students);
        return ResponseEntity.ok().body(studentsDto);
    }

    @GetMapping("/students")
    public List<StudentsDto> getAllStudents(){
        return myMapper.toStudentsDtoList(studentsRepository.findAll());
    }

    @PutMapping("/students/{id}")
    public ResponseEntity<StudentsDto> updateStudents(@PathVariable Long id,
                                                      @RequestBody StudentsDto studentsDto){
        Students students = studentsRepository.findById(id).orElse(null);
        Groups groups = groupsRepository.findById(studentsDto.getGroups()).orElse(null);
        students.setFirst_name(studentsDto.getFirst_name());
        students.setLast_name(studentsDto.getLast_name());
        students.setGroups(groups);
        final Students updatesStudents = studentsRepository.save(students);
        return ResponseEntity.ok().body(myMapper.toStudentsDto(updatesStudents));
    }

    @DeleteMapping("/students/{id}")
    public Map<String, Boolean> deleteStudents(@PathVariable Long id){
        Students students = studentsRepository.findById(id).orElse(null);
        studentsRepository.delete(students);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted",Boolean.TRUE);
        return response;
    }
    
}
