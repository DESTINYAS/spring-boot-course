package com.luv2code.demo.rest;

import com.luv2code.demo.entity.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {
    private List<Student> theStudents;

    //    define @PostConstruct to load the student data...only once!
    @PostConstruct
    public void loadData() {
        theStudents = new ArrayList<>();
        theStudents.add(new Student("Titus", "Prosper"));
        theStudents.add(new Student("Chief", "Nwata"));
        theStudents.add(new Student("Donatus", "Bunkry"));
    }

    //    define endpoint for "/students"-returns list of students
    @GetMapping("/students")
    public List<Student> getTheStudents() {
        return theStudents;
    }
//    define endpoint for "/student/{studentId}" return student by index
    @GetMapping("/student/{studentId}")
    public Student getStudent(@PathVariable int studentId){
//        just index into the list to keep it simple for now

//        Check studentId against the list size
        if(studentId>=theStudents.size() || studentId<0){
            throw new StudentNotFoundException("studentId not found - "+studentId);
        }
        return theStudents.get(studentId);
    }

}