package com.example.employeemanagement.service;

import com.example.employeemanagement.dto.*;
import com.example.employeemanagement.entity.Department;
import com.example.employeemanagement.entity.Employee;
import com.example.employeemanagement.entity.Organization;
import com.example.employeemanagement.entity.Projects;
import com.example.employeemanagement.repository.DepartmentRepository;
import com.example.employeemanagement.repository.EmployeeRepository;
import com.example.employeemanagement.repository.OrganizationRepository;
import com.example.employeemanagement.repository.ProjectsRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
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
        return employeeRepo.findByOrganizationId(orgId)
                .stream()
                .map(e -> new EmployeeSummaryDto(e.getId(), e.getName(), e.getEmail()))
                .toList();
    }

    public EmployeeDetailDto get(Long id) {
        Employee e = employeeRepo.findById(id).orElseThrow();
        EmployeeDetailDto dto = new EmployeeDetailDto();
        dto.setId(e.getId());
        dto.setName(e.getName());
        dto.setEmail(e.getEmail());
        dto.setOrganizationName(e.getOrganization().getName());
        dto.setDepartments(
                e.getDepartments().stream().map(Department::getName).collect(Collectors.toSet())
        );
        dto.setProjects(
                e.getProjects().stream().map(Projects::getName).collect(Collectors.toSet())
        );


        return dto;
    }

    public Employee create(EmployeeCreateDTO dto) {
        Organization org = orgRepo.findById(dto.getOrganizationId()).orElseThrow();

        Employee e = new Employee();
        e.setName(dto.getName());
        e.setEmail(dto.getEmail());
        e.setOrganization(org);

        if (dto.getDepartmentIds() != null) {
            Set<Department> departments = validateDepartments(dto.getDepartmentIds(), org.getId());
            e.setDepartments(departments);

            // IMPORTANT: sync reverse side
            departments.forEach(d -> d.getEmployees().add(e));
        }

        if (dto.getProjectIds() != null) {
            Set<Projects> projects = validateProjects(dto.getProjectIds(), org.getId());
            e.setProjects(projects);

            // IMPORTANT
            projects.forEach(p -> p.getEmployees().add(e));
        }

        return employeeRepo.save(e);
    }

    public void update(Long id, EmployeeUpdateDto dto) {
        Employee e = employeeRepo.findById(id).orElseThrow();
        e.setName(dto.getName());
        e.setEmail(dto.getEmail());
    }

    public void updateDepartments(Long id, Set<Long> deptIds) {
        Employee e = employeeRepo.findById(id).orElseThrow();
        e.setDepartments(validateDepartments(deptIds, e.getOrganization().getId()));
    }

    public void delete(Long id) {
        employeeRepo.deleteById(id);
    }

    private Set<Department> validateDepartments(Set<Long> ids, Long orgId) {
        Set<Department> depts = new HashSet<>(deptRepo.findAllById(ids));
        if (depts.stream().anyMatch(d -> !d.getOrganization().getId().equals(orgId)))
            throw new RuntimeException("Department organization mismatch");
        return depts;
    }

    private Set<Projects> validateProjects(Set<Long> ids, Long orgId) {
        Set<Projects> projects = new HashSet<>(projectRepo.findAllById(ids));
        if (projects.stream().anyMatch(p -> !p.getOrganization().getId().equals(orgId)))
            throw new RuntimeException("Project organization mismatch");
        return projects;
    }
}
