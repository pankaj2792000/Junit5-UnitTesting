package com.spring.boot.test.testcontainers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.boot.test.model.Student;
import com.spring.boot.test.repository.StudentRepository;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class StudentControllerIT extends AbstractContainerBaseTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private StudentRepository studentRepository;

    @BeforeEach
    public  void  setUp(){
        studentRepository.deleteAll();
    }

    @Test
    public void givenStudentObject_whenCreateStudent_thenReturnStudentObject() throws Exception {
            //given-preconditon set up
        Student student=Student.builder()
                .firstName("Pankaj")
                .lastName("Sunda")
                .email("p@gmail.com")
                .build();

            //when -action
       ResultActions response=mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/student")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(student)));
            //then-verify the output

        response.andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName", CoreMatchers.is(student.getFirstName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName", CoreMatchers.is(student.getLastName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email", CoreMatchers.is(student.getEmail())));

        }

    @Test
    public void givenStudentObject_whenGetStudentById_thenReturnStudentObject() throws Exception {
        //given-preconditon set up
        Student student=Student.builder()
                .firstName("Pankaj")
                .lastName("Sunda")
                .email("p@gmail.com")
                .build();
        studentRepository.save(student);
        //when -action
        ResultActions response=mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/student/{studentId}",student.getId()));
        //then-verify the output

        response.andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName", CoreMatchers.is(student.getFirstName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName", CoreMatchers.is(student.getLastName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email", CoreMatchers.is(student.getEmail())));

    }

    @Test
    public void givenStudentObject_whenGetStudentById_thenReturnEmpty() throws Exception {
        //given-preconditon set up
        Long employeeId=1L;
        Student student=Student.builder()
                .firstName("Pankaj")
                .lastName("Sunda")
                .email("p@gmail.com")
                .build();
        studentRepository.save(student);
        //when -action
        ResultActions response=mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/student/{studentId}",employeeId));
        //then-verify the output

        response.andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isNotFound());

    }

    @Test
    public void givenStudentListt_whenGetAllStudent_thenReturnStudentList() throws Exception {
        //given-preconditon set up
        Student student=Student.builder()
                .firstName("Pankaj")
                .lastName("Sunda")
                .email("p@gmail.com")
                .build();
        Student student2=Student.builder()
                .firstName("Mahendra")
                .lastName("Sunda")
                .email("m@gmail.com")
                .build();
        List<Student> studentList=new ArrayList<>();
        studentList.add(student);
        studentList.add(student2);
        studentRepository.saveAll(studentList);
        //when -action
        ResultActions response=mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/student"));
        //then-verify the output

        response.andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].firstName", CoreMatchers.is(student.getFirstName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].lastName", CoreMatchers.is(student.getLastName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].email", CoreMatchers.is(student.getEmail())));

    }

    @Test
    public void givenStudentObject_whenUpdateStudent_thenReturnUpdatedStudent() throws Exception {
        //given-preconditon set up
        Student student=Student.builder()
                .firstName("Pankaj")
                .lastName("Sunda")
                .email("p@gmail.com")
                .build();
        Student updatedStudent=Student.builder()
                .firstName("Mahendra")
                .lastName("Sunda")
                .email("m@gmail.com")
                .build();
        studentRepository.save(student);
        //when -action
        ResultActions response=mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/student/{studentId}",student.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedStudent)));
        //then-verify the output

        response.andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName", CoreMatchers.is(updatedStudent.getFirstName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName", CoreMatchers.is(updatedStudent.getLastName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email", CoreMatchers.is(updatedStudent.getEmail())));

    }

    @Test
    public void givenStudentObject_whenUpdateStudent_thenReturnEmptyStudent() throws Exception {
        //given-preconditon set up
        Long studentId=1L;
        Student student=Student.builder()
                .firstName("Pankaj")
                .lastName("Sunda")
                .email("p@gmail.com")
                .build();
        Student updatedStudent=Student.builder()
                .firstName("Mahendra")
                .lastName("Sunda")
                .email("m@gmail.com")
                .build();
        studentRepository.save(student);
        //when -action
        ResultActions response=mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/student/{studentId}",studentId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedStudent)));
        //then-verify the output

        response.andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isNotFound());

    }

    @Test
    public void givenStudentId_whenDeletdStudent_thenReturnEmptySuccess() throws Exception {
        //given-preconditon set up
        Student student=Student.builder()
                .firstName("Pankaj")
                .lastName("Sunda")
                .email("p@gmail.com")
                .build();
        studentRepository.save(student);
        //when -action
        ResultActions response=mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/student/{studentId}",student.getId())
                .contentType(MediaType.APPLICATION_JSON));
        //then-verify the output

        response.andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());

    }


}
