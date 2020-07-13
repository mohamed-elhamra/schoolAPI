package com.melhamra.schoolAPI.models;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
public class Teachers implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String first_name;
    private String last_name;
    @OneToMany(mappedBy =  "teachers",cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<SubjectsGroupsTeachersLink> links;

}
