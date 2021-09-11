package com.neshan.rajmanint.Repositories;

import com.neshan.rajmanint.Entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
