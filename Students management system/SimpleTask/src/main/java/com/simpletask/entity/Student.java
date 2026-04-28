package com.simpletask.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "age")
    private int age;

    @Column(name = "department")
    private String department;

    // ── Constructors ─────────────────────────────────────────────
    public Student() {}

    public Student(Long id, String name, String email, int age, String department) {
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

    public int getAge()                   { return age; }
    public void setAge(int age)           { this.age = age; }

    public String getDepartment()                   { return department; }
    public void setDepartment(String department)    { this.department = department; }
}
