package com.neshan.rajmanint;

import com.neshan.rajmanint.Entities.Student;
import com.neshan.rajmanint.Repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Iterator;

public class StudentStatus {
    private Iterator<Student> studentIterator;
    private Object stdIteLock = new Object();

    @Autowired
    public StudentStatus(StudentRepository studentRepository) {
        studentIterator = studentRepository.findAll().listIterator();
    }


    public Student getCurrentStd(){
        synchronized (stdIteLock){
            if(studentIterator.hasNext()){
                return studentIterator.next();
            }
        }
        return null;
    }
}