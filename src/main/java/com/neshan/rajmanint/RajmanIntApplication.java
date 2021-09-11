package com.neshan.rajmanint;

import com.neshan.rajmanint.Entities.Student;
import com.neshan.rajmanint.Repositories.CourseStudentRepository;
import com.neshan.rajmanint.Repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.annotation.PostConstruct;
import java.util.List;

@SpringBootApplication
@EnableScheduling
@EnableCaching
public class RajmanIntApplication {
    private StudentRepository studentRepository;
    private CourseStudentRepository courseStudentRepository;
    private static ApplicationContext applicationContext;

    @Autowired
    public RajmanIntApplication(StudentRepository studentRepository, CourseStudentRepository courseStudentRepository) {
        this.studentRepository = studentRepository;
        this.courseStudentRepository = courseStudentRepository;
    }

    public static void main(String[] args) {
        applicationContext = SpringApplication.run(RajmanIntApplication.class, args);
        /*
        StudentRepository studentRepository = applicationContext.getBean(StudentRepository.class);
        StudentStatus studentStatus = new StudentStatus(studentRepository);
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        List<StudentThread> threads = new ArrayList<>();
        for (int i = 0; i < 3; i++){
            threads.add(new StudentThread(studentStatus));
            executorService.execute(threads.get(i));
        }
        executorService.shutdown();
         */
    }

/*
    @Scheduled(fixedRate = 600000)
    public void DBStatus(){
        System.out.printf("%d students have registered in this system since now!%n", studentRepository.count());
        List<CourseStudentRepository.CourseStudentSection> average = courseStudentRepository.getCourseStudentsAvg();
        for (CourseStudentRepository.CourseStudentSection courseStudentSection : average) {
            System.out.printf("Student with id %s -> gets %s average.%n", courseStudentSection.getStd(), courseStudentSection.getScr() );
        }
    }

 */
@PostConstruct
public void fillRestDB() {
    List<Student> allStudents = studentRepository.findAll();
    System.out.println(allStudents.size());
}
}
