package com.example.thymeleaftutorial.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.thymeleaftutorial.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee,Long>{


}
