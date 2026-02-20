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
                .map(project -> new ProjectSummaryDto(
                        project.getId(), project.getName(), project.getEmployees().size()))
                .toList();
    }

    public ProjectDetailDto get(Long id) {
        Projects project = projectRepo.findById(id).orElseThrow();
        return new ProjectDetailDto(
                project.getId(),
                project.getName(),
                project.getEmployees().stream()
                        .map(emp -> new EmployeeSummaryDto(emp.getId(), emp.getName(), emp.getEmail(),emp.getOrganization().getId(),emp.getCreatedAt(),emp.getUpdatedAt()))
                        .toList()
        );
    }

    public Projects create(ProjectCreateDto dto) {
        Organization org = orgRepo.findById(dto.getOrganizationId()).orElseThrow();

        Projects project = new Projects();
        project.setName(dto.getName());
        project.setOrganization(org);


        if (dto.getEmployeeIds() != null)
            project.setEmployees(new HashSet<>(empRepo.findAllById(dto.getEmployeeIds())));

        return projectRepo.save(project);
    }

    public void update(Long id, ProjectUpdateDto dto) {
        Projects project = projectRepo.findById(id).orElseThrow();
        project.setName(dto.getName());
    }



    public void delete(Long id) {
        projectRepo.deleteById(id);
    }
}

