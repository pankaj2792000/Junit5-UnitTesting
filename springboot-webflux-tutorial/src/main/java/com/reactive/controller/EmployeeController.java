package com.reactive.controller;

import com.reactive.dto.EmployeeDto;
import com.reactive.service.EmployeeService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@AllArgsConstructor
public class EmployeeController {


    private EmployeeService employeeService;

//    public EmployeeController(EmployeeService employeeService) {
//        this.employeeService = employeeService;
//    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<EmployeeDto> createEmployee(@RequestBody EmployeeDto employeeDto){
        return  employeeService.saveEmployee(employeeDto);
    }

    @GetMapping("{employeeId}")
    public Mono<EmployeeDto> getEmployee(@PathVariable("employeeId") String id){
        return  employeeService.getEmployeeById(id);
    }
//    @GetMapping
//    public Mono<List<EmployeeDto>> getEmployee(){
//        return  employeeService.getAllEmployee();
//    }

        @GetMapping
    public Flux<EmployeeDto> getEmployee(){
        return  employeeService.getAllEmployee();
    }

    @PutMapping("{employeeId}")
    public Mono<EmployeeDto> updateEmployee(@PathVariable ("employeeId") String id,@RequestBody EmployeeDto employeeDto){
        return employeeService.updateEmployee(id,employeeDto);
    }
    @DeleteMapping("{employeeId}")
    @ResponseStatus (HttpStatus.NO_CONTENT)
    public Mono<Void> deleteEmployee(@PathVariable ("employeeId") String id){
        return employeeService.deleteEmployee(id);
    }

}
