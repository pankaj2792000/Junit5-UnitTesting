package com.reactive.service;

import com.reactive.dto.EmployeeDto;
import com.reactive.entity.Employee;
import com.reactive.mapper.EmployeeMapper;
import com.reactive.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements  EmployeeService{


    private EmployeeRepository employeeRepository;

    //below can be done ny lombok
//    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
//        this.employeeRepository = employeeRepository;
//    }

    @Override
    public Mono<EmployeeDto> saveEmployee(EmployeeDto employeeDto) {
      Employee employee= EmployeeMapper.mapToEmployee(employeeDto);
      Mono<Employee> savedEmployee=employeeRepository.save(employee);

   //   return savedEmployee.map((employee1 -> EmployeeMapper.mapToEmployeeDto(employee1));
      return  savedEmployee.map(EmployeeMapper::mapToEmployeeDto);
    }

//    @Override
//    public Mono<List<EmployeeDto>> getAllEmployee() {
//        return employeeRepository.findAll().map(employee -> EmployeeMapper.mapToEmployeeDto(employee)).collectList();
//    }

    @Override
    public Mono<EmployeeDto> getEmployeeById(String employeeId) {
        return employeeRepository.findById(employeeId).map(employee -> EmployeeMapper.mapToEmployeeDto(employee));
    }

    @Override
    public Flux<EmployeeDto> getAllEmployee() {
        return employeeRepository.findAll().map(employee -> EmployeeMapper.mapToEmployeeDto(employee)).switchIfEmpty(Flux.empty());
    }

    @Override
    public Mono<EmployeeDto> updateEmployee(String employeeId, EmployeeDto employeeDto) {
        Mono<Employee> savedEmployee=employeeRepository.findById(employeeId);
     Mono<Employee>updatedEmployee= savedEmployee.flatMap(employee -> {

            employee.setFirstName(employeeDto.getFirstName());
            employee.setLastName(employeeDto.getLastName());
            employee.setEmail(employeeDto.getEmail());
            return employeeRepository.save(employee);

        });

     return updatedEmployee.map(employee-> EmployeeMapper.mapToEmployeeDto(employee));

    }

    @Override
    public Mono<Void> deleteEmployee(String employeeId) {
       return employeeRepository.deleteById(employeeId);
    }


}
