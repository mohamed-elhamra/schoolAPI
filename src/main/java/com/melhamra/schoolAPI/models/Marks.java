package com.melhamra.schoolAPI.models;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
public class Marks implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private Date date;
    private double mark;
    @ManyToOne
    private Students students;
    @ManyToOne
    private Subjects subjects;

}
