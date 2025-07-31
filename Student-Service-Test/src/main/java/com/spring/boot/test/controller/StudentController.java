package com.spring.boot.test.controller;

import com.spring.boot.test.model.Student;
import com.spring.boot.test.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/student")
public class StudentController {

    private StudentService service;
    public StudentController(StudentService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Student saveStudent(@RequestBody  Student student){
        return service.createStudent(student);
    }
    @GetMapping
    public List<Student> getAllStudent(){
        return service.getAllStudent();
    }

    @GetMapping("{studentId}")
    public ResponseEntity<Student> getStudent(@PathVariable("studentId") Long id){

   return service
        .getStudentById(id)
        .map(ResponseEntity::ok)
        .orElseGet(()-> ResponseEntity.notFound().build());

    }
    @PutMapping("{studentId}")
    public  ResponseEntity<Student> updateStudent(@PathVariable("studentId") Long id ,@RequestBody Student student){
     return service.getStudentById(id).map(student1 -> {
         student1.setFirstName(student.getFirstName());
         student1.setLastName(student.getLastName());
         student1.setEmail(student.getEmail());
         service.updateStudent(student1);
         return ResponseEntity.ok().body(student1);
     }).orElseGet(()->ResponseEntity.notFound().build());
    }

    @DeleteMapping("{studentId}")
    public void deleteStudent(@PathVariable("studentId") Long studentId){
        service.deleteStudent(studentId);
    }




}
