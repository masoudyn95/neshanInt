package com.neshan.rajmanint.Entities;
import com.neshan.rajmanint.Entities.Course;
import com.neshan.rajmanint.Entities.CrsStdFKey;
import com.neshan.rajmanint.Entities.Student;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "course_student")
@Data
public class CourseStudent {

    @EmbeddedId
    private CrsStdFKey crsStdFKey;

    @ManyToOne
    @MapsId("student_id")
    @JoinColumn(name = "st_id")
    private Student student;

    @ManyToOne
    @MapsId("course_id")
    @JoinColumn(name = "crs_id")
    private Course course;

    @Column(name = "score")
    int score;

    @Column(name = "date")
    private String date;


}
