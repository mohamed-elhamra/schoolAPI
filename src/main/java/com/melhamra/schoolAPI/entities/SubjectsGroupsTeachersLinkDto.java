package com.melhamra.schoolAPI.entities;

import lombok.Data;

@Data
public class SubjectsGroupsTeachersLinkDto {

    private long id;
    private long subjects;
    private long teachers;
    private long groups;

}
