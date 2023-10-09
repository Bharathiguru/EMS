package com.example.employeemanagementsystem.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EmployeeRequest {

    @NotNull(message = "name should be required")
    @NotBlank(message = "name should be in valid character")
    @Size(max = 80, min = 3, message = "name should be with in 80 characters and minimum 3 characters")
    private String name;

    @NotNull(message = "gender should be required")
    @NotBlank(message = "gender should be in valid character")
    @Size(max = 15, min = 4, message = "name should be with in 80 characters and minimum 3 characters")
    private String gender;

    @NotNull(message = "age is required")
    @Min(value = 18, message = "age should be minimum 18")
    @Max(value = 60, message = "age should be maximum 60")
    private int age;
    
    @NotNull(message = "password should be required")
    @NotBlank(message = "password should be in valid character")
    @Size(min = 8 , max = 16 , message = "passwrod should be minimum 8 characters")
    private String password;

    @NotNull(message = "role is required")
    @NotEmpty(message = "minimum one role should be need")
    private List<String> role;

}
