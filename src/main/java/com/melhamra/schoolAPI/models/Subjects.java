package com.melhamra.schoolAPI.models;


import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
public class Subjects implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    @OneToMany(mappedBy = "subjects")
    private List<Marks> marksList;
    @OneToMany(mappedBy =  "subjects",cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<SubjectsGroupsTeachersLink> links;

}
