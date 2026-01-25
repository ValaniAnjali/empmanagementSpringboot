package com.example.employeemanagement.service;

import com.example.employeemanagement.dto.*;
import com.example.employeemanagement.entity.Department;
import com.example.employeemanagement.entity.Organization;
import com.example.employeemanagement.repository.DepartmentRepository;
import com.example.employeemanagement.repository.EmployeeRepository;
import com.example.employeemanagement.repository.OrganizationRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Transactional
public class DepartmentService {

    private final DepartmentRepository deptRepo;
    private final OrganizationRepository orgRepo;
    private final EmployeeRepository empRepo;

    public List<DepartmentSummaryDto> getAll(Long orgId) {
        return deptRepo.findByOrganizationId(orgId)
                .stream()
                .map(d -> new DepartmentSummaryDto(
                        d.getId(), d.getName(), d.getEmployees().size()))
                .toList();
    }

    public DepartmentDetailDto get(Long id) {
        Department d = deptRepo.findById(id).orElseThrow();
        return new DepartmentDetailDto(
                d.getId(),
                d.getName(),
                d.getEmployees().stream()
                        .map(e -> new EmployeeSummaryDto(e.getId(), e.getName(), e.getEmail()))
                        .toList()
        );
    }

    public Department create(DepartmentCreateDto dto) {
        Organization org = orgRepo.findById(dto.getOrganizationId()).orElseThrow();
        Department d = new Department();
        d.setName(dto.getName());
        d.setOrganization(org);
        return deptRepo.save(d);
    }

    public void update(Long id, DepartmentUpdateDto dto) {
        Department d = deptRepo.findById(id).orElseThrow();
        d.setName(dto.getName());
    }

    public void updateEmployees(Long id, Set<Long> empIds) {
        Department d = deptRepo.findById(id).orElseThrow();
        d.setEmployees(new HashSet<>(empRepo.findAllById(empIds)));
    }

    public void delete(Long id) {
        deptRepo.deleteById(id);
    }
}

