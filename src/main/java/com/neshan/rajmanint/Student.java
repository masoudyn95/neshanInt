package com.neshan.rajmanint;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "student")
@Data
public class Student {
    @Id
    @Column(name = "st_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long studentId;

    @Column(name = "st_name")
    private String stName;

    @Column(name = "st_age")
    private int stAge;

    @Column(name = "st_major")
    private String stMajor;

    @Column(name = "un_name")
    @Enumerated(EnumType.STRING)
    private University unName;

    @OneToMany(mappedBy = "course")
    Set<CourseStudent> courseStudentRel;
}
