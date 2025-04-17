package com.hexa.empsystem.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexa.empsystem.entity.Employee;
import com.hexa.empsystem.entity.EmployeeDetailsResponse;
import com.hexa.empsystem.entity.ProjectManager;
import com.hexa.empsystem.service.EmployeeService;
import com.hexa.empsystem.service.ExcelExportService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private final EmployeeService employeeService;
    private final ExcelExportService excelExportService;

    public EmployeeController(EmployeeService employeeService, ExcelExportService excelExportService) {
        this.employeeService = employeeService;
        this.excelExportService = excelExportService;
    }

    @GetMapping("/{empId}")
    public Employee getEmployeeDetails(@PathVariable String empId) throws IOException {
        return employeeService.getEmployee(empId);
    }

    @GetMapping("/{empId}/project-manager")
    public ProjectManager getProjectManagerDetails(@PathVariable String empId) throws IOException {
        return employeeService.getProjectManagerDetails(empId);
    }

    @GetMapping("/all")
    public List<EmployeeDetailsResponse> getAllEmployeeDetails() throws IOException {
        return employeeService.getAllEmployeeDetails();
    }

    @GetMapping("/export")
    public void exportEmployeesToExcel(HttpServletResponse response) throws IOException {
        excelExportService.exportToExcel(response);
    }

    @PostMapping("/write")
    public void writeEmployeesToExcel(@RequestBody List<Employee> employees) throws IOException {
        excelExportService.writeEmployeesToExcel(employees);
    }
}