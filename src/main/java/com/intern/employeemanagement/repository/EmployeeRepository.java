package com.intern.employeemanagement.repository;

import com.intern.employeemanagement.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findByNameContaining(String name);

    List<Employee> findByDepartmentId(Long departmentId);
}
