package com.example.employeemanagement.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DepartmentCreateDto {
    @NotBlank(message = "Department name is required")
    @Size(min = 2, max = 50, message = "Department name must be between 2 and 50 characters")
    private String name;

    @NotNull(message = "Organization ID is required")
    private Long organizationId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

