package com.intern.employeemanagement.service;

import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class UtilityService {
    public String generateEmployeeCode() {
        return "EMP-" + UUID.randomUUID().toString().substring(0,8).toUpperCase();
    }

    public String normalizeName(String name) {
        if (name == null) return "";
        return name.trim().replaceAll("\\s+", " ");
    }
}
