package com.intern.employeemanagement.controller;

import com.intern.employeemanagement.model.Employee;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private List<Employee> employees = new ArrayList<>();

    public EmployeeController() {
        employees.add(new Employee(1, "Alice Johnson", "alice@gmail.com"));
        employees.add(new Employee(2, "Bob Smith", "bobsm@gmail.com"));
    }
    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        return ResponseEntity.ok(employees);
    }

    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        int newId = employees.size() + 1;
        employee.setId(newId);

        employees.add(employee);

        return ResponseEntity.status(201).body(employee);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable int id) {
        for (Employee emp : employees) {
            if (emp.getId() == id) {
                return ResponseEntity.ok(emp);
            }
        }
        return ResponseEntity.notFound().build();
    }
}
