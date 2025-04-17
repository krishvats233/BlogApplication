package com.mongodb.controller;

import com.mongodb.entities.Student;
import com.mongodb.repo.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class MyController
{
    @Autowired
    public StudentRepo studentRepo;

    @PostMapping("/")
    public ResponseEntity<?> addStudent(@RequestBody Student student)
    {
        Student student1=this.studentRepo.save(student);

        return  new ResponseEntity<>(student1,HttpStatus.CREATED);
    }



    @GetMapping("/")
    public ResponseEntity<?> getStudent()
    {
        List<Student> student1=this.studentRepo.findAll();

        return  new ResponseEntity<>(student1,HttpStatus.ACCEPTED);
    }


}
