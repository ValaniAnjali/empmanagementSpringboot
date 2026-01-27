package com.example.employeemanagement.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data
public class EmployeeCreateDTO {
    private String name;
    private String email;
    private Long organizationId;
    private Set<Long> departmentIds;
    private Set<Long> projectIds;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
