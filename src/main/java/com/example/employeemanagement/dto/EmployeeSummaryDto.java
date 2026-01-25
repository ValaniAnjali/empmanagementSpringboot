package com.example.employeemanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmployeeSummaryDto {
    private Long id;
    private String name;
    private String email;
}

