package com.example.employeemanagement.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data
public class EmployeeCreateDTO {

    @NotBlank(message = "Employee name is required")
    @Size(min = 3, max = 50, message = "Name must be between 2 and 50 characters")
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Email format is invalid")
    private String email;

    @NotNull(message = "Organization ID is required")
    private Long organizationId;

    @NotEmpty(message = "At least one department must be selected")
    private Set<@NotNull Long> departmentIds;

    private Set<@NotNull Long> projectIds;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
