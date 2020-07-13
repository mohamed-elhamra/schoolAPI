package com.melhamra.schoolAPI.models;


import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Table(uniqueConstraints = @UniqueConstraint(columnNames={"subjects_id", "teachers_id", "groups_id"}))
@Entity
public class SubjectsGroupsTeachersLink implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Subjects subjects;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Teachers teachers;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Groups groups;

}
