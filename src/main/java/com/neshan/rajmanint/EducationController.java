package com.neshan.rajmanint;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EducationController {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private CourseStudentRepository courseStudentRepository;

    @RequestMapping(value = "/api/getAllStudents", method = RequestMethod.GET)
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        if (students.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @RequestMapping(value = "/api/getAllCourses", method = RequestMethod.GET)
    public ResponseEntity<List<Course>> getAllCourses() {
        List<Course> courses = courseRepository.findAll();
        if (courses.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }

    @RequestMapping(value = "/api/getAllStudentAvg", method = RequestMethod.GET)
    public ResponseEntity<List<CourseStudentRepository.CourseStudentSection>> getAllStudentAvg() {
        List<CourseStudentRepository.CourseStudentSection> courseStudents = courseStudentRepository.getCourseStudentsAvg();
        if (courseStudents.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(courseStudents, HttpStatus.OK);
    }

    @RequestMapping(value = "/api/deleteStudent/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<HttpStatus> deleteStudentById(@RequestParam(name = "id") long id) {
        studentRepository.deleteByStudentId(id);
        try {
            studentRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
