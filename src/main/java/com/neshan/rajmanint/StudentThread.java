package com.neshan.rajmanint;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.neshan.rajmanint.Entities.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentThread implements Runnable {
    private List<Student> students = new ArrayList<>();
    private StudentStatus studentStatus;

    public StudentThread(StudentStatus studentStatus) {
        this.studentStatus = studentStatus;
    }

    @Override
    public void run() {
        Student currentStd = studentStatus.getCurrentStd();
        while(currentStd != null){
            students.add(currentStd);
            System.out.printf("thread : %s has got the student with stdName: %s%n",
                    Thread.currentThread().getName(),
                    currentStd.getStName());
            currentStd = studentStatus.getCurrentStd();
        }
        ObjectMapper objectMapper = new ObjectMapper();
        String arrayToJson = null;
        try {
            arrayToJson = objectMapper.writeValueAsString(students);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println("1. Convert List of students to JSON :");
        System.out.println(arrayToJson);
    }

    public List<Student> getThreadStudents(){
        return students;
    }
}
