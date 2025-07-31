package com.spring.boot.test.service;

import com.spring.boot.test.model.Student;

import java.util.List;
import java.util.Optional;

public interface StudentService {

    public Student createStudent(Student student);

    public Optional<Student> getStudentById(Long id);

    public List<Student> getAllStudent();

    public Student updateStudent(Student student);

    public void deleteStudent(Long id);
}
