package com.example.employeemanagementsystem.controller;

import com.example.employeemanagementsystem.Service.EmployeeService;
import com.example.employeemanagementsystem.request.EmployeeRequest;
import com.example.employeemanagementsystem.resonse.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    EmployeeService employeeServcie;

    @PostMapping("/")
    public ApiResponse addEmployee(@Valid @RequestBody EmployeeRequest employeeRequest) {
        return employeeServcie.addEmployee(employeeRequest);
    }

    @GetMapping("/")
    public ApiResponse getEmployees() {
        return employeeServcie.getEmployees();
    }

    @GetMapping("/{id}")
    public ApiResponse getEmployeeById(@PathVariable("id") int id) {
        return employeeServcie.getEmployeeById(id);
    }

    @PutMapping("/{id}")
    public ApiResponse updateEmployeeById(@PathVariable("id") int id, @RequestBody EmployeeRequest employeeRequest) {
        return employeeServcie.updateEmployeeById(id, employeeRequest);
    }

    @DeleteMapping("/{id}")
    public ApiResponse deleteEmloyeeById(@PathVariable("id") int id) {
        return employeeServcie.deleteEmployeeById(id);
    }
    
  
}
