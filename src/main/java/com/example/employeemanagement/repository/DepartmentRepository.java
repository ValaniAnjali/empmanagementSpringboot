package com.example.employeemanagement.repository;

import com.example.employeemanagement.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Arrays;
import java.util.List;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
    List<Department> findByOrganizationId(Long orgId);
}