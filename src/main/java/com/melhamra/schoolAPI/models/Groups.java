package com.melhamra.schoolAPI.models;


import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
public class Groups implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    @OneToMany(mappedBy = "groups")
    private List<Students> studentsList;
    @OneToMany(mappedBy =  "groups", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<SubjectsGroupsTeachersLink> links;


}
