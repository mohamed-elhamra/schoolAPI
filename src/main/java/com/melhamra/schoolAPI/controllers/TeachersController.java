package com.melhamra.schoolAPI.controllers;


import com.melhamra.schoolAPI.entities.TeachersDto;
import com.melhamra.schoolAPI.mappers.MyMapper;
import com.melhamra.schoolAPI.models.Teachers;
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
public class TeachersController {

    @Autowired
    private TeachersRepository teachersRepository;
    @Autowired
    private MyMapper myMapper;

    @PostMapping("/teachers")
    public Map<String, Boolean> saveTeachers(@RequestBody TeachersDto teachersDto){
        Teachers teachers = myMapper.toTeachers(teachersDto);
        teachersRepository.save(teachers);
        Map<String, Boolean> response = new HashMap<>();
        response.put("added",Boolean.TRUE);
        return response;
    }

    @GetMapping("/teachers/{id}")
    public ResponseEntity<TeachersDto> getTeachersByID(@PathVariable Long id){
        Teachers teachers = teachersRepository.findById(id).orElse(null);
        TeachersDto teachersDto = myMapper.toTeachersDto(teachers);
        return ResponseEntity.ok().body(teachersDto);
    }

    @GetMapping("/teachers")
    public List<TeachersDto> getAllTeachers(){
        return myMapper.toTeachersDtoList(teachersRepository.findAll());
    }

    @PutMapping("/teachers/{id}")
    public ResponseEntity<TeachersDto> updateTeachers(@PathVariable Long id,
                                                      @RequestBody TeachersDto teachersDto){
        Teachers teachers = teachersRepository.findById(id).orElse(null);
        teachers.setFirst_name(teachersDto.getFirst_name());
        teachers.setLast_name(teachersDto.getLast_name());
        final Teachers updatesTeachers = teachersRepository.save(teachers);
        return ResponseEntity.ok().body(myMapper.toTeachersDto(updatesTeachers));
    }

    @DeleteMapping("/teachers/{id}")
    public Map<String, Boolean> deleteTeachers(@PathVariable Long id){
        Teachers teachers = teachersRepository.findById(id).orElse(null);
        teachersRepository.delete(teachers);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted",Boolean.TRUE);
        return response;
    }

}
