package com.intern.employeemanagement.controller;

import com.intern.employeemanagement.service.ReportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);
    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;
    private final ReportService reportService;

    public EmployeeController(EmployeeRepository employeeRepository,
                              DepartmentRepository departmentRepository,
                              ReportService reportService) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
        this.reportService = reportService;
    }
    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees(@RequestParam(required = false) Long deptId) {
        logger.info("Request lấy danh sách nhân viên. DeptId: {}", deptId);
        if(deptId != null){
            return ResponseEntity.ok(employeeRepository.findByDepartmentId(deptId));
        }
        return ResponseEntity.ok(employeeRepository.findAll());
    }

    @PostMapping
    public ResponseEntity<Employee> createEmployee(@Valid @RequestBody Employee employee) {
        logger.info("Request tạo nhân viên mới: {}", employee.getEmail());
        reportService.clearCache();
        return ResponseEntity.ok(employeeRepository.save(employee));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No employee was found with ID: " + id));
        logger.info("Request lấy nhân viên. Id: {}", id);
        return ResponseEntity.ok(employee);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No employee was found with ID: " + id));

        employeeRepository.delete(employee);
        logger.info("Request xoá nhân viên. Id: {}", id);
        return ResponseEntity.ok("Delete successfully with ID: " + id);
    }

    @GetMapping("/count")
    public ResponseEntity<Long> countEmployees() {
        return ResponseEntity.ok(reportService.getTotalEmployees());
    }
}
