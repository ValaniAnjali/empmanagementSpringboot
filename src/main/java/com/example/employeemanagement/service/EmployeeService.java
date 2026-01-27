package com.example.employeemanagement.service;

import com.example.employeemanagement.dto.*;
import com.example.employeemanagement.entity.*;
import com.example.employeemanagement.exception.ResourceNotFoundException;
import com.example.employeemanagement.repository.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class EmployeeService {

    private final EmployeeRepository employeeRepo;
    private final OrganizationRepository orgRepo;
    private final DepartmentRepository deptRepo;
    private final ProjectsRepository projectRepo;

    public List<EmployeeSummaryDto> getAll(Long orgId) {
        return employeeRepo.findByOrganizationId(orgId);
    }

    public EmployeeDetailDto get(Long id) {
        Employee e = employeeRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("No such id exist"));

        EmployeeDetailDto dto = new EmployeeDetailDto();
        dto.setId(e.getId());
        dto.setName(e.getName());
        dto.setEmail(e.getEmail());
        dto.setOrganizationName(e.getOrganization().getName());
        dto.setDepartments(
                e.getDepartments().stream()
                        .map(Department::getName)
                        .collect(Collectors.toSet())
        );
        dto.setProjects(
                e.getProjects().stream()
                        .map(Projects::getName)
                        .collect(Collectors.toSet())
        );
        dto.setCreatedAt(e.getCreatedAt());
        dto.setUpdatedAt(e.getUpdatedAt());

        return dto;
    }

    public Employee create(EmployeeCreateDTO dto) {
        Organization org = orgRepo.findById(dto.getOrganizationId()).orElseThrow();

        Employee e = new Employee();
        e.setName(dto.getName());
        e.setEmail(dto.getEmail());
        e.setOrganization(org);
        e.setCreatedAt(dto.getCreatedAt());
        e.setUpdatedAt(dto.getUpdatedAt());

        if (dto.getDepartmentIds() != null && !dto.getDepartmentIds().isEmpty()) {
            Set<Department> departments = new HashSet<>(deptRepo.findAllById(dto.getDepartmentIds()));

            if (departments.stream().anyMatch(d -> !d.getOrganization().getId().equals(org.getId())))
                throw new RuntimeException("Department organization mismatch");

            e.setDepartments(departments);
            departments.forEach(d -> d.getEmployees().add(e));
        }

        if (dto.getProjectIds() != null && !dto.getProjectIds().isEmpty()) {
            Set<Projects> projects = new HashSet<>(projectRepo.findAllById(dto.getProjectIds()));

            if (projects.stream().anyMatch(p -> !p.getOrganization().getId().equals(org.getId())))
                throw new RuntimeException("Project organization mismatch");

            e.setProjects(projects);
            projects.forEach(p -> p.getEmployees().add(e));
        }

        return employeeRepo.save(e);
    }

    public void update(Long id, EmployeeUpdateDto dto) {
        Employee e = employeeRepo.findById(id).orElseThrow();
        e.setName(dto.getName());
        e.setEmail(dto.getEmail());
    }



    public void delete(Long id) {
        employeeRepo.deleteById(id);
    }
}
