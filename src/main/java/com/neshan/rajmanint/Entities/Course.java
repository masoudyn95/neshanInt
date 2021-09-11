package com.neshan.rajmanint.Entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "course")
@Data
public class Course {
    @Id
    @Column(name="crs_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long courseId;

    @Column(name="crs_name")
    private String courseName;

    @Column(name="crs_value")
    private int courseValue;

    @OneToMany(mappedBy = "student")
    Set<CourseStudent> courseStudentRel;
}
