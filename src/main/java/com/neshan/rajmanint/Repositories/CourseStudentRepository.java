package com.neshan.rajmanint.Repositories;

import com.neshan.rajmanint.Entities.CourseStudent;
import com.neshan.rajmanint.Entities.CrsStdFKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CourseStudentRepository extends JpaRepository<CourseStudent, CrsStdFKey> {

    @Query("select crsStdFKey.student_id as std ,avg(score) as scr from CourseStudent group by crsStdFKey.student_id")
    List<CourseStudentSection> getCourseStudentsAvg();

    interface CourseStudentSection {
        String getStd();
        String getScr();
    }
}

