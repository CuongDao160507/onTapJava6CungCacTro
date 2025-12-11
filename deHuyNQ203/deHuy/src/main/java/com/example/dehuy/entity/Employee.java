package com.example.dehuy.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String email;
    private Float salary;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_department", referencedColumnName = "id", nullable = false)
    private Department department;
}
