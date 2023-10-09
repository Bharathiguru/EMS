package com.example.employeemanagementsystem.repo;

import com.example.employeemanagementsystem.model.EmployeeDocument;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeDocumentRepository extends JpaRepository<EmployeeDocument, Integer> {
    
}
