package com.example.employeemanagement.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DepartmentCreateDto {
    private String name;
    private Long organizationId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

