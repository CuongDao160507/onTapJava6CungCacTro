package com.example.dehuy.repository;

import com.example.dehuy.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
    // Tìm kiếm theo tên có chứa từ khóa (tương đương LIKE %name%)
    Page<Employee> findByNameContaining(String name, Pageable pageable);
}
