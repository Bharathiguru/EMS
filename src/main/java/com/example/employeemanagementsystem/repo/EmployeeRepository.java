package com.example.employeemanagementsystem.repo;

import com.example.employeemanagementsystem.model.Employee;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;


public interface EmployeeRepository extends JpaRepository<Employee, Object> {
 
    public Optional<Employee> findByName(String userName);
    
}
