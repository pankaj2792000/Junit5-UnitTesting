package com.springbootTest.controller;

import com.springbootTest.exception.ResourceNotFoundException;
import com.springbootTest.model.Employee;
import com.springbootTest.service.EmployeeService;
import com.springbootTest.service.EmployeeServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService){
        this.employeeService=employeeService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Employee createEmployee(@RequestBody  Employee employee){
        return  employeeService.saveEmployee(employee);
    }

    @GetMapping
    public List<Employee> getAllEmployee(){
        return  employeeService.getAllEmployees();
    }

    @GetMapping("{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") Long employeeId){
        return employeeService.getEmployeeById(employeeId)
                .map(ResponseEntity::ok)
                .orElseGet(()->ResponseEntity.notFound().build());
    }

    @PutMapping("{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable("id") Long employeeId,@RequestBody Employee employee){
//        Optional<Employee> employeeOptional=employeeService.getEmployeeById(employeeId);
//        if(!employeeOptional.isPresent()){
//           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        Employee updatedEmployee=employeeService.updateEmployee(employee);
//        return ResponseEntity.ok().body(updatedEmployee);
       return employeeService.getEmployeeById(employeeId).map(savedEmployee->{
            savedEmployee.setFirstName(employee.getFirstName());
            savedEmployee.setLastName(employee.getLastName());
            savedEmployee.setEmail(employee.getEmail());
          Employee updatedEmployee=  employeeService.updateEmployee(savedEmployee);
          return  new ResponseEntity(updatedEmployee,HttpStatus.OK);

        }).orElseGet(()->ResponseEntity.notFound().build());

    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") Long employeeId)
    {
        this.employeeService.deleteEmployee(employeeId);
        return new ResponseEntity<String>("Employee deleted successfully",HttpStatus.OK);
    }

}
