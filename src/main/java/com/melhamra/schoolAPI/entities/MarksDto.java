package com.melhamra.schoolAPI.entities;


import lombok.Data;

import java.util.Date;

@Data
public class MarksDto {

    private long id;
    private Date date;
    private double mark;
    private long students;
    private long subjects;

}
