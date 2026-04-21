package com.EmsServices.system.EmployeeMapper;

import org.springframework.stereotype.Component;

import com.EmsServices.system.Entity.Employees;
import com.EmsServices.system.dto.EmployeesDto;

@Component
public class EmployeeMapper {

    public EmployeesDto mapToEmployeeDto(Employees employee) {
        if (employee == null) {
            return null;
        }
        return new EmployeesDto(
            employee.getId(),
            employee.getFirstName(),
            employee.getLastName(),
            employee.getEmail(),
            employee.getSalary(),
            employee.getDepartment()
        );
    }

    public Employees mapToNewEmployee(EmployeesDto dto) {
        if (dto == null) {
            return null;
        }
        Employees emp = new Employees();
        emp.setFirstName(dto.getFirstName());
        emp.setLastName(dto.getLastName());
        emp.setEmail(dto.getEmail());
        emp.setSalary(dto.getSalary());
        emp.setDepartment(dto.getDepartment());
        return emp;
    }
}
