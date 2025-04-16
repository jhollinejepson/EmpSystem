package com.hexa.empsystem.entity;

public class ProjectManager {
    private String empId;
    private String currentProject;
    private String manager;

    public ProjectManager(String empId, String currentProject, String manager) {
        this.empId = empId;
        this.currentProject = currentProject;
        this.manager = manager;
    }

    public String getEmpId() {
        return empId;
    }

    public String getCurrentProject() {
        return currentProject;
    }

    public String getManager() {
        return manager;
    }
}