package com.example.employeemanagement.service;

import com.example.employeemanagement.dto.*;
import com.example.employeemanagement.entity.Department;
import com.example.employeemanagement.entity.Organization;
import com.example.employeemanagement.repository.DepartmentRepository;
import com.example.employeemanagement.repository.EmployeeRepository;
import com.example.employeemanagement.repository.OrganizationRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
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

    public List<DepartmentSummaryDto> getAll(Long orgId) {
        List<Department> departments = deptRepo.findByOrganizationId(orgId);

        return departments.stream()
                .map(d -> new DepartmentSummaryDto(d.getId(), d.getName(), d.getEmployees().size(), d.getCreatedAt(), d.getUpdatedAt()))
                .toList();
    }

    public DepartmentDetailDto get(Long id) {
        Department d = deptRepo.findById(id).orElseThrow();
        return new DepartmentDetailDto(
                d.getId(),
                d.getName(),
                d.getEmployees().stream()
                        .map(e -> new EmployeeSummaryDto(e.getId(), e.getName(), e.getEmail(), e.getCreatedAt(), e.getUpdatedAt()))
                        .toList(),
                d.getCreatedAt(),
                d.getUpdatedAt()
        );
    }

    public Department create(DepartmentCreateDto dto) {
        Organization org = orgRepo.findById(dto.getOrganizationId()).orElseThrow();
        Department d = new Department();
        d.setName(dto.getName());
        d.setOrganization(org);
        d.setCreatedAt(dto.getCreatedAt());
        d.setUpdatedAt(dto.getUpdatedAt());
        return deptRepo.save(d);
    }

    public void update(Long id, DepartmentUpdateDto dto) {
        Department d = deptRepo.findById(id).orElseThrow();
        d.setName(dto.getName());
    }


    public void delete(Long id) {
        deptRepo.deleteById(id);
    }
}

