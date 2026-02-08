package com.example.employeemanagement.controller;

import com.example.employeemanagement.dto.*;
import com.example.employeemanagement.entity.Employee;
import com.example.employeemanagement.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> get(@PathVariable Long id) {
//       try{
//           return ResponseEntity.ok(service.get(id));
//       }catch (Exception e){
//           return ResponseEntity.status(400).body("No such id exist");
//       }
        return ResponseEntity.ok(service.get(id));

    }

    @PostMapping
    public Employee create(@RequestBody EmployeeCreateDTO dto) {
        return service.create(dto);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody EmployeeUpdateDto dto) {
        service.update(id, dto);
    }


    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
