package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StudentService {

    private List<Student> students;

    public List<Student> getStudents(){
        if(students==null){
            students=new ArrayList<>();
        }
        return this.students;
    }

    public void addStudents(Student student){
       if(students==null){
           students=new ArrayList<>();
       }
       students.add(student);
    }

    public Student getStudentById(int studentId){
      return  students.stream()
                .filter(student -> student.getId()==studentId)
                .findFirst()
                .orElse(null);
    }
    public String [] getStudentsNameByDepartment(String department){
       return students.stream()
                .filter(student -> student.getDepartment().equals(department))
                .map(student -> student.getName())
                .toArray( String[]::new);


    }
    public Integer [] getStudentsIdByDepartment(String department){
        return students.stream()
                .filter(student -> student.getDepartment().equals( department))
                .map(student -> student.getId())
                .toArray( Integer[]::new);


    }

    public List<String> getStudentsNameListByDepartment(String department){
        return students.stream()
                .filter(student -> student.getDepartment().equals(department))
                .map(student -> student.getName())
                .collect(Collectors.toList());


    }
    public List<Integer> getStudentsIdListByDepartment(String department){
        return students.stream()
                .filter(student -> student.getDepartment().equals( department))
                .map(Student::getId)
                .collect(Collectors.toList());


    }
    public  Student getStudentByName(String name){
       return students.stream()
                .filter(student -> student.getName().equals(name))
                .findFirst()
                .orElseThrow(()->new StudentNotFoundException("Student is Not available"));
    }
}
