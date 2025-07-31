package com.springbootTest.repository;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.springbootTest.model.Employee;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

@DataJpaTest
public class EmployeeRepositoryTests {

    @Autowired
    private EmployeeRepository employeeRepository;
    private Employee employee1;

@BeforeEach
public void setUp(){
    employee1 = Employee.builder()
            .firstName("Pankaj")
            .lastName("Sunda")
            .email("p@gmail.com")
            .build();
}
    @Test
    public void givenEmployeeObject_whenSave_thenReturnSavedEmployee() {

        //given-preconditon set up
//        Employee employee = Employee.builder()
//                .firstName("Pankaj")
//                .lastName("Sunda")
//                .email("p@gmail.com")
//                .build();

        //when -action
        Employee savedEmployee = employeeRepository.save(employee1);

        //then-verify the output
        assertThat(savedEmployee).isNotNull();
        assertThat(savedEmployee.getId()).isGreaterThan(0);


    }

    @Test
    public void givenEmployeesList_whenFindAll_thenReturnEmployeeList() {
        //given-preconditon set up
//        Employee employee1 = Employee.builder()
//                .firstName("Pankaj")
//                .lastName("Sunda")
//                .email("p@gmail.com")
//                .build();

        Employee employee2 = Employee.builder()
                .firstName("mahendra")
                .lastName("Sunda")
                .email("m@gmail.com")
                .build();

        Employee employee3 = Employee.builder()
                .firstName("deepak")
                .lastName("Sunda")
                .email("d@gmail.com")
                .build();
        employeeRepository.save(employee1);
        employeeRepository.save(employee2);
        employeeRepository.save(employee3);


        //when -action

        List<Employee> employeeList = employeeRepository.findAll();


        //then-verify the output
        assertThat(employeeList).isNotNull();
        assertThat(employeeList.size()).isEqualTo(3);

    }

    @Test
    public void givenEmployeeList_whenFindById_thenReturnEmployeeObject() {
        //given-preconditon set up

//        Employee employee1 = Employee.builder()
//                .firstName("Pankaj")
//                .lastName("Sunda")
//                .email("p@gmail.com")
//                .build();

        Employee employee2 = Employee.builder()
                .firstName("mahendra")
                .lastName("Sunda")
                .email("m@gmail.com")
                .build();

        Employee employee3 = Employee.builder()
                .firstName("deepak")
                .lastName("Sunda")
                .email("d@gmail.com")
                .build();
        employeeRepository.save(employee1);
        employeeRepository.save(employee2);
        employeeRepository.save(employee3);

        //when -action
        Optional<Employee> employee = employeeRepository.findById(employee1.getId());

        //then-verify the output
        assertThat(employee.get()).isNotNull();
        assertThat(employee.get().getId()).isEqualTo(1);

    }

    @Test
    public void givenEmployeeObject_whenFindByEmail_thenReturnEmployeeObject() {
        //given-preconditon set up
//        Employee employee1 = Employee.builder()
//                .firstName("Pankaj")
//                .lastName("Sunda")
//                .email("p@gmail.com")
//                .build();
        employeeRepository.save(employee1);

        //when -action
        Optional<Employee> employee = employeeRepository.findByEmail(employee1.getEmail());

        //then-verify the output
        assertThat(employee.get()).isNotNull();
        assertThat(employee.get().getEmail()).isEqualTo(employee1.getEmail());

    }

