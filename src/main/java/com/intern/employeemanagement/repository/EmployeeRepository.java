package com.intern.employeemanagement.repository;

import com.intern.employeemanagement.dto.DepartmentStatDTO;
import com.intern.employeemanagement.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findByNameContaining(String name);

    List<Employee> findByDepartmentId(Long departmentId);

    @Query("SELECT new com.intern.employeemanagement.dto.DepartmentStatDTO(d.name, COUNT(e)) " +
            "FROM Employee e JOIN e.department d " +
            "GROUP BY d.name")
    List<DepartmentStatDTO> countEmployeesByDepartment();
}
