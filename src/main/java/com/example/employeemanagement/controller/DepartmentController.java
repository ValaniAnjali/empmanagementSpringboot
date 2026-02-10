package com.example.employeemanagement.controller;

import com.example.employeemanagement.dto.DepartmentCreateDto;
import com.example.employeemanagement.dto.DepartmentDetailDto;
import com.example.employeemanagement.dto.DepartmentSummaryDto;
import com.example.employeemanagement.dto.DepartmentUpdateDto;
import com.example.employeemanagement.entity.Department;
import com.example.employeemanagement.service.DepartmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/departments")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService service;

    @GetMapping
    public List<DepartmentSummaryDto> getAll(@RequestParam Long organizationId) {
        return service.getAll(organizationId);
    }

    @GetMapping("/{id}")
    public DepartmentDetailDto get(@PathVariable Long id) {
        return service.get(id);
    }

    @PostMapping
    public Department create(@Valid @RequestBody DepartmentCreateDto dto) {
        return service.create(dto);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody DepartmentUpdateDto dto) {
        service.update(id, dto);
    }



    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
