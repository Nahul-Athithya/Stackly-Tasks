package com.EmsServices.system.Services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.EmsServices.system.EmployeeMapper.EmployeeMapper;
import com.EmsServices.system.Entity.Employees;
import com.EmsServices.system.Exception.ResourcesNotFoundException;
import com.EmsServices.system.Repository.EmployeeRepository;
import com.EmsServices.system.dto.EmployeesDto;

@Service
public class EmpServicesImplementations implements EmployeeServices {

    private final EmployeeRepository empRepo;
    private final EmployeeMapper empMapper;

    public EmpServicesImplementations(EmployeeRepository empRepo, EmployeeMapper empMapper) {
        this.empRepo = empRepo;
        this.empMapper = empMapper;
    }

    @Override
    public EmployeesDto createEmployees(EmployeesDto employeesDto) {
        Employees emp = empMapper.mapToNewEmployee(employeesDto);
        Employees createdEmp = empRepo.save(emp);
        return empMapper.mapToEmployeeDto(createdEmp);
    }

    @Override
    public EmployeesDto getEmployeesId(Long id) {
        Employees emp = empRepo.findById(id)
                .orElseThrow(() -> new ResourcesNotFoundException("Employee not found with id: " + id));
        return empMapper.mapToEmployeeDto(emp);
    }

    @Override
    public List<EmployeesDto> getAllEmployees() {
        List<Employees> employees = empRepo.findAll();
        return employees.stream()
                .map(empMapper::mapToEmployeeDto)
                .collect(Collectors.toList());
    }

    @Override
    public EmployeesDto updateEmployees(Long id, EmployeesDto employeesDto) {
        Employees emp = empRepo.findById(id)
                .orElseThrow(() -> new ResourcesNotFoundException("Employee not found with id: " + id));
        emp.setFirstName(employeesDto.getFirstName());
        emp.setLastName(employeesDto.getLastName());
        emp.setEmail(employeesDto.getEmail());
        emp.setSalary(employeesDto.getSalary());
        emp.setDepartment(employeesDto.getDepartment());
        Employees updatedEmp = empRepo.save(emp);
        return empMapper.mapToEmployeeDto(updatedEmp);
    }

    @Override
    public void deleteEmployees(Long id) {
        Employees employee = empRepo.findById(id)
                .orElseThrow(() -> new ResourcesNotFoundException("Employee not found with id: " + id));
        empRepo.delete(employee);
    }

    @Override
    public List<EmployeesDto> getEmployeesByDepartment(String department) {
        List<Employees> employees = empRepo.findByDepartment(department);
        return employees.stream()
                .map(empMapper::mapToEmployeeDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<EmployeesDto> getEmployeesBySalaryRange(Double min, Double max) {
        List<Employees> employees = empRepo.findBySalaryBetween(min, max);
        return employees.stream()
                .map(empMapper::mapToEmployeeDto)
                .collect(Collectors.toList());
    }

    @Override
    public Page<EmployeesDto> getAllEmployeesPaginated(int page, int size) {
        Page<Employees> employeePage = empRepo.findAll(PageRequest.of(page, size));
        return employeePage.map(empMapper::mapToEmployeeDto);
    }
}
