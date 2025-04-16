package com.hexa.empsystem.entity;

public class EmployeeDetailsResponse {
    private String empId;
    private String empName;
    private String empEmail;
    private String currentProject;
    private String manager;

    // Constructors, Getters, and Setters
    public EmployeeDetailsResponse(String empId, String empName, String empEmail, String currentProject, String manager) {
        this.empId = empId;
        this.empName = empName;
        this.empEmail = empEmail;
        this.currentProject = currentProject;
        this.manager = manager;
    }

    public String getEmpId() {
        return empId;
    }

    public String getEmpName() {
        return empName;
    }

    public String getEmpEmail() {
        return empEmail;
    }

    public String getCurrentProject() {
        return currentProject;
    }

    public String getManager() {
        return manager;
    }
}
