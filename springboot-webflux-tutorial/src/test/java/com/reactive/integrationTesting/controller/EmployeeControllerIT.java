package com.reactive.integrationTesting.controller;

import com.reactive.dto.EmployeeDto;
import com.reactive.repository.EmployeeRepository;
import com.reactive.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EmployeeControllerIT {

   @Autowired
    private EmployeeService employeeService;

   @Autowired
    private WebTestClient webTestClient;

   @Autowired
   private EmployeeRepository employeeRepository;

//clean mongodb
    @BeforeEach
   public  void setUp() {
        //subscribe it to deltete
        employeeRepository.deleteAll().subscribe();

    }

   @Test
       public void givenEmployeeObject_whenCreateEmployee_thenReturnEmpoyeeObject() {
       //given-preconditon set up
       EmployeeDto employeeDto = new EmployeeDto();
       employeeDto.setFirstName("Pankaj");
       employeeDto.setLastName("Sunda");
       employeeDto.setEmail("p@gmail.com");


       //when -action
       WebTestClient.ResponseSpec responseSpec = webTestClient.post().uri("/api/employees")
               .contentType(MediaType.APPLICATION_JSON)
               .accept(MediaType.APPLICATION_JSON)
               .body(Mono.just(employeeDto), EmployeeDto.class)
               .exchange();


       //then-verify the output
       responseSpec.expectStatus().isCreated()
               .expectBody()
               .consumeWith(System.out::println)
               .jsonPath("$.firstName").isEqualTo(employeeDto.getFirstName())
               .jsonPath("$.lastName").isEqualTo(employeeDto.getLastName())
               .jsonPath("$.email").isEqualTo(employeeDto.getEmail());

   }
       @Test
       public void givenEmployeeId_whenGetEmployee_thenReturnEmployeeObject(){
           //given-preconditon set up

           //first we need to save
           EmployeeDto employeeDto= new EmployeeDto();
           employeeDto.setFirstName("Pankaj");
           employeeDto.setLastName("Sunda");
           employeeDto.setEmail("p@gmail.com");

            EmployeeDto savedEmployeeDto=employeeService.saveEmployee(employeeDto).block();


           //when -action
           //m-1

//        WebTestClient.ResponseSpec responseSpec= webTestClient.get().uri("/api/employees/{employeeId}",employeeId)
//                    .exchange();
//m-2 multiple key value
           WebTestClient.ResponseSpec responseSpec= webTestClient.get().uri("/api/employees/{employeeId}", Collections.singletonMap("employeeId",savedEmployeeDto.getId()))
                   .exchange();

           //then-verify the output

           responseSpec.expectStatus().isOk()
                   .expectBody()
                   .consumeWith(System.out::println)
                   .jsonPath("$.id").isEqualTo(savedEmployeeDto.getFirstName())
                   .jsonPath("$.firstName").isEqualTo(employeeDto.getFirstName())
                   .jsonPath("$.lastName").isEqualTo(employeeDto.getLastName())
                   .jsonPath("$.email").isEqualTo(employeeDto.getEmail());


       }

       @Test
       public void givenEmployeeList_whenGetEmployee_thenReturnEmployeeList(){
           //given-preconditon set up

           EmployeeDto employeeDto= new EmployeeDto();
           employeeDto.setFirstName("Pankaj");
           employeeDto.setLastName("Sunda");
           employeeDto.setEmail("p@gmail.com");

           EmployeeDto employeeDto1= new EmployeeDto();
           employeeDto1.setFirstName("Mahendra");
           employeeDto1.setLastName("Sunda");
           employeeDto1.setEmail("m@gmail.com");


           employeeService.saveEmployee(employeeDto).block();
           employeeService.saveEmployee(employeeDto1).block();

           //when -action
           WebTestClient.ResponseSpec responseSpec= webTestClient.get()
                   .uri("/api/employees")
                   .exchange();


           //then-verify the output
           responseSpec.expectStatus().isOk()
                   .expectBodyList(EmployeeDto.class)
                   .consumeWith(System.out::println);

       }
       @Test
       public void givenEmployeeObject_whenUpdateEmployee_thenReturnUpdateEmployee(){
           //given-preconditon set up

           //first save

                EmployeeDto employeeDto= new EmployeeDto();
                employeeDto.setFirstName("Pankaj");
           employeeDto.setLastName("Sunda");
           employeeDto.setEmail("p@gmail.com");

           EmployeeDto savedEmployeeDto=employeeService.saveEmployee(employeeDto).block();

           EmployeeDto updatedEmployeeDto= new EmployeeDto();
           updatedEmployeeDto.setFirstName("Mahendra");
           updatedEmployeeDto.setLastName("Sunda");
           updatedEmployeeDto.setEmail("m@gmail.com");


           //when -action
           WebTestClient.ResponseSpec responseSpec= webTestClient.put().uri("/api/employees/employeeId",savedEmployeeDto.getId())
                   .contentType(MediaType.APPLICATION_JSON)
                   .accept(MediaType.APPLICATION_JSON)
                   .body(Mono.just(updatedEmployeeDto),EmployeeDto.class)
                   .exchange();
           //then-verify the output
           responseSpec.expectStatus().isOk()
                   .expectBody()
                   .consumeWith(System.out::println)
                   .jsonPath("$.firstName").isEqualTo(updatedEmployeeDto.getFirstName())
                   .jsonPath("$.lastName").isEqualTo(updatedEmployeeDto.getLastName())
                   .jsonPath("$.email").isEqualTo(updatedEmployeeDto.getEmail());

       }

       @Test
       public void givenEmployeeId_whenDeleteEmployee_thenReturnSuccess(){
           //given-preconditon set up

           EmployeeDto employeeDto= new EmployeeDto();
           employeeDto.setFirstName("Pankaj");
           employeeDto.setLastName("Sunda");
           employeeDto.setEmail("p@gmail.com");

           EmployeeDto savedEmployeeDto=employeeService.saveEmployee(employeeDto).block();

           //when -action
           WebTestClient.ResponseSpec responseSpec=webTestClient.delete().uri("/api/employees/employeeId",savedEmployeeDto.getId())
                   .exchange();


           //then-verify the output
           responseSpec.expectStatus().isNoContent()
                   .expectBody()
                   .consumeWith(System.out::println);


       }

   }

