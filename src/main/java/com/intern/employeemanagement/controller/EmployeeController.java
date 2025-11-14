package com.intern.employeemanagement.controller;

import com.intern.employeemanagement.model.Department;
import com.intern.employeemanagement.model.Employee;
import com.intern.employeemanagement.repository.DepartmentRepository;
import com.intern.employeemanagement.repository.EmployeeRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.intern.employeemanagement.exception.ResourceNotFoundException;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;

    public EmployeeController(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
    }
    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees(@RequestParam(required = false) Long deptId) {
        if(deptId != null){
            return ResponseEntity.ok(employeeRepository.findByDepartmentId(deptId));
        }
        return ResponseEntity.ok(employeeRepository.findAll());
    }

    @PostMapping
    public ResponseEntity<Employee> createEmployee(@Valid @RequestBody Employee employee) {
        return ResponseEntity.ok(employeeRepository.save(employee));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No employee was found with ID: " + id));
        return ResponseEntity.ok(employee);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No employee was found with ID: " + id));

        employeeRepository.delete(employee);
        return ResponseEntity.ok("Delete successfully with ID: " + id);
    }

}
