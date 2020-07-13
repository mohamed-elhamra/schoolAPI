package com.melhamra.schoolAPI.controllers;


import com.melhamra.schoolAPI.entities.MarksDto;
import com.melhamra.schoolAPI.mappers.MyMapper;
import com.melhamra.schoolAPI.models.Marks;
import com.melhamra.schoolAPI.repositories.MarksRepository;
import com.melhamra.schoolAPI.repositories.StudentsRepository;
import com.melhamra.schoolAPI.repositories.SubjectsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/schoolAPI")
public class MarksController {

    @Autowired
    private MarksRepository marksRepository;
    @Autowired
    private StudentsRepository studentsRepository;
    @Autowired
    private SubjectsRepository subjectsRepository;
    @Autowired
    private MyMapper myMapper;

    @PostMapping("/marks")
    public Map<String, Boolean> saveMarks(@RequestBody MarksDto marksDto){
        marksDto.setDate(new Date());
        Marks marks = myMapper.toMarks(marksDto);
        marks.setStudents(
                studentsRepository.findById(
                        marksDto.getStudents()).orElse(null));
        marks.setSubjects(
                subjectsRepository.findById(
                        marksDto.getSubjects()).orElse(null));
        marksRepository.save(marks);
        Map<String, Boolean> response = new HashMap<>();
        response.put("added",Boolean.TRUE);
        return response;
    }

    @GetMapping("/marks/{id}")
    public ResponseEntity<MarksDto> getMarksByID(@PathVariable Long id){
        Marks marks = marksRepository.findById(id).orElse(null);
        MarksDto marksDto = myMapper.toMarksDto(marks);
        return ResponseEntity.ok().body(marksDto);
    }

    @GetMapping("/marks")
    public List<MarksDto> getAllMarks(){
        return myMapper.toMarksDtoList(marksRepository.findAll());
    }

    @PatchMapping("/marks/{id}")
    public ResponseEntity<MarksDto> updateMarks(@PathVariable Long id,
                                                @RequestBody MarksDto marksDto){
        Marks marks = marksRepository.findById(id).orElse(null);
        marks.setMark(marksDto.getMark());
        marks.setStudents(
                studentsRepository.findById(
                        marksDto.getStudents()).orElse(null));
        marks.setSubjects(
                subjectsRepository.findById(
                        marksDto.getSubjects()).orElse(null));
        final Marks updatesMarks = marksRepository.save(marks);
        return ResponseEntity.ok().body(myMapper.toMarksDto(updatesMarks));
    }

    @DeleteMapping("/marks/{id}")
    public Map<String, Boolean> deleteMarks(@PathVariable Long id){
        Marks marks = marksRepository.findById(id).orElse(null);
        marksRepository.delete(marks);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted",Boolean.TRUE);
        return response;
    }
    
}
