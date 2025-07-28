package com.springbootTest.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springbootTest.model.Employee;
import com.springbootTest.service.EmployeeService;
import com.springbootTest.service.EmployeeServiceImpl;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.assertj.MockMvcTester;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.awaitility.Awaitility.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest
public class EmployeeControllerTests {

    @Autowired
    MockMvc mockMvc;//to call rest apis

    //if we use mock then it wont register in application context we wont able to use
    /// beacuse we are creating object of employee controller here
    /// beacuse its a controller we are not going to call anything from controller object
    /// even the constructor wont work here which we define below
    @MockitoBean
    private EmployeeService employeeService;

    private EmployeeController employeeController;

    @Autowired
    private ObjectMapper objectMapper;

//    public EmployeeControllerTests(){
//        employeeService= Mockito.mock(EmployeeServiceImpl.class);
//        employeeController=new EmployeeController(employeeService);
//
//    }


    @Test
        public void givenEmployeeObject_whenCreateEmployee_thenReturnSavedEmployeeObject() throws Exception {
            //given-preconditon set up

        Employee employee=Employee.builder()
                .firstName("Pankaj")
                .lastName("Sunda")
                .email("p@gmail.com")
                .build();
//mock the behaviour of service class
        //when it will receive any Employee class object it will return then argument
        //in createEmployee we are passing only one argument so will get zero index object
        BDDMockito.given(employeeService.saveEmployee(ArgumentMatchers.any(Employee.class)))
                .willAnswer(invocationOnMock -> invocationOnMock.getArgument(0));

            //when -action
        //call rest apis using mock mvc methods
// ResultActions response =mockMvc.perform(MockMvcRequestBuilders.post("/api/employee")
//           .contentType(MediaType.APPLICATION_JSON)
//           .content(objectMapper.writeValueAsString(employee)));

        ResultActions response =mockMvc.perform(post("/api/employee")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(employee)));
            //then-verify the output
        //$ - ROOT OBJECT MEANS WHOLE JSON
        response
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName",
                        CoreMatchers.is(employee.getFirstName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName",
                        CoreMatchers.is(employee.getLastName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email",
                        CoreMatchers.is(employee.getEmail())));


        }
            @Test
            public void givenEmployeeList_whenGetAllEmployee_thenReturnEmployeeList() throws Exception {
                //given-preconditon set up
            Employee employee=Employee.builder()
                    .id(0)
                    .firstName("Pankaj")
                    .lastName("Sunda")
                    .email("p@gmail.com")
                    .build();
            Employee employee1=Employee.builder()
                    .id(1)
                    .firstName("Mahendra")
                    .lastName("Sunda")
                    .email("m@gmail.com")
                    .build();

            //m-1
           // BDDMockito.given(employeeService.getAllEmployees()).willReturn(List.of(employee1,employee));

            //m-2
            List<Employee> list=new ArrayList<>();
            list.add(employee1);
            list.add(employee);
            BDDMockito.given(employeeService.getAllEmployees()).willReturn(list);
                //when -action

           ResultActions response=mockMvc.perform(
                get("/api/employee")
                            .contentType(MediaType.APPLICATION_JSON)
            );
                //then-verify the output
            response.andDo(MockMvcResultHandlers.print())
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(MockMvcResultMatchers.jsonPath("$.size()",CoreMatchers.is(list.size())))
                    .andExpect(MockMvcResultMatchers.
                            jsonPath("$[0].firstName",CoreMatchers.is(employee1.getFirstName())));

            }

            @Test
                public void givenEmployeeID_whenGetEmployeeById_thenReturnEmployeeObject() throws Exception{
                    //given-preconditon set up
                Long employeeId=0L;
                Employee employee=Employee.builder()
                        .id(0)
                        .firstName("Pankaj")
                        .lastName("Sunda")
                        .email("p@gmail.com")
                        .build();

               BDDMockito.given(employeeService.getEmployeeById(employeeId)).willReturn(Optional.ofNullable(employee));
                    //when -action

                ResultActions response=mockMvc.perform(get("/api/employee/{employeeId}",employeeId)
                        .contentType(MediaType.APPLICATION_JSON)
                );

                    //then-verify the output
                response.andDo(MockMvcResultHandlers.print())
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andExpect(MockMvcResultMatchers.jsonPath("$.firstName",CoreMatchers.is(employee.getFirstName())))
                        .andExpect(MockMvcResultMatchers.jsonPath("$.lastName",
                                CoreMatchers.is(employee.getLastName())))
                        .andExpect(MockMvcResultMatchers.jsonPath("$.email",
                                CoreMatchers.is(employee.getEmail())));



                }

    @Test
    public void givenEmployeeID_whenGetEmployeeById_thenReturnEmpty() throws Exception{
        //given-preconditon set up
        Long employeeId=1L;
        Employee employee=Employee.builder()
                .id(0)
                .firstName("Pankaj")
                .lastName("Sunda")
                .email("p@gmail.com")
                .build();

        BDDMockito.given(employeeService.getEmployeeById(employeeId)).willReturn(Optional.empty());
        //when -action

        ResultActions response=mockMvc.perform(get("/api/employee/{employeeId}",employeeId)
                .contentType(MediaType.APPLICATION_JSON)
        );

        //then-verify the output
        response.andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isNotFound());

    }

    @Test
        public void givenUpdatedEmployee_whenUpdateEmployee_thenReturnUpdatedEmployee() throws Exception {
            //given-preconditon set up
        Long employeeId=0L;
        Employee savedEmployee=Employee.builder()
                .id(0)
                .firstName("Pankaj")
                .lastName("Sunda")
                .email("p@gmail.com")
                .build();
        Employee updatedEmployee=Employee.builder()
                .id(0)
                .firstName("Mahendra")
                .lastName("Sunda")
                .email("m@gmail.com")
                .build();

        BDDMockito.given(employeeService.getEmployeeById(employeeId)).willReturn(Optional.of(savedEmployee));
        //below is not working
       // BDDMockito.given(employeeService.updateEmployee(updatedEmployee)).willReturn(updatedEmployee);
        BDDMockito.given(employeeService.updateEmployee(ArgumentMatchers.any(Employee.class)))
                .willAnswer(invocationOnMock -> invocationOnMock.getArgument(0));

            //when -action
        ResultActions response=mockMvc.perform(put("/api/employee/{employeeId}",employeeId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedEmployee)));

            //then-verify the output
        response.andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName",CoreMatchers.is(updatedEmployee.getFirstName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName",
                        CoreMatchers.is(updatedEmployee.getLastName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email",
                        CoreMatchers.is(updatedEmployee.getEmail())));

    }

    @Test
    public void givenUpdatedEmployee_whenUpdateEmployee_thenReturnEmpty() throws Exception {
        //given-preconditon set up
        Long employeeId=0L;
        Employee savedEmployee=Employee.builder()
                .id(0)
                .firstName("Pankaj")
                .lastName("Sunda")
                .email("p@gmail.com")
                .build();
        Employee updatedEmployee=Employee.builder()
                .id(0)
                .firstName("Mahendra")
                .lastName("Sunda")
                .email("m@gmail.com")
                .build();

        BDDMockito.given(employeeService.getEmployeeById(employeeId)).willReturn(Optional.empty());
        //below is not working
        // BDDMockito.given(employeeService.updateEmployee(updatedEmployee)).willReturn(updatedEmployee);
        BDDMockito.given(employeeService.updateEmployee(ArgumentMatchers.any(Employee.class)))
                .willAnswer(invocationOnMock -> invocationOnMock.getArgument(0));

        //when -action
        ResultActions response=mockMvc.perform(put("/api/employee/{employeeId}",employeeId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedEmployee)));

        //then-verify the output
        response.andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isNotFound());

    }
    @Test
        public void givenEmployeeId_whenDeleteEmployee_thenReturnSuccess() throws Exception {
            //given-preconditon set up
        Long employeeId=0L;
        //return void
        //m-1
      ///  BDDMockito.doNothing().when(employeeService).deleteEmployee(employeeId);

        //M-2
        BDDMockito.willDoNothing().given(employeeService).deleteEmployee(employeeId);

            //when -action
       ResultActions response= mockMvc.perform(delete("/api/employee/{employeeId}",employeeId));

            //then-verify the output
        response.andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());

        }


}
