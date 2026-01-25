package com.example.employeemanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDetailDto {
    private Long id;
    private String name;
    private String email;
    private String organizationName;
    private Set<String> departments;
    private Set<String> projects;
//    private LocalDateTime createdAt;  // timestamp of creation
//    private LocalDateTime updatedAt;  // timestamp of last update

}


