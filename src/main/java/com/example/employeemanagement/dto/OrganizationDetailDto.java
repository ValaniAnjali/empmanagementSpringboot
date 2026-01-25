package com.example.employeemanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class OrganizationDetailDto {
    private Long id;
    private String name;
    private List<String> departments;
    private List<String> projects;
}
