package com.example.employeemanagement.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EmployeeUpdateDto {
    private String name;
    private String email;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
