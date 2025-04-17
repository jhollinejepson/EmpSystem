package com.hexa.empsystem.service;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import com.hexa.empsystem.entity.Employee;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class ExcelExportService {

    private static final String EMPLOYEE_FILE_PATH = "employees.xlsx";

    public void writeEmployeesToExcel(List<Employee> newEmployees) throws IOException {
       
        List<Employee> existingEmployees = readEmployeesFromExcel();

        existingEmployees.addAll(newEmployees);

        try (Workbook workbook = new XSSFWorkbook();
             FileOutputStream fileOutputStream = new FileOutputStream(EMPLOYEE_FILE_PATH)) {

            Sheet sheet = workbook.createSheet("Employees");

            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("Emp ID");
            headerRow.createCell(1).setCellValue("Emp Name");
            headerRow.createCell(2).setCellValue("Emp Email");

            int rowCount = 1;
            for (Employee employee : existingEmployees) {
                Row row = sheet.createRow(rowCount++);
                row.createCell(0).setCellValue(employee.getEmpId());
                row.createCell(1).setCellValue(employee.getEmpName());
                row.createCell(2).setCellValue(employee.getEmpEmail());
            }

            workbook.write(fileOutputStream);
        }
    }
    
    

    public void exportToExcel(HttpServletResponse response) throws IOException {
        List<Employee> employees = readEmployeesFromExcel();

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Employees");

        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("Emp ID");
        headerRow.createCell(1).setCellValue("Emp Name");
        headerRow.createCell(2).setCellValue("Emp Email");

        int rowCount = 1;
        for (Employee employee : employees) {
            Row row = sheet.createRow(rowCount++);
            row.createCell(0).setCellValue(employee.getEmpId());
            row.createCell(1).setCellValue(employee.getEmpName());
            row.createCell(2).setCellValue(employee.getEmpEmail());
        }

        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename=employees.xlsx");

        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
    }

    private List<Employee> readEmployeesFromExcel() throws IOException {
        List<Employee> employees = new ArrayList<>();
        FileInputStream fileInputStream = new FileInputStream(EMPLOYEE_FILE_PATH);
        Workbook workbook = new XSSFWorkbook(fileInputStream);
        Sheet sheet = workbook.getSheetAt(0);

        for (Row row : sheet) {
            if (row.getRowNum() == 0) continue;
            String empId = row.getCell(0).getStringCellValue();
            String empName = row.getCell(1).getStringCellValue();
            String empEmail = row.getCell(2).getStringCellValue();
            employees.add(new Employee(empId, empName, empEmail));
        }

        workbook.close();
        fileInputStream.close();
        return employees;
    }
}