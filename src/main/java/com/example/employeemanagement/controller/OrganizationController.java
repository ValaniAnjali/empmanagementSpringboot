package com.example.employeemanagement.controller;

import com.example.employeemanagement.dto.OrganizationCountDto;
import com.example.employeemanagement.dto.OrganizationCreateDto;
import com.example.employeemanagement.dto.OrganizationDetailDto;
import com.example.employeemanagement.dto.OrganizationUpdateDto;
import com.example.employeemanagement.entity.Organization;
import com.example.employeemanagement.service.OrganizationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/organizations")
@RequiredArgsConstructor
public class OrganizationController {

    private final OrganizationService service;

    @GetMapping
    public List<OrganizationCountDto> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public OrganizationDetailDto get(@PathVariable Long id) {
        return service.get(id);
    }

    @PostMapping
    public Organization create(@Valid @RequestBody OrganizationCreateDto dto) {
        return service.create(dto);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody OrganizationUpdateDto dto) {
        service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}

