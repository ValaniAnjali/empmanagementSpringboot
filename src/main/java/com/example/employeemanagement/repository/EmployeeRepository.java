package com.example.employeemanagement.repository;

import com.example.employeemanagement.dto.EmployeeSummaryDto;
import com.example.employeemanagement.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    List<EmployeeSummaryDto> findByOrganizationId(Long organizationId);
}
