package com.intern.employeemanagement.controller;

import com.intern.employeemanagement.dto.DepartmentStatDTO;
import com.intern.employeemanagement.model.Employee;
import com.intern.employeemanagement.repository.DepartmentRepository;
import com.intern.employeemanagement.repository.EmployeeRepository;
import com.intern.employeemanagement.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/web/employees")
public class EmployeeWebController {

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;

    public EmployeeWebController(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
    }

    @GetMapping
    public String showEmployeeList(Model model) {
        model.addAttribute("listEmployees", employeeRepository.findAll());
        return "employee-list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("employee", new Employee());
        model.addAttribute("listDepartments", departmentRepository.findAll());
        return "employee-add";
    }

    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute("employee") Employee employee) {
        employeeRepository.save(employee);
        return "redirect:/web/employees";
    }

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/statistics")
    public String showStatistics(Model model) {
        List<DepartmentStatDTO> stats = employeeService.getEmployeeStats();

        long totalEmployees = employeeService.getTotalEmployees();

        model.addAttribute("departmentStats", stats);
        model.addAttribute("totalEmployees", totalEmployees);

        return "statistics";
    }
}
