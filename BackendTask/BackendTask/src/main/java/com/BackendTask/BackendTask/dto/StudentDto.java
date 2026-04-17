package com.BackendTask.BackendTask.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class StudentDto {
    
    private Long rollno;

    @NotBlank(message = "Name cannot be empty")
    private String name;

    @NotBlank(message = "Phone number cannot be empty")
    @Pattern(regexp = "^\\d{10}$", message = "Phone number must be exactly 10 digits")
    private String phoneNumber;

    @Min(value = 16, message = "Age must be at least 16")
    @Max(value = 60, message = "Age must not exceed 60")
    private int age;

    @NotBlank(message = "Department cannot be empty")
    private String department;

    public StudentDto() {
    }

    public StudentDto(Long rollno, String name, String phoneNumber, int age, String department) {
        this.rollno = rollno;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.age = age;
        this.department = department;
    }

    public Long getRollno() {
        return rollno;
    }

    public void setRollno(Long rollno) {
        this.rollno = rollno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
