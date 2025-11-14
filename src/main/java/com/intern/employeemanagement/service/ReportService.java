package com.intern.employeemanagement.service;

import com.intern.employeemanagement.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ReportService {

    private static final Logger logger = LoggerFactory.getLogger(ReportService.class);
    private final EmployeeRepository employeeRepository;

    public ReportService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Cacheable("total_emp")
    public long getTotalEmployees() {
        logger.info("Đang tính toán tổng số nhân viên");
        try { Thread.sleep(2000); } catch (InterruptedException e) {}

        return employeeRepository.count();
    }

    @CacheEvict(value = "total_emp", allEntries = true)
    public void clearCache() {
        logger.info("--- Đã xóa Cache ---");
    }


    @Scheduled(fixedRate = 30000)
    public void reportSystemStatus() {
        logger.info("SYSTEM RUNNING: Hệ thống vẫn đang hoạt động lúc " + java.time.LocalDateTime.now());
    }
}