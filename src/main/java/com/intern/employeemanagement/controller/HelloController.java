package com.intern.employeemanagement.controller;

import com.intern.employeemanagement.service.UtilityService;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    private final UtilityService utilityService;
    private final ModelMapper modelMapper;

    public HelloController(UtilityService utilityService, ModelMapper modelMapper) {
        this.utilityService = utilityService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/hello")
    public String sayHello() {
        String code = utilityService.generateEmployeeCode();

        String mapperStatus = (modelMapper != null) ? "Active" : "Inactive";

        return "Hello! Your new ID is: " + code + " | Mapper status: " + mapperStatus;
    }
}