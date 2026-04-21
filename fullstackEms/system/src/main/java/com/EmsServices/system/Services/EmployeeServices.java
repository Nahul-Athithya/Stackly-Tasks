package com.EmsServices.system.Services;

import java.util.List;

import org.springframework.data.domain.Page;

import com.EmsServices.system.dto.EmployeesDto;

public interface EmployeeServices {

    EmployeesDto createEmployees(EmployeesDto employeesDto);

    EmployeesDto getEmployeesId(Long id);

    List<EmployeesDto> getAllEmployees();

    EmployeesDto updateEmployees(Long id, EmployeesDto employeesDto);

    void deleteEmployees(Long id);

    List<EmployeesDto> getEmployeesByDepartment(String department);

    List<EmployeesDto> getEmployeesBySalaryRange(Double min, Double max);

    Page<EmployeesDto> getAllEmployeesPaginated(int page, int size);
}
