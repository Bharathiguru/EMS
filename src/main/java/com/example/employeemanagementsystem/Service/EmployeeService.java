package com.example.employeemanagementsystem.Service;

import com.example.employeemanagementsystem.model.Employee;
import com.example.employeemanagementsystem.model.Role;
import com.example.employeemanagementsystem.repo.EmployeeRepository;
import com.example.employeemanagementsystem.repo.RoleRepositroy;
import com.example.employeemanagementsystem.request.EmployeeRequest;
import com.example.employeemanagementsystem.resonse.ApiResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepo;

    @Autowired
    PasswordEncoder passwordEncoder;
    
    @Autowired
    RoleRepositroy roleRepositroy;

    public ApiResponse addEmployee(EmployeeRequest employeeRequest) {

        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeRequest, employee);

        employee.setPassword(passwordEncoder.encode(employee.getPassword()));
        
        List<Role> roles = new ArrayList<>();

        for (String name : employeeRequest.getRole()) {

            Optional<Role> role = roleRepositroy.findByName(name);
            if (role.isPresent()) {
                roles.add(role.get());
            } else {
                if (name.toUpperCase().equals("ADMIN")) {
                    roles.add(roleRepositroy.save(new Role(0, "ADMIN")));
                } else if (name.toUpperCase().equals("EMPLOYEE")) {
                    roles.add(roleRepositroy.save(new Role(0, "EMPLOYEE")));
                } else {
                    return ApiResponse.builder().code("1005").errors(Map.of("role error", "invalid role to add")).build();
                }
            }
        }
        employee.setRoles(roles);

        Employee newEmployee = employeeRepo.save(employee);

        if (Objects.nonNull(newEmployee)) {
            return ApiResponse.builder().code("1000").value("employee added successfully").build();
        } else {
            return ApiResponse.builder().code("1010").value("data not added").build();
        }

    }

    public ApiResponse getEmployees() {

        List<Employee> employees = employeeRepo.findAll();

        if (employees.isEmpty()) {
            return ApiResponse.builder().code("1003").errors(Map.of("error", "no data found for employees")).build();
        }
        return ApiResponse.builder().code("1000").value(employees).build();
    }

    public ApiResponse getEmployeeById(int id) {

        Optional<Employee> employee = employeeRepo.findById(id);

        if (employee.isEmpty()) {
            return ApiResponse.builder().code("1004").errors(Map.of("not found", "employee data not found for the particular id")).build();
        }
        return ApiResponse.builder().code("1000").value(employee.get()).build();
    }

    public ApiResponse updateEmployeeById(int id, EmployeeRequest employeeRequest) {

        Optional<Employee> employee = employeeRepo.findById(id);

        if (employee.isEmpty()) {
            return ApiResponse.builder().code("1004").errors(Map.of("not found", "employee data not found for the particular id")).build();
        }

        Employee updateEmployee = new Employee();
        BeanUtils.copyProperties(employeeRequest, updateEmployee);

        List<Role> roles = new ArrayList<>();

        for (String name : employeeRequest.getRole()) {

            Optional<Role> role = roleRepositroy.findByName(name);
            if (role.isPresent()) {
                roles.add(role.get());
            } else {
                if (name.toUpperCase().equals("ADMIN")) {
                    roles.add(roleRepositroy.save(new Role(0, "ADMIN")));
                } else if (name.toUpperCase().equals("EMPLOYEE")) {
                    roles.add(roleRepositroy.save(new Role(0, "EMPLOYEE")));
                } else {
                    return ApiResponse.builder().code("1005").errors(Map.of("role error", "invalid role to add")).build();
                }
            }
        }
        updateEmployee.setId(id);
        updateEmployee.setRoles(roles);
        System.out.println(updateEmployee);

        Employee updatedEmployee = employeeRepo.save(updateEmployee);
        if (Objects.nonNull(updatedEmployee)) {
            return ApiResponse.builder().code("1000").value("employee updated successfully").build();
        } else {
            return ApiResponse.builder().code("1010").value("data not added").build();
        }

    }

    public ApiResponse deleteEmployeeById(int id) {

        Employee employee = employeeRepo.findById(id).orElse(null);

        if (employee == null) {
            return ApiResponse.builder().code("1004").errors(Map.of("not found", "employee data not found for the particular id")).build();
        }

        employee.disassociateRoles();
        employeeRepo.delete(employee);

        Optional<Employee> deletedEmployee = employeeRepo.findById(id);

        if (deletedEmployee.isEmpty()) {
            return ApiResponse.builder().code("1000").value("employee deleted successfully").build();

        }

        return ApiResponse.builder().code("1010").value(Map.of("error", "employee not deleted try again later")).build();

    }
  
}
