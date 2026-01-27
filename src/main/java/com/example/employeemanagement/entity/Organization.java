package com.example.employeemanagement.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
public class Organization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "organization",cascade = CascadeType.REMOVE)
    private List<Employee> employees=new ArrayList<>();

    @OneToMany(mappedBy = "organization",cascade = CascadeType.REMOVE)
    private List<Department> departments=new ArrayList<>();

    @OneToMany(mappedBy = "organization",cascade = CascadeType.REMOVE)
    private List<Projects> projects=new ArrayList<>();

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

}
