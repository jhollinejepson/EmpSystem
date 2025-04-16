package com.hexa.empsystem.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.hexa.empsystem.entity.Employee;
import com.hexa.empsystem.entity.ProjectManager;

@Service
public class ExcelService {

    private static final Logger logger = LoggerFactory.getLogger(ExcelService.class);

    private static final String EMPLOYEE_FILE_PATH = "employees.xlsx";
    private static final String PROJECT_MANAGER_FILE_PATH = "project_manager.xlsx";

    // Method to read employee details from Excel
    public List<Employee> readEmployeesFromExcel() throws IOException {
        List<Employee> employees = new ArrayList<>();
        File file = new File(EMPLOYEE_FILE_PATH);

        if (!file.exists()) {
            logger.warn("Employee file not found: {}", EMPLOYEE_FILE_PATH);
            return employees; // Return empty list if file does not exist
        }

        try (FileInputStream fileInputStream = new FileInputStream(file);
             Workbook workbook = new XSSFWorkbook(fileInputStream)) {

            Sheet sheet = workbook.getSheetAt(0);

            for (Row row : sheet) {
                if (row.getRowNum() == 0) continue; // Skip header row
                String empId = row.getCell(0).getStringCellValue();
                String empName = row.getCell(1).getStringCellValue();
                String empEmail = row.getCell(2).getStringCellValue();
                employees.add(new Employee(empId, empName, empEmail));
            }
        }

        return employees;
    }

    public ProjectManager readProjectManagerFromExcel(String empId) throws IOException {
        File file = new File(PROJECT_MANAGER_FILE_PATH);

        if (!file.exists()) {
            logger.warn("Project manager file not found: {}", PROJECT_MANAGER_FILE_PATH);
            return null;
        }

        try (FileInputStream fileInputStream = new FileInputStream(file);
             Workbook workbook = new XSSFWorkbook(fileInputStream)) {

            Sheet sheet = workbook.getSheetAt(0);

            for (Row row : sheet) {
                if (row.getRowNum() == 0) continue; // Skip header row
                String id = row.getCell(0).getStringCellValue();
                if (id.equals(empId)) {
                    String currentProject = row.getCell(1).getStringCellValue();
                    String manager = row.getCell(2).getStringCellValue();
                    return new ProjectManager(empId, currentProject, manager);
                }
            }
        }

        return null; // Employee not found
    }
    
    public void writeProjectManagerToExcel(List<ProjectManager> projectManagers) throws IOException {
        try (Workbook workbook = new XSSFWorkbook();
             FileOutputStream fileOutputStream = new FileOutputStream(PROJECT_MANAGER_FILE_PATH)) {

            Sheet sheet = workbook.createSheet("Project Managers");

            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("Emp ID");
            headerRow.createCell(1).setCellValue("Current Project");
            headerRow.createCell(2).setCellValue("Manager");

            int rowCount = 1;
            for (ProjectManager projectManager : projectManagers) {
                Row row = sheet.createRow(rowCount++);
                row.createCell(0).setCellValue(projectManager.getEmpId()); // Use empId
                row.createCell(1).setCellValue(projectManager.getCurrentProject());
                row.createCell(2).setCellValue(projectManager.getManager());
            }

            workbook.write(fileOutputStream);
        }
    }
}