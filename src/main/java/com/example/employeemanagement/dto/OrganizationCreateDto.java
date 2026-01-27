package com.example.employeemanagement.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrganizationCreateDto {
    private String name;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

