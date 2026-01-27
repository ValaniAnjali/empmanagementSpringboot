package com.example.employeemanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
public class DepartmentDetailDto {
    private Long id;
    private String name;
    private List<EmployeeSummaryDto> employees;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

