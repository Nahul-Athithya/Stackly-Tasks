package com.EmsServices.system.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.EmsServices.system.Entity.Employees;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employees, Long> {

    List<Employees> findByDepartment(String department);

    List<Employees> findBySalaryBetween(Double minSalary, Double maxSalary);

    Page<Employees> findAll(Pageable pageable);
}
