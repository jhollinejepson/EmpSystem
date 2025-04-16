package com.hexa.empsystem.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.hexa.empsystem.entity.ProjectManager;

@Service
public class ProjectManagerService {

    private final ExcelService excelService;

    public ProjectManagerService(ExcelService excelService) {
        this.excelService = excelService;
    }

    // Method to get all project managers
    public List<ProjectManager> getAllProjectManagers() throws IOException {
        List<ProjectManager> projectManagers = new ArrayList<>();
        // Logic to read project managers from Excel can be added here
        // For now, we will return an empty list
        return projectManagers;
    }

    // Method to add or update project managers
    public void addOrUpdateProjectManagers(List<ProjectManager> projectManagers) throws IOException {
    
        excelService.writeProjectManagerToExcel(projectManagers);
    }
}