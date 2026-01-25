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

    private final OrganizationRepository orgRepo;

    public List<OrganizationCountDto> getAll() {
        return orgRepo.findAll().stream()
                .map(o -> new OrganizationCountDto(
                        o.getId(),
                        o.getName(),
                        o.getEmployees().size(),
                        o.getDepartments().size(),
                        o.getProjects().size()))
                .toList();
    }

    public OrganizationDetailDto get(Long id) {
        Organization o = orgRepo.findById(id).orElseThrow();
        return new OrganizationDetailDto(
                o.getId(),
                o.getName(),
                o.getDepartments().stream().map(Department::getName).toList(),
                o.getProjects().stream().map(Projects::getName).toList()
        );
    }

    public Organization create(OrganizationCreateDto dto) {
        Organization o = new Organization();
        o.setName(dto.getName());
        return orgRepo.save(o);
    }

    public void update(Long id, OrganizationUpdateDto dto) {
        Organization o = orgRepo.findById(id).orElseThrow();
        o.setName(dto.getName());
    }

    public void delete(Long id) {
        orgRepo.deleteById(id);
    }
}
