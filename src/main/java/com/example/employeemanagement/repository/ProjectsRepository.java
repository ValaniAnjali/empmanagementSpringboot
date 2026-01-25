package com.example.employeemanagement.repository;

import com.example.employeemanagement.entity.Projects;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Arrays;
import java.util.List;

public interface ProjectsRepository extends JpaRepository<Projects, Long> {
    List<Projects> findByOrganizationId(Long orgId);
}