package com.hexa.empsystem.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.hexa.empsystem.entity.Employee;
import com.hexa.empsystem.entity.EmployeeDetailsResponse;
import com.hexa.empsystem.entity.ProjectManager;

@Service
public class EmployeeService {

    private final ExcelService excelService;

    public EmployeeService(ExcelService excelService) {
        this.excelService = excelService;
    }

    public Employee getEmployee(String empId) throws IOException {
        List<Employee> employees = excelService.readEmployeesFromExcel();
        return employees.stream()
                .filter(employee -> employee.getEmpId().equals(empId))
                .findFirst()
                .orElse(null); // Employee not found
    }

    public ProjectManager getProjectManagerDetails(String empId) throws IOException {
        return excelService.readProjectManagerFromExcel(empId);
    }

    // Method to get all employee details
    public List<EmployeeDetailsResponse> getAllEmployeeDetails() throws IOException {
        List<Employee> employees = excelService.readEmployeesFromExcel();
        List<EmployeeDetailsResponse> employeeDetailsList = new ArrayList<>();

        for (Employee employee : employees) {
            ProjectManager projectManager = excelService.readProjectManagerFromExcel(employee.getEmpId());
            if (projectManager != null) {
                employeeDetailsList.add(new EmployeeDetailsResponse(
                    employee.getEmpId(),
                    employee.getEmpName(),
                    employee.getEmpEmail(),
                    projectManager.getCurrentProject(),
                    projectManager.getManager()
                ));
            } else {
                // If project manager details are not found, add employee with null values for project and manager
                employeeDetailsList.add(new EmployeeDetailsResponse(
                    employee.getEmpId(),
                    employee.getEmpName(),
                    employee.getEmpEmail(),
                    null,
                    null
                ));
            }
        }

        return employeeDetailsList;
    }
}