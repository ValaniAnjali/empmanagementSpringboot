package com.example.employeemanagement.controller;

import com.example.employeemanagement.dto.ProjectCreateDto;
import com.example.employeemanagement.dto.ProjectDetailDto;
import com.example.employeemanagement.dto.ProjectSummaryDto;
import com.example.employeemanagement.dto.ProjectUpdateDto;
import com.example.employeemanagement.entity.Projects;
import com.example.employeemanagement.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/projects")
@RequiredArgsConstructor
public class ProjectsController {

    private final ProjectService service;

    @GetMapping
    public List<ProjectSummaryDto> getAll(@RequestParam Long organizationId) {
        return service.getAll(organizationId);
    }

    @GetMapping("/{id}")
    public ProjectDetailDto get(@PathVariable Long id) {
        return service.get(id);
    }

    @PostMapping
    public Projects create(@RequestBody ProjectCreateDto dto) {
        return service.create(dto);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody ProjectUpdateDto dto) {
        service.update(id, dto);
    }

    @PatchMapping("/{id}/employees")
    public void updateEmployees(@PathVariable Long id, @RequestBody Set<Long> empIds) {
        service.updateEmployees(id, empIds);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}

