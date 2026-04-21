package com.EmsServices.system.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Employees")
public class Employees {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "firstName", nullable = false)
    private String firstName;

    @Column(name = "lastName", nullable = false)
    private String lastName;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "salary", nullable = false)
    private Double salary;

    @Column(name = "department", nullable = false)
    private String department;


    public Employees() {}

    public Employees(Long id, String firstName, String lastName, String email, Double salary, String department) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.salary = salary;
        this.department = department;
    }

    // ─── Getters ──────────────────────────────────────────────────────────────

    public Long getId() { return id; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getEmail() { return email; }
    public Double getSalary() { return salary; }
    public String getDepartment() { return department; }

    // ─── Setters ──────────────────────────────────────────────────────────────

    public void setId(Long id) { this.id = id; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public void setEmail(String email) { this.email = email; }
    public void setSalary(Double salary) { this.salary = salary; }
    public void setDepartment(String department) { this.department = department; }
}
