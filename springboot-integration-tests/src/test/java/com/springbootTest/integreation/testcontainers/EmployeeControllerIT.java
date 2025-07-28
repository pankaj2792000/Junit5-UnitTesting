package com.springbootTest.integreation.testcontainers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springbootTest.model.Employee;
import com.springbootTest.repository.EmployeeRepository;
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
@AutoConfigureMockMvc// IT USED TO AUTOCONFIGURE THE MockMvc class object
//@Testcontainers  //--> its only needed when we are using mysql container inside this class to manaqge the lifecycle
public class EmployeeControllerIT extends AbstractionContainerBaseTest {
    //create mysql container as we are using that db only

//    @Container
//    private static  final MySQLContainer mySQLContainer=new MySQLContainer("mysql:latest")
//            .withUsername("root")
//            .withPassword("root")
//            .withDatabaseName("ems");

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        //it will all the data from table before each test case
        employeeRepository.deleteAll();
    }
//    @DynamicPropertySource
//    public  void dynamicPropertySource(DynamicPropertyRegistry registry){
//        registry.add("spring.datasource.url",mySQLContainer::getJdbcUrl);
//        registry.add("spring.datasource.username",mySQLContainer::getUsername);
//        registry.add("spring.datasource.password",mySQLContainer::getPassword);
//
//
//    }


    @Test
    public void givenEmployeeObject_whenCreateEmployee_thenReturnSavedEmployeeObject() throws Exception {
        //given-preconditon set up
        Employee employee = Employee.builder()
                .firstName("pankaj")
                .lastName("sunda")
                .email("p@gmail.com")
                .build();

        //no need to mock anything

        //when -action
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders
                .post("/api/employee")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(employee)));

        //then-verify the output
        response.andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName", CoreMatchers.is(employee.getFirstName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName", CoreMatchers.is(employee.getLastName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email", CoreMatchers.is(employee.getEmail())));
    }

    @Test
    public void givenEmployeeList_whenGetAllEmployee_thenReturnEmployeeList() throws Exception {
        //given-preconditon set up
        List<Employee> list = new ArrayList<>();
        Employee employee1 = Employee.builder()
                .firstName("pankaj")
                .lastName("sunda")
                .email("p@gmail.com")
                .build();
        Employee employee2 = Employee.builder()
                .firstName("mahendra")
                .lastName("sunda")
                .email("m@gmail.com")
                .build();
        list.add(employee1);
        list.add(employee2);
        //here we employeeRepository class to save the records
        employeeRepository.saveAll(list);

        //when -action

        ResultActions resonse = mockMvc.perform(MockMvcRequestBuilders.get("/api/employee"));

        //then-verify the output
        resonse.andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()", CoreMatchers.is(list.size())));

    }

    @Test
    public void givenEmployeeID_whenGetEmployeeById_thenReturnEmployeeObject() throws Exception {
        //given-preconditon set up
        //Long employeeId=4L;
        Employee employee = Employee.builder()
                .firstName("pankaj")
                .lastName("sunda")
                .email("p@gmail.com")
                .build();

        employeeRepository.save(employee);

        //when -action
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.get("/api/employee/{employeeId}", employee.getId()));


        //then-verify the output
        response.andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName", CoreMatchers.is(employee.getFirstName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName", CoreMatchers.is(employee.getLastName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email", CoreMatchers.is(employee.getEmail())));

    }

    @Test
    public void givenEmployeeID_whenGetEmployeeById_thenReturnEmpty() throws Exception {
        //given-preconditon set up
        Long employeeId = 4L;
        Employee employee = Employee.builder()
                .firstName("pankaj")
                .lastName("sunda")
                .email("p@gmail.com")
                .build();

        employeeRepository.save(employee);

        //when -action
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.get("/api/employee/{employeeId}", employeeId));


        //then-verify the output
        response.andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isNotFound());

    }

    @Test
    public void givenUpdatedEmployee_whenUpdateEmployee_thenReturnUpdatedEmployee() throws Exception {
        //given-preconditon set up
        Employee employee = Employee.builder()
                .firstName("pankaj")
                .lastName("sunda")
                .email("p@gmail.com")
                .build();
        employeeRepository.save(employee);
        Employee updatedEmployee = Employee.builder()
                .firstName("mahendra")
                .lastName("sunda")
                .email("m@gmail.com")
                .build();
        //when -action
        ResultActions resultActions =
                mockMvc.perform(MockMvcRequestBuilders.put("/api/employee/{employeeId}", employee.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(updatedEmployee)));

        //then-verify the output
        resultActions.andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName", CoreMatchers.is(updatedEmployee.getFirstName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName", CoreMatchers.is(updatedEmployee.getLastName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email", CoreMatchers.is(updatedEmployee.getEmail())));


    }

    @Test
    public void givenUpdatedEmployee_whenUpdateEmployee_thenReturnEmpty() throws Exception {
        Long employeeId = 2L;
        Employee employee = Employee.builder()
                .firstName("pankaj")
                .lastName("sunda")
                .email("p@gmail.com")
                .build();
        employeeRepository.save(employee);
        Employee updatedEmployee = Employee.builder()
                .firstName("mahendra")
                .lastName("sunda")
                .email("m@gmail.com")
                .build();
        //when -action
        ResultActions resultActions =
                mockMvc.perform(MockMvcRequestBuilders.put("/api/employee/{employeeId}", employeeId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(updatedEmployee)));

        //then-verify the output
        resultActions.andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isNotFound());

    }

    @Test
    public void givenEmployeeId_whenDeleteEmployee_thenReturnSuccess() throws Exception {
        //given-preconditon set up
        Employee employee = Employee.builder()
                .firstName("pankaj")
                .lastName("sunda")
                .email("p@gmail.com")
                .build();
        employeeRepository.save(employee);
        //when -action
        ResultActions respoonse = mockMvc.perform(MockMvcRequestBuilders.delete("/api/employee/{employeeId}", employee.getId()));

        //then-verify the output
        respoonse.andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());

    }


}
