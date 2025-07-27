package com.springbootTest.service;

import com.springbootTest.model.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    public Employee saveEmployee(Employee employee);

    public List<Employee> getAllEmployees();
    public Optional<Employee> getEmployeeById(Long id);
    public Employee updateEmployee(Employee employee);

    public void deleteEmployee(Long id);
}
