package com.intern.employeemanagement.service;

import com.intern.employeemanagement.dto.DepartmentStatDTO;
import com.intern.employeemanagement.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public long getTotalEmployees() {
        return employeeRepository.count(); // Hàm có sẵn của JpaRepository
    }

    public List<DepartmentStatDTO> getEmployeeStats() {
        return employeeRepository.countEmployeesByDepartment();
    }
}
