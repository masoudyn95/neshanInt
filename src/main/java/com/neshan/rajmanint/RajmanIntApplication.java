package com.neshan.rajmanint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;
import java.util.List;

@SpringBootApplication
@EnableScheduling
@EnableConfigurationProperties
public class RajmanIntApplication {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private CourseStudentRepository courseStudentRepository;


    public static void main(String[] args) {
        SpringApplication.run(RajmanIntApplication.class, args);
    }

    @Scheduled(fixedRate = 600000)
    public void DBStatus(){
        System.out.printf("%d students have registered in this system since now!%n", studentRepository.count());
        List<CourseStudentRepository.CourseStudentSection> average = courseStudentRepository.getCourseStudentsAvg();
        for (CourseStudentRepository.CourseStudentSection courseStudentSection : average) {
            System.out.printf("Student with id %s -> gets %s average.%n", courseStudentSection.getStd(), courseStudentSection.getScr() );
        }
    }
}
