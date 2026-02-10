package com.example.employeemanagement.service;
import com.example.employeemanagement.dto.OrganizationCountDto;
import com.example.employeemanagement.dto.OrganizationCreateDto;
import com.example.employeemanagement.dto.OrganizationDetailDto;
import com.example.employeemanagement.dto.OrganizationUpdateDto;
import com.example.employeemanagement.entity.Department;
import com.example.employeemanagement.entity.Organization;
import com.example.employeemanagement.entity.Projects;
import com.example.employeemanagement.repository.OrganizationRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class OrganizationService {

    private final OrganizationRepository organizationRepository;

    public List<OrganizationCountDto> getAll() {
        return organizationRepository.findAll().stream()
                .map(org -> new OrganizationCountDto(
                        org.getId(),
                        org.getName(),
                        org.getEmployees().size(),
                        org.getDepartments().size(),
                        org.getProjects().size(),
                        org.getCreatedAt(),
                        org.getUpdatedAt()))
                .toList();
    }

    public OrganizationDetailDto get(Long id) {
        Organization org = organizationRepository.findById(id).orElseThrow();
        return new OrganizationDetailDto(
                org.getId(),
                org.getName(),
                org.getDepartments().stream().map(Department::getName).toList(),
                org.getProjects().stream().map(Projects::getName).toList(),
                org.getCreatedAt(),
                org.getUpdatedAt()
        );
    }

    public Organization create(OrganizationCreateDto dto) {
        Organization org = new Organization();
        org.setName(dto.getName());
        org.setCreatedAt(dto.getCreatedAt());
        org.setUpdatedAt(dto.getUpdatedAt());
        return organizationRepository.save(org);
    }

    public void update(Long id, OrganizationUpdateDto dto) {
        Organization org = organizationRepository.findById(id).orElseThrow();
        org.setName(dto.getName());
    }

    public void delete(Long id) {
        organizationRepository.deleteById(id);
    }
}
