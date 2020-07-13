package com.melhamra.schoolAPI.entities;


import lombok.Data;

@Data
public class StudentsDto {

    private long id;
    private String first_name;
    private String last_name;
    private long groups;

}
