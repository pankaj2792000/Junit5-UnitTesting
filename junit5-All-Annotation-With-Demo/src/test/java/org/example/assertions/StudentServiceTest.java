package org.example.assertions;

import org.example.Student;
import org.example.StudentNotFoundException;
import org.example.StudentService;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StudentServiceTest {

    @Test
    public void getStudentsTest(){
        StudentService service=new StudentService();
        //Student student=new Student("RAMESH",1);
       // service.addStudents(student);
       List<Student>students= service.getStudents();
       boolean actualValue=students.isEmpty();
//       assertTrue(actualValue);
        //assertTrue(()->actualValue);
        //assertTrue(!actualValue,"list of student is Empty");
        //assertTrue(()-> actualValue,"list of student is Empty");
       // assertTrue(actualValue,()-> "list of student is Empty");
//        assertTrue(()->!actualValue,()->"list of student is Empty");
        assertTrue(()->actualValue,()->"list of student is Empty");
    }

    @Test
    public void getStudentsTestUsingAssertFalse(){
        StudentService service=new StudentService();
        Student student=new Student("RAMESH",1);
         service.addStudents(student);
        List<Student>students= service.getStudents();
        boolean actualValue=students.isEmpty();
        //assertFalse(actualValue);
        //assertFalse(()->!actualValue);
        //assertFalse(!actualValue,"List is empty");
       // assertFalse(()->!actualValue,"List is Empty");
       // assertFalse(!actualValue,()->"List is Empty");
        assertFalse(()->actualValue,()->"List is Empty");


        //assertTrue(()->actualValue);
        //assertTrue(!actualValue,"list of student is Empty");
        //assertTrue(()-> actualValue,"list of student is Empty");
        // assertTrue(actualValue,()-> "list of student is Empty");
//        assertTrue(()->!actualValue,()->"list of student is Empty");
       // assertTrue(()->actualValue,()->"list of student is Empty");
    }

    @Test
    public void getStudentsTestUsingAssertNull(){
        StudentService service=new StudentService();
        Student student=new Student("RAMESH",1);
        service.addStudents(student);

        Student actualValue=service.getStudentById(2);

       // assertNull(actualValue);
      //  assertNull(actualValue,"Student is not present");
        assertNull(actualValue,()->"Student object is not null");

    }

    @Test
    public void getStudentsTestUsingAssertNotNull(){
        StudentService service=new StudentService();
        Student student=new Student("RAMESH",1);
        service.addStudents(student);

        Student actualValue=service.getStudentById(2);

        //assertNotNull(actualValue);
       // assertNotNull(actualValue,"Student object is  null");
        assertNotNull(actualValue,()->"Student object is null");

    }

    @Test
    public void getStudentsTestUsingAssertEquals(){
        StudentService service=new StudentService();
        Student student=new Student("RAMESH",1);
        service.addStudents(student);

        Student actualValue=service.getStudentById(2);



//        assertEquals(student,actualValue);
//        assertEquals(student.getId(),actualValue.getId());
//        assertEquals(student.getName(),actualValue.getName());

       // assertEquals(student,actualValue,"student object is not equal");
//        assertEquals(student.getId(),actualValue.getId(),"student id is not equal");
//        assertEquals(student.getName(),actualValue.getName(),"student name is not equal");
        assertEquals(student,actualValue,()->"student object is not equal");

    }

    @Test
    public void getStudentsTestUsingAssertNotEquals(){
        StudentService service=new StudentService();
        Student student=new Student("RAMESH",1);
        service.addStudents(student);

        Student actualValue=service.getStudentById(1);


        //assertNotEquals(student,actualValue);
       // assertNotEquals(student.getId(),actualValue.getId());
     //  assertNotEquals(student.getName(),actualValue.getName());

        //assertNotEquals(student,actualValue,"Objects are equal");
        assertNotEquals(student,actualValue,()->"Objects are equal");

    }
    @Test
    public void getStudentsNameTestUsingAssertArrayEquals(){
        StudentService service=new StudentService();
        Student student1=new Student("RAMESH",1,"AB");
        Student student2=new Student("RAM",2,"AB");
        Student student3=new Student("R",3,"BC");
        service.addStudents(student1);
        service.addStudents(student2);
        service.addStudents(student3);

//     String [] names= service.getStudentsNameByDepartment("AB");
//     String [] expectedArray={"RAMESH","RAM"};
//
//      assertArrayEquals(expectedArray,names);
//        assertArrayEquals(expectedArray,names,"both are not equal");
//        assertArrayEquals(expectedArray,names,()->"both are not equal");

//        names= service.getStudentsNameByDepartment("BC");
//        assertArrayEquals(expectedArray,names);
//        assertArrayEquals(expectedArray,names,"both are not equal");
//        assertArrayEquals(expectedArray,names,()->"both are not equal");

        Integer [] expected={1,2};
        Integer [] actual=service.getStudentsIdByDepartment("AB");
        assertArrayEquals(expected,actual);



    }

    @Test
    public void getStudentsNameListTestUsingAssertIterableEquals(){
        StudentService service=new StudentService();
        Student student1=new Student("RAMESH",1,"AB");
        Student student2=new Student("RAM",2,"AB");
        Student student3=new Student("R",3,"BC");
        service.addStudents(student1);
        service.addStudents(student2);
        service.addStudents(student3);


      List<String> actualNameList=  service.getStudentsNameListByDepartment("AB");
      //List<String> expectedNameList=List.of("RAMESH","RAM");
        List<String> expectedNameList= Arrays.asList("RAMESH","RAM");

        assertIterableEquals(actualNameList,expectedNameList);
        assertIterableEquals(actualNameList,expectedNameList,"names are not equal");
        assertIterableEquals(actualNameList,expectedNameList,()->"names are not equal");

        expectedNameList= Arrays.asList("RAMESH");
        assertIterableEquals(actualNameList,expectedNameList);
        assertIterableEquals(actualNameList,expectedNameList,"names are not equal");
        assertIterableEquals(actualNameList,expectedNameList,()->"names are not equal");

    }

    @Test
  public  void getStudentByNameTestUsingAssertThrows() {
        StudentService service=new StudentService();
        Student student1=new Student("RAMESH",1,"AB");
        Student student2=new Student("RAM",2,"AB");
        Student student3=new Student("R",3,"BC");
        service.addStudents(student1);
        service.addStudents(student2);
        service.addStudents(student3);


//     assertThrows(RuntimeException.class,()->{
//         service.getStudentByName("RAMESH");
//     });

//        assertThrows(RuntimeException.class,()->{
//            service.getStudentByName("RA");
//        });

//        assertThrows(RuntimeException.class,()->{
//            service.getStudentByName("RAMESH");
//        },"Student is available");

//        assertThrows(RuntimeException.class,()->{
//            service.getStudentByName("RAMESH");
//        },()->"Student is available");


//        assertThrows(NullPointerException.class,()->{
//            service.getStudentByName("RA");
//        },()->"Student is available");

        assertThrows(StudentNotFoundException.class,()->{
            service.getStudentByName("RA");
        },()->"Student is available");
    }

    @Test
    public  void getStudentByNameTestUsingAssertThrowsExactly() {
        StudentService service=new StudentService();
        Student student1=new Student("RAMESH",1,"AB");
        Student student2=new Student("RAM",2,"AB");
        Student student3=new Student("R",3,"BC");
        service.addStudents(student1);
        service.addStudents(student2);
        service.addStudents(student3);

//        assertThrowsExactly(StudentNotFoundException.class,
//                ()->service.getStudentByName("kamal"));
//Fail
//        assertThrowsExactly(RuntimeException.class,
//                ()->service.getStudentByName("kamal"));

//       assertThrowsExactly(StudentNotFoundException.class,
//                ()->service.getStudentByName("kamal"),"expected StudentNotFoundException");

//        assertThrowsExactly(RuntimeException.class,
//                ()->service.getStudentByName("kamal"),"expected RunTimeException");

//        assertThrowsExactly(RuntimeException.class,
//                ()->service.getStudentByName("kamal"),()->"expected RunTimeException");

       StudentNotFoundException exception= assertThrowsExactly(StudentNotFoundException.class,
                ()->service.getStudentByName("kamal"),"expected StudentNotFoundException");

       assertEquals("Student is Not available",exception.getMessage());

    }
}