package com.example.employeemanagement.controller;

import com.example.employeemanagement.dto.*;
import com.example.employeemanagement.entity.Employee;
import com.example.employeemanagement.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService service;

    @GetMapping
    public List<EmployeeSummaryDto> getAll(@RequestParam Long organizationId) {
        return service.getAll(organizationId);
    }

    @GetMapping("/{id}")
    public EmployeeDetailDto get(@PathVariable Long id) {
        return service.get(id);
    }

    @PostMapping
    public Employee create(@RequestBody EmployeeCreateDTO dto) {
        return service.create(dto);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody EmployeeUpdateDto dto) {
        service.update(id, dto);
    }

    @PatchMapping("/{id}/department")
    public void updateDepartments(@PathVariable Long id, @RequestBody Set<Long> deptIds) {
        service.updateDepartments(id, deptIds);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
