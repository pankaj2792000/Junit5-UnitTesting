package com.springbootTest.service;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.springbootTest.exception.ResourceNotFoundException;
import com.springbootTest.model.Employee;
import com.springbootTest.repository.EmployeeRepository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

//to tell spring that we are using mockito annotation we need to below extension on class level
@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTests {


    @Mock
    private EmployeeRepository employeeRepository;

    //employee service cretates own actual object shown in constructoro and add the mock object of EmployeeRepository class
    @InjectMocks
    private EmployeeServiceImpl employeeService;

    private Employee employee;

    @BeforeEach
    public void setup() {
//        employeeRepository= Mockito.mock(EmployeeRepository.class);
//        employeeService=new EmployeeServiceImpl(employeeRepository);
        employee = Employee.builder()
                .id(1)
                .firstName("Pankaj")
                .lastName("Sunda")
                .email("p@gmail.com")
                .build();
    }

    @Test
    public void givenEmployeeObject_whenSaveEmployee_thenReturnEmployeeObject() {
        //given-preconditon set up
        //providng stubbing method
        //wherever in save employee method we are calling method using employeeRepository object we need to stub it

//stubbing
        given(employeeRepository.findByEmail(employee.getEmail())).willReturn(Optional.empty());
        given(employeeRepository.save(employee)).willReturn(employee);

        //checkng its mocked and not null
        System.out.println(employeeRepository);
        System.out.println(employeeService);


        //when -action

        //now actually test employee test method

        //it should return the primary key also so we need to add id in employee object
        Employee savedEmployee = employeeService.saveEmployee(employee);
        System.out.println(savedEmployee);

        //then-verify the output
        assertThat(savedEmployee).isNotNull();
        assertThat(savedEmployee.getId()).isEqualTo(1);
    }

    @Test
    public void givenExistingEmail_whenSaveEmployee_thenThrowsResourceNotFoundException() {
        //given-preconditon set up

        given(employeeRepository.findByEmail(employee.getEmail())).willReturn(Optional.of(employee));
        //below is unnecessary stubbing its not required beacuse control never reached to this call , if we add
        //this test case will fail
        //given(employeeRepository.save(employee)).willReturn(employee);

        //when -action
        Assertions.assertThrows(ResourceNotFoundException.class, () -> employeeService.saveEmployee(employee));

        //then-verify the outputa

        //we are verifying after exception throws,control should not go to next statment means employeeRepository.save()
        verify(employeeRepository, never()).save(any(Employee.class));

    }

    @Test
    public void givenEmployeeList_whenGetAllEmployees_thenReturnEmployeeList() {
        //given-preconditon set up
        Employee employee1 = Employee.builder()
                .id(2)
                .firstName("Mahendra")
                .lastName("Sunda")
                .email("m@gmail.com")
                .build();
        given(employeeRepository.findAll()).willReturn(List.of(employee1, employee));
        //when -action
        List<Employee> employeeList = employeeService.getAllEmployees();


        //then-verify the output
        assertThat(employeeList).isNotNull();
        assertThat(employeeList.size()).isEqualTo(2);

    }


    @Test
    public void givenEmployeeList_whenGetAllEmployees_thenReturnEmptyEmployeeList() {
        //given-preconditon set up

        given(employeeRepository.findAll()).willReturn(Collections.EMPTY_LIST);
        //when -action
        List<Employee> employeeList = employeeService.getAllEmployees();


        //then-verify the output
        assertThat(employeeList).isNotNull();
        assertThat(employeeList.size()).isEqualTo(0);

    }

    @Test
    public void givenEmployeeId_whenGetEmployeeById_thenReturnEmployee() {
        //given-preconditon set up

        given(employeeRepository.findById(employee.getId())).willReturn(Optional.of(employee));

        //when -action
        Employee employee1 = employeeService.getEmployeeById(employee.getId()).get();


        //then-verify the output
        assertThat(employee1).isNotNull();
        assertThat(employee1.getId()).isEqualTo(employee.getId());

    }

    @Test
    public void givenEmployeeObject_whenUpdate_thenReturnUpdatedEmployee() {
        //given-preconditon set up
        given(employeeRepository.save(employee)).willReturn(employee);
        employee.setFirstName("Mahendra");
        employee.setEmail("m@gmail.com");

        //when -action
        Employee updatedEmployee = employeeService.updateEmployee(employee);


        //then-verify the output
        assertThat(updatedEmployee).isNotNull();
        assertThat(updatedEmployee.getFirstName()).isEqualTo("Mahendra");
        assertThat(updatedEmployee.getEmail()).isEqualTo("m@gmail.com");


    }

    @Test
    public void givenEmployeeId_whenDeleteEmployee_thenReturnNothing() {
        //given-preconditon set up
        Long employeeId = 1L;
        willDoNothing().given(employeeRepository).deleteById(employeeId);


        //when -action
        employeeService.deleteEmployee(employeeId);


        //then-verify the output
        //how many times employeeRepository.deleteById() is called

        verify(employeeRepository, times(1)).deleteById(employeeId);

    }

}
