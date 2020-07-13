package com.melhamra.schoolAPI.controllers;

import com.melhamra.schoolAPI.entities.SubjectsDto;
import com.melhamra.schoolAPI.entities.SubjectsDto;
import com.melhamra.schoolAPI.entities.TeachersDto;
import com.melhamra.schoolAPI.mappers.MyMapper;
import com.melhamra.schoolAPI.models.Subjects;
import com.melhamra.schoolAPI.models.Teachers;
import com.melhamra.schoolAPI.repositories.SubjectsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/schoolAPI")
public class SubjectsController {

    @Autowired
    private SubjectsRepository subjectsRepository;
    @Autowired
    private MyMapper myMapper;

    @PostMapping("/subjects")
    public Map<String, Boolean> saveSubjects(@RequestBody SubjectsDto subjectsDto){
        Subjects subjects = myMapper.toSubjects(subjectsDto);
        subjectsRepository.save(subjects);
        Map<String, Boolean> response = new HashMap<>();
        response.put("added",Boolean.TRUE);
        return response;
    }

    @GetMapping("/subjects/{id}")
    public ResponseEntity<SubjectsDto> GetSubjects(@PathVariable Long id){
        Subjects subjects = subjectsRepository.findById(id).orElse(null);
        SubjectsDto subjectsDto = myMapper.toSubjectsDto(subjects);
        return ResponseEntity.ok().body(subjectsDto);
    }

    @GetMapping("/subjects")
    public List<SubjectsDto> getAllSubjects(){
        return myMapper.toSubjectsDtoList(subjectsRepository.findAll());
    }

    @PutMapping("/subjects/{id}")
    public ResponseEntity<SubjectsDto> updateSubjects(@PathVariable Long id,
                                                      @RequestBody SubjectsDto subjectsDto){
        Subjects subjects = subjectsRepository.findById(id).orElse(null);
        subjects.setTitle(subjectsDto.getTitle());
        final Subjects updatedSubjects = subjectsRepository.save(subjects);
        return ResponseEntity.ok(myMapper.toSubjectsDto(updatedSubjects));
    }

    @DeleteMapping("/subjects/{id}")
    public Map<String, Boolean> deleteSubjects(@PathVariable Long id){
        Subjects subjects = subjectsRepository.findById(id).orElse(null);
        subjectsRepository.delete(subjects);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted",Boolean.TRUE);
        return response;
    }


}
