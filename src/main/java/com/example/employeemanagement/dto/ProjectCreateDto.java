package com.example.employeemanagement.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Set;

@Data
public class ProjectCreateDto {

    @NotBlank(message = "Project name is required")
    private String name;

    @NotBlank(message = "Organization ID is required")
    private Long organizationId;

    private Set<@NotNull Long> employeeIds;
}
