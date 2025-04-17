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


    public void addOrUpdateProjectManagers(List<ProjectManager> projectManagers) throws IOException {
    
        excelService.writeProjectManagerToExcel(projectManagers);
    }
}