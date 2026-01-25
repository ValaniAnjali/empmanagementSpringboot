package com.example.employeemanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DepartmentSummaryDto {
    private Long id;
    private String name;
    private long employeeCount;
}

