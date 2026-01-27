package com.example.employeemanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class OrganizationCountDto {
    private Long id;
    private String name;
    private long employeeCount;
    private long departmentCount;
    private long projectCount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}

