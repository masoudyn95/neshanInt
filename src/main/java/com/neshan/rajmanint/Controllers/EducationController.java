package com.neshan.rajmanint.Controllers;
import com.neshan.rajmanint.Entities.Course;
import com.neshan.rajmanint.Entities.Student;
import com.neshan.rajmanint.Entities.Teacher;
import com.neshan.rajmanint.Entities.University;
import com.neshan.rajmanint.Repositories.CourseRepository;
import com.neshan.rajmanint.Repositories.CourseStudentRepository;
import com.neshan.rajmanint.Repositories.StudentRepository;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

@ConfigurationProperties(prefix = "remote")
@RestController
@Data
public class EducationController {
    private String url;
    private StudentRepository studentRepository;
    private CourseRepository courseRepository;
    private CourseStudentRepository courseStudentRepository;

    public EducationController(CourseRepository courseRepository, StudentRepository studentRepository, CourseStudentRepository courseStudentRepository){
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
        this.courseStudentRepository = courseStudentRepository;
    }

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

    @RequestMapping(value = "/api/getUniversityStd", method = RequestMethod.GET)
    public ResponseEntity<Map<University, Long>> getUniversitiesStd() {
        List<Student> students = getAllStudents().getBody();
        Map<University, Long> collection = students.stream().collect(groupingBy(student -> student.getUnName(), counting()));
        if (collection.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(collection, HttpStatus.OK);
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

    @RequestMapping(value = "/api/addStudent", method = RequestMethod.POST)
    public ResponseEntity<HttpStatus> addStudent(@RequestBody Student student) {
        studentRepository.save(student);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @RequestMapping(value = "/api/getTeachers/restTemplate/{id}", method = RequestMethod.GET)
    public ResponseEntity<Teacher> getTeacherTemplate(@PathVariable (name = "id") long id) {
        RestTemplate restTemplate = new RestTemplate();
        String url1 = String.format("%s/api/getTeacherById/%d", url, id);
        System.out.println(url1);
        Teacher teacher = restTemplate.getForObject(url, Teacher.class);
        if (teacher == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(teacher, HttpStatus.OK);
    }

    @RequestMapping(value = "/api/getTeachers/webClient/{id}", method = RequestMethod.GET)
    public ResponseEntity<Teacher> getTeacherWClient(@PathVariable (name = "id") long id) {
        String url1 = String.format("%s/api/getTeacherById/%d", url, id);
        System.out.println(url1);
        WebClient client = WebClient.create();
        WebClient.ResponseSpec responseSpec = client.get()
                .uri(url1)
                .retrieve();
        Teacher teacher = responseSpec.bodyToMono(Teacher.class).block();
        if (teacher == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(teacher, HttpStatus.OK);
    }
}
