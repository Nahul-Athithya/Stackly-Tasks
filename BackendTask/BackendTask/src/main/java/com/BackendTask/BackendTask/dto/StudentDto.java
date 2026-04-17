package com.BackendTask.BackendTask.dto;

public class StudentDto {
    
    private Long rollno;
    private String name;
    private String phoneNumber;
    private int age;
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
