package com.example.thymeleaftutorial.service;

import java.util.List;
import com.example.thymeleaftutorial.model.Employee;

public interface EmployeeService {
    Employee saveEmployee(Employee employee);
    List<Employee> getAllEmployees();
    Employee getEmployeeById(long id);
    Employee updateEmployee(Employee employee, long id);
    void deleteEmployee(long id);
    void saveEmployeeByGUI(Employee employee);
    void deleteEmployeeById(long id);
}
