package com.example.dehuy.service;

import com.example.dehuy.dto.EmployeeRequest;
import com.example.dehuy.dto.EmployeeResponse;
import com.example.dehuy.entity.Employee;
import com.example.dehuy.repository.DepartmentRepository;
import com.example.dehuy.repository.EmployeeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployeeFull() {
        return employeeRepository.findAll();
    }

    public List<EmployeeResponse> getAllEmployee() {
        return employeeRepository.findAll().stream().map(EmployeeResponse::new).toList();
    }

    public EmployeeResponse getEmloyeeById(Integer id) {
        Employee employee = employeeRepository.getOne(id);
        return new EmployeeResponse(employee);
    }

    public Employee addEmployee(EmployeeRequest employeeRequest) {
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeRequest, employee);
        return employeeRepository.save(employee);
    }

    public void deleteEmployee(Integer id) {
        employeeRepository.deleteById(id);
    }

    public void updateEmployee(EmployeeRequest employeeRequest) {
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeRequest, employee);
        employeeRepository.save(employee);
    }

    public Page<Employee> getAll(Integer page, String keyword, String sortType) {
        // 1. Xử lý Sắp xếp (Sort)
        // Mặc định sắp xếp theo ID tăng dần
        Sort sort = Sort.by("id").ascending();

        // Nếu client gửi lên sortType = "desc" thì sắp xếp lương giảm dần (ví dụ vậy)
        if (sortType != null && sortType.equalsIgnoreCase("desc")) {
            sort = Sort.by("salary").descending();
        }
        // Nếu muốn sắp xếp tăng dần theo lương
        else if (sortType != null && sortType.equalsIgnoreCase("asc")) {
            sort = Sort.by("salary").ascending();
        }

        // 2. Tạo đối tượng Pageable với thông tin trang và sắp xếp
        Pageable pageable = PageRequest.of(page, 3, sort); // 3 phần tử 1 trang

        // 3. Xử lý Tìm kiếm (Search)
        if (keyword != null && !keyword.isEmpty()) {
            // Nếu có từ khóa -> gọi hàm tìm kiếm vừa viết ở Repository
            return employeeRepository.findByNameContaining(keyword, pageable);
        }
        return employeeRepository.findAll(pageable);
    }


}
