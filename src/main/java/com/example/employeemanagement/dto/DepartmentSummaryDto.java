package com.example.employeemanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class DepartmentSummaryDto {
    private Long id;
    private String name;
    private long employeeCount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}

