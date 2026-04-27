package com.simpletask.dto;

import jakarta.validation.constraints.*;

public class StudentDto {

    private Long id;

    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Email must be a valid email address")
    private String email;

    @NotNull(message = "Age is required")
    @Min(value = 18, message = "Age must be at least 18")
    @Max(value = 60, message = "Age must not exceed 60")
    private Integer age;

    @NotBlank(message = "Department is required")
    private String department;

    // ── Constructors ─────────────────────────────────────────────
    public StudentDto() {}

    public StudentDto(Long id, String name, String email, Integer age, String department) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.age = age;
        this.department = department;
    }

    // ── Getters & Setters ────────────────────────────────────────
    public Long getId()                   { return id; }
    public void setId(Long id)            { this.id = id; }

    public String getName()               { return name; }
    public void setName(String name)      { this.name = name; }

    public String getEmail()              { return email; }
    public void setEmail(String email)    { this.email = email; }

    public Integer getAge()               { return age; }
    public void setAge(Integer age)       { this.age = age; }

    public String getDepartment()                   { return department; }
    public void setDepartment(String department)    { this.department = department; }
}