    @Test
    public void givenEmployeeObject_whenUpdateEmployee_thenReturnUpdateEmployee() {
        //given-preconditon set up
//        Employee employee1 = Employee.builder()
//                .firstName("Pankaj")
//                .lastName("Sunda")
//                .email("p@gmail.com")
//                .build();
        employeeRepository.save(employee1);


        //when -action
        Employee employee = employeeRepository.findById(employee1.getId()).get();
        employee.setFirstName("Dibesh");
        employee.setLastName("Sharma");

        Employee updatedEmployee = employeeRepository.save(employee);
        //then-verify the output
        assertThat(updatedEmployee).isNotNull();
        assertThat(updatedEmployee.getFirstName()).isEqualTo("Dibesh");
        assertThat(updatedEmployee.getLastName()).isEqualTo("Sharma");
    }
    @Test
        public void givenEmployeeObject_whenDeleteById_thenReturnDeletedEmployee(){
            //given-preconditon set up
//        Employee employee1 = Employee.builder()
//                .firstName("Pankaj")
//                .lastName("Sunda")
//                .email("p@gmail.com")
//                .build();
        employeeRepository.save(employee1);

            //when -action
     employeeRepository.deleteById(employee1.getId());
     Optional<Employee> deletedEmployee=employeeRepository.findById(employee1.getId());
            //then-verify the output
        assertThat(deletedEmployee.isEmpty()).isTrue();

        }
        @Test
            public void givenEmployeeObject_whenfindByJPQLQuery_thenReturnEmployeeObject(){
                //given-preconditon set up
//            Employee employee1 = Employee.builder()
//                    .firstName("Pankaj")
//                    .lastName("Sunda")
//                    .email("p@gmail.com")
//                    .build();
            employeeRepository.save(employee1);

                //when -action
            String firstName="Pankaj";
            String lastName="Sunda";
            Employee employee=employeeRepository.findByJPQLQuery(firstName,lastName);

                //then-verify the output
            assertThat(employee).isNotNull();
            assertThat(employee.getFirstName()).isEqualTo("Pankaj");
            assertThat(employee.getLastName()).isEqualTo("Sunda");


            }

    @Test
    public void givenEmployeeObject_whenfindByJPQLQNamedParams_thenReturnEmployeeObject(){
        //given-preconditon set up
//        Employee employee1 = Employee.builder()
//                .firstName("Pankaj")
//                .lastName("Sunda")
//                .email("p@gmail.com")
//                .build();
        employeeRepository.save(employee1);

        //when -action
        String firstName="Pankaj";
        String lastName="Sunda";
        Employee employee=employeeRepository.findByJPQLQNamedParams(firstName,lastName);

        //then-verify the output
        assertThat(employee).isNotNull();
        assertThat(employee.getFirstName()).isEqualTo("Pankaj");
        assertThat(employee.getLastName()).isEqualTo("Sunda");


    }
    @Test
    public void givenEmployeeObject_whenfindByNativeSqlIndexing_thenReturnEmployeeObject(){
        //given-preconditon set up
//        Employee employee1 = Employee.builder()
//                .firstName("Pankaj")
//                .lastName("Sunda")
//                .email("p@gmail.com")
//                .build();
        employeeRepository.save(employee1);

        //when -action
        String firstName="Pankaj";
        String lastName="Sunda";
        Employee employee=employeeRepository.findByNativeSqlWithIndex(firstName,lastName);

        //then-verify the output
        assertThat(employee).isNotNull();
        assertThat(employee.getFirstName()).isEqualTo("Pankaj");
        assertThat(employee.getLastName()).isEqualTo("Sunda");


    }

    @Test
    public void givenEmployeeObject_whenfindByNativeSqlWithName_thenReturnEmployeeObject(){
        //given-preconditon set up
//        Employee employee1 = Employee.builder()
//                .firstName("Pankaj")
//                .lastName("Sunda")
//                .email("p@gmail.com")
//                .build();
        employeeRepository.save(employee1);

        //when -action
        String firstName="Pankaj";
        String lastName="Sunda";
        Employee employee=employeeRepository.findByNativeSqlWithName(firstName,lastName);

        //then-verify the output
        assertThat(employee).isNotNull();
        assertThat(employee.getFirstName()).isEqualTo("Pankaj");
        assertThat(employee.getLastName()).isEqualTo("Sunda");


    }

}
