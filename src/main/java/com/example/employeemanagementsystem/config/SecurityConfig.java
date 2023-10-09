package com.example.employeemanagementsystem.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain getSecurtiFilterChain(HttpSecurity http) throws Exception {

        http.csrf(csrf -> csrf.disable());
        http.authorizeHttpRequests(authorize
                -> authorize
                        .requestMatchers(HttpMethod.POST, "/api/employees/{id}/documents").hasAuthority("EMPLOYEE")
                        .requestMatchers(HttpMethod.GET, "/api/employees/").hasAnyAuthority("ADMIN", "EMPLOYEE")
                        .requestMatchers(HttpMethod.POST, "/api/employees/").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/employees/{id}").hasAnyAuthority("ADMIN", "EMPLOYEE")
                        .requestMatchers(HttpMethod.DELETE, "/api/employees/{id}").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/employees/{id}").hasAuthority("ADMIN")
                        .anyRequest().authenticated()
        ).httpBasic(Customizer.withDefaults());
        http.formLogin(Customizer.withDefaults());

        return http.build();

    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
