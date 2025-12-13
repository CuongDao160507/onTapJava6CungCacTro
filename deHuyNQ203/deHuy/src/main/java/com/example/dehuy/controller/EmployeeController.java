package com.example.dehuy.controller;

import com.example.dehuy.dto.EmployeeRequest;
import com.example.dehuy.dto.EmployeeResponse;
import com.example.dehuy.entity.Employee;
import com.example.dehuy.exception.ApiResponse;
import com.example.dehuy.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/display")
    public List<EmployeeResponse> display(){
        return employeeService.getAllEmployee();
    }

    @GetMapping("/getAllDisplay")
    public ResponseEntity<ApiResponse<List<Employee>>> getAllDisplay(){
        List<Employee> employeeList =  employeeService.getAllEmployeeFull();
        return ResponseEntity.ok(new ApiResponse<>(
                HttpStatus.OK.value(), // Thay số 200 bằng cái này
                "Lấy dữ liệu thành công",
                employeeList,
                LocalDateTime.now()
        ));
    }

    @GetMapping("/detail/{id}")
    public EmployeeResponse display(@PathVariable("id") Integer id){
        return employeeService.getEmloyeeById(id);
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll(
            @RequestParam(name = "page", defaultValue = "0", required = false) Integer page,
            @RequestParam(required = false) String keyword, // Không bắt buộc nhập
            @RequestParam(required = false) String sort     // Không bắt buộc nhập
    ){
        return ResponseEntity.ok(employeeService.getAll(page, keyword, sort));
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse<Employee>> addEmployee(@RequestBody @Valid EmployeeRequest employeeRequest){
        Employee addEmployee = employeeService.addEmployee(employeeRequest);
        return ResponseEntity.ok(new ApiResponse<>(
                HttpStatus.OK.value(), // Thay số 200 bằng cái này
                "Add thành công",
                addEmployee,
                LocalDateTime.now()
        ));
    }

    @PutMapping("/update")
    public void  updateEmployee(@RequestBody @Valid EmployeeRequest employeeRequest){
        employeeService.updateEmployee(employeeRequest);
    }

    @DeleteMapping("/delete")
    public void deleteEmployee(@RequestParam(name = "id") Integer id){
        employeeService.deleteEmployee(id);
    }
}
