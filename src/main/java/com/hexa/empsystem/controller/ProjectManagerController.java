package com.hexa.empsystem.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.hexa.empsystem.entity.ProjectManager;
import com.hexa.empsystem.service.ProjectManagerService;

@RestController
@RequestMapping("/project-managers")
public class ProjectManagerController {

    private final ProjectManagerService projectManagerService;

    public ProjectManagerController(ProjectManagerService projectManagerService) {
        this.projectManagerService = projectManagerService;
    }

    // Get all project managers
    @GetMapping
    public List<ProjectManager> getAllProjectManagers() throws IOException {
        return projectManagerService.getAllProjectManagers();
    }

    // Add or update project managers
    @PostMapping
    public void addOrUpdateProjectManagers(@RequestBody List<ProjectManager> projectManagers) throws IOException {
        projectManagerService.addOrUpdateProjectManagers(projectManagers);
    }
}