package com.example.employeemanagement.dto;

import lombok.Data;

import java.util.Set;

@Data
public class ProjectCreateDto {
    private String name;
    private Long organizationId;
    private Set<Long> employeeIds;
}

