package com.example.employeemanagement.repository;

import com.example.employeemanagement.entity.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganizationRepository extends JpaRepository<Organization, Long> {
}