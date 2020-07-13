package com.melhamra.schoolAPI.models;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
public class Students implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String first_name;
    private String last_name;
    @ManyToOne
    private Groups groups;
    @OneToMany(mappedBy = "students")
    private List<Marks> marksList;

}
