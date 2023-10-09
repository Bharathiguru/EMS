package com.example.employeemanagementsystem.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "employee_documents")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDocument {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    
    @Column(name = "path")
    private String path;
    
    @Column(name = "file_nmae")
    private String fileName;
    
    @ManyToOne
    private Employee employee;
    
}
