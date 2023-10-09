package com.example.employeemanagementsystem.config;

import com.example.employeemanagementsystem.model.Employee;
import com.example.employeemanagementsystem.model.Role;
import com.example.employeemanagementsystem.repo.EmployeeRepository;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Employee employee = employeeRepository.findByName(username).orElseThrow(() -> new UsernameNotFoundException("user name not found"));

        Set<GrantedAuthority> grantedAuthoritys = new HashSet<>();

        for (Role role : employee.getRoles()) {
            grantedAuthoritys.add(new SimpleGrantedAuthority(role.getName()));
        }

        return new User(employee.getName(), employee.getPassword(), grantedAuthoritys);

    }

}
