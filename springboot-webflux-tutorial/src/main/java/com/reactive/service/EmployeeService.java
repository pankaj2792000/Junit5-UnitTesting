package com.reactive.service;

import com.reactive.dto.EmployeeDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface EmployeeService {

    public Mono<EmployeeDto> saveEmployee(EmployeeDto employeeDto);
    //public Mono<List<EmployeeDto>> getAllEmployee();
    public Mono<EmployeeDto> getEmployeeById(String employeeId);

    public Flux<EmployeeDto> getAllEmployee();

    public Mono<EmployeeDto> updateEmployee(String employeeId,EmployeeDto employeeDto);

    public Mono<Void> deleteEmployee(String employeeId);
}
