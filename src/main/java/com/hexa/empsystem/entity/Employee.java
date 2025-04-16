package com.hexa.empsystem.entity;

public class Employee {
 private String empId;
 private String empName;
 private String empEmail;

 // Constructors, Getters, and Setters
 public Employee(String empId, String empName, String empEmail) {
     this.empId = empId;
     this.empName = empName;
     this.empEmail = empEmail;
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
}
