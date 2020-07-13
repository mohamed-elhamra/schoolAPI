package com.melhamra.schoolAPI.controllers;


import com.melhamra.schoolAPI.entities.GroupsDto;
import com.melhamra.schoolAPI.mappers.MyMapper;
import com.melhamra.schoolAPI.models.Groups;
import com.melhamra.schoolAPI.repositories.GroupsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/schoolAPI")
public class GroupsController {

    @Autowired
    private GroupsRepository groupsRepository;
    @Autowired
    private MyMapper myMapper;

    @PostMapping("/groups")
    public Map<String, Boolean> saveGroups(@RequestBody GroupsDto groupsDto){
        Groups groups = myMapper.toGroups(groupsDto);
        groupsRepository.save(groups);
        Map<String, Boolean> response = new HashMap<>();
        response.put("added",Boolean.TRUE);
        return response;
    }

    @GetMapping("/groups/{id}")
    public ResponseEntity<GroupsDto> getGroupsByID(@PathVariable Long id){
        Groups groups = groupsRepository.findById(id).orElse(null);
        GroupsDto groupsDto = myMapper.toGroupsDto(groups);
        return ResponseEntity.ok().body(groupsDto);
    }

    @GetMapping("/groups")
    public List<GroupsDto> getAllGroups(){
        return myMapper.toGroupsDtoList(groupsRepository.findAll());
    }

    @PutMapping("/groups/{id}")
    public ResponseEntity<GroupsDto> updateGroups(@PathVariable Long id,
                                                  @RequestBody GroupsDto groupsDto){
        Groups groups = groupsRepository.findById(id).orElse(null);
        groups.setName(groupsDto.getName());
        final Groups updatesGroups = groupsRepository.save(groups);
        return ResponseEntity.ok().body(myMapper.toGroupsDto(updatesGroups));
    }

    @DeleteMapping("/groups/{id}")
    public Map<String, Boolean> deleteGroups(@PathVariable Long id){
        Groups groups = groupsRepository.findById(id).orElse(null);
        groupsRepository.delete(groups);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted",Boolean.TRUE);
        return response;
    }
}
