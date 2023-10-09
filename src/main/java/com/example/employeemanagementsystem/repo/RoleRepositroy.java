package com.example.employeemanagementsystem.repo;

import com.example.employeemanagementsystem.model.Role;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepositroy extends JpaRepository<Role,Integer>{
    public Optional<Role> findByName(String names);
}
