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
                .map(dept -> new DepartmentSummaryDto(dept.getId(), dept.getName(), dept.getEmployees().size(), dept.getCreatedAt(), dept.getUpdatedAt()))
                .toList();
    }

    public DepartmentDetailDto get(Long id) {
        Department dept = deptRepo.findById(id).orElseThrow();
        return new DepartmentDetailDto(
                dept.getId(),
                dept.getName(),
                dept.getEmployees().stream()
                        .map(emp -> new EmployeeSummaryDto(emp.getId(), emp.getName(), emp.getEmail(), emp.getCreatedAt(), emp.getUpdatedAt()))
                        .toList(),
                dept.getCreatedAt(),
                dept.getUpdatedAt()
        );
    }

    public Department create(DepartmentCreateDto dto) {
        Organization org = orgRepo.findById(dto.getOrganizationId()).orElseThrow();
        Department dept = new Department();
        dept.setName(dto.getName());
        dept.setOrganization(org);
        dept.setCreatedAt(dto.getCreatedAt());
        dept.setUpdatedAt(dto.getUpdatedAt());
        return deptRepo.save(dept);
    }

    public void update(Long id, DepartmentUpdateDto dto) {
        Department dept = deptRepo.findById(id).orElseThrow();
        dept.setName(dto.getName());
    }


    public void delete(Long id) {
        deptRepo.deleteById(id);
    }
}

