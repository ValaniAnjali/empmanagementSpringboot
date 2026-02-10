package com.example.employeemanagement.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrganizationCreateDto {
    @NotBlank(message = "organization name is required")
    private String name;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

