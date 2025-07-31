package com.reactive.controller;

import com.reactive.dto.EmployeeDto;
import com.reactive.entity.Employee;
import com.reactive.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@ExtendWith(SpringExtension.class)//it will register spring extenstion to test
@WebFluxTest(controllers = EmployeeController.class)//only load the controller necessary bean to test
public class EmployeeControllerTests {

    @Autowired
    private WebTestClient webTestClient; //to Call the rest apis

    @MockitoBean
    private EmployeeService employeeService; //mocking service class


    @Test
        public void givenEmployeeObject_whenCreateEmployee_thenReturnSavedEmployee(){
            //given-preconditon set up
        EmployeeDto employeeDto= new EmployeeDto();
        employeeDto.setFirstName("Pankaj");
        employeeDto.setLastName("Sunda");
        employeeDto.setEmail("p@gmail.com");

        BDDMockito.given(employeeService.saveEmployee(ArgumentMatchers.any(EmployeeDto.class)))
                .willReturn(Mono.just(employeeDto));


            //when -action
WebTestClient.ResponseSpec responseSpec = webTestClient.post().uri("/api/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(employeeDto),EmployeeDto.class)
                .exchange();
//excahnge to call api
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
            String employeeId="1";

            EmployeeDto employeeDto= new EmployeeDto();
            employeeDto.setFirstName("Pankaj");
            employeeDto.setLastName("Sunda");
            employeeDto.setEmail("p@gmail.com");

            //mock
        BDDMockito.given(employeeService.getEmployeeById(employeeId)).willReturn(Mono.just(employeeDto));

                //when -action
            //m-1

//        WebTestClient.ResponseSpec responseSpec= webTestClient.get().uri("/api/employees/{employeeId}",employeeId)
//                    .exchange();
//m-2 multiple key value
            WebTestClient.ResponseSpec responseSpec= webTestClient.get().uri("/api/employees/{employeeId}", Collections.singletonMap("employeeId",employeeId))
                    .exchange();

                //then-verify the output

            responseSpec.expectStatus().isOk()
                    .expectBody()
                    .consumeWith(System.out::println)
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

               List<EmployeeDto> list=new ArrayList<>();
               list.add(employeeDto);
               list.add(employeeDto1);

               BDDMockito.given(employeeService.getAllEmployee()).willReturn( Flux.fromIterable(list));

                //when -action
         WebTestClient.ResponseSpec responseSpec= webTestClient.get()
                       .uri("/api/employees")
                       .exchange();


                //then-verify the output
               responseSpec.expectStatus().isOk()
                       .expectBodyList(EmployeeDto.class)
                       .hasSize(list.size())
                       .consumeWith(System.out::println);

            }
            @Test
                public void givenEmployeeObject_whenUpdateEmployee_thenReturnUpdateEmployee(){
                    //given-preconditon set up
                String employeeId="123";
//                EmployeeDto savedEmployeeDto= new EmployeeDto();
//                savedEmployeeDto.setFirstName("Pankaj");
//                savedEmployeeDto.setLastName("Sunda");
//                savedEmployeeDto.setEmail("p@gmail.com");

                EmployeeDto updatedEmployeeDto= new EmployeeDto();
                updatedEmployeeDto.setFirstName("Mahendra");
                updatedEmployeeDto.setLastName("Sunda");
                updatedEmployeeDto.setEmail("m@gmail.com");
                //to return body we need to add argument matcher
                BDDMockito.given(employeeService.updateEmployee(ArgumentMatchers.any(String.class),ArgumentMatchers.any(EmployeeDto.class))).willReturn(Mono.just(updatedEmployeeDto));

                    //when -action
             WebTestClient.ResponseSpec responseSpec= webTestClient.put().uri("/api/employees/employeeId",employeeId)
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
                    String employeeId="123";

                   Mono<Void> mono=Mono.empty();
                    BDDMockito.given(employeeService.deleteEmployee(ArgumentMatchers.any(String.class))).willReturn(mono);

                        //when -action
                    WebTestClient.ResponseSpec responseSpec=webTestClient.delete().uri("/api/employees/employeeId",employeeId)
                            .exchange();


                        //then-verify the output
                    responseSpec.expectStatus().isNoContent()
                            .expectBody()
                            .consumeWith(System.out::println);


                    }
}
