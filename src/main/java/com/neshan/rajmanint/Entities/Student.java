package com.neshan.rajmanint.Entities;

import lombok.Data;
import org.springframework.data.redis.core.RedisHash;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "student")
@RedisHash("student")
@Data
public class Student implements Serializable {
    @Id
    @org.springframework.data.annotation.Id
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

    @OneToMany(mappedBy = "course", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    Set<CourseStudent> courseStudentRel;
}
