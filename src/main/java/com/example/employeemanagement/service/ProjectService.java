package com.example.employeemanagement.service;

import com.example.employeemanagement.dto.*;
import com.example.employeemanagement.entity.Organization;
import com.example.employeemanagement.entity.Projects;
import com.example.employeemanagement.repository.EmployeeRepository;
import com.example.employeemanagement.repository.OrganizationRepository;
import com.example.employeemanagement.repository.ProjectsRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Transactional
public class ProjectService {

    private final ProjectsRepository projectRepo;
    private final OrganizationRepository orgRepo;
    private final EmployeeRepository empRepo;

    public List<ProjectSummaryDto> getAll(Long orgId) {
        return projectRepo.findByOrganizationId(orgId)
                .stream()
                .map(p -> new ProjectSummaryDto(
                        p.getId(), p.getName(), p.getEmployees().size()))
                .toList();
    }

    public ProjectDetailDto get(Long id) {
        Projects p = projectRepo.findById(id).orElseThrow();
        return new ProjectDetailDto(
                p.getId(),
                p.getName(),
                p.getEmployees().stream()
                        .map(e -> new EmployeeSummaryDto(e.getId(), e.getName(), e.getEmail()))
                        .toList()
        );
    }

    public Projects create(ProjectCreateDto dto) {
        Organization org = orgRepo.findById(dto.getOrganizationId()).orElseThrow();

        Projects p = new Projects();
        p.setName(dto.getName());
        p.setOrganization(org);

        if (dto.getEmployeeIds() != null)
            p.setEmployees(new HashSet<>(empRepo.findAllById(dto.getEmployeeIds())));

        return projectRepo.save(p);
    }

    public void update(Long id, ProjectUpdateDto dto) {
        Projects p = projectRepo.findById(id).orElseThrow();
        p.setName(dto.getName());
    }

    public void updateEmployees(Long id, Set<Long> empIds) {
        Projects p = projectRepo.findById(id).orElseThrow();
        p.setEmployees(new HashSet<>(empRepo.findAllById(empIds)));
    }

    public void delete(Long id) {
        projectRepo.deleteById(id);
    }
}

