package com.example.dehuy.dto;

import com.example.dehuy.entity.Department;
import com.example.dehuy.entity.Employee;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({"name", "salary", "department"})  //Dong code giup format vi tri theo y muon.
public class EmployeeResponse {
//    private Integer id;
    private String name;
//    private String email;
    private Float salary;
    private Department department;

    public EmployeeResponse(Employee employee) {
        this.name = employee.getName();
        this.salary = employee.getSalary();
        this.department = employee.getDepartment();
    }
}
