package com.example.employeemanagement.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DepartmentUpdateDto {
    private String name;
    private LocalDateTime createdat;
    private LocalDateTime updatedAt;
}

