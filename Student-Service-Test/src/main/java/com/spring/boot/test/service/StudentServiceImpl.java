package com.spring.boot.test.service;

import com.spring.boot.test.model.Student;
import com.spring.boot.test.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements  StudentService{

    private StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student createStudent(Student student) {
       Student savedStudent=studentRepository.save(student);
       return savedStudent;
    }

    @Override
    public Optional<Student> getStudentById(Long id) {

       Optional<Student> student= studentRepository.findById(id);
        return student;
    }

    @Override
    public List<Student> getAllStudent() {
       return studentRepository.findAll();

    }

    @Override
    public Student updateStudent( Student student) {
       return studentRepository.save(student);
    }

    @Override
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);

    }
}
