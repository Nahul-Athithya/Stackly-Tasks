package com.EmsServices.system.Controller;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.EmsServices.system.Services.EmployeeServices;
import com.EmsServices.system.dto.EmployeesDto;

import java.util.List;

@RestController
@RequestMapping("/employee/api")
public class EmpController {

    private final EmployeeServices empServices;

    public EmpController(EmployeeServices empServices) {
        this.empServices = empServices;
    }


//-----------------------------create employee------------------------------------------------
    
    @PostMapping
    public ResponseEntity<EmployeesDto> createEmployee(@Valid @RequestBody EmployeesDto employeesDto) {
        EmployeesDto savedEmp = empServices.createEmployees(employeesDto);
        return new ResponseEntity<>(savedEmp, HttpStatus.CREATED);
    }


//----------------------------get emp by id----------------------------------------------------
    @GetMapping("/{id}")
    public ResponseEntity<EmployeesDto> getEmployeesById(@PathVariable("id") Long id) {
        EmployeesDto empDto = empServices.getEmployeesId(id);
        return new ResponseEntity<>(empDto, HttpStatus.OK);
    }


//--------------------------------------get all emp---------------------------------------------------
    @GetMapping
    public ResponseEntity<List<EmployeesDto>> getAllEmployees() {
        List<EmployeesDto> empDto = empServices.getAllEmployees();
        return new ResponseEntity<>(empDto, HttpStatus.OK);
    }


//----------------------------------------update emp(put map)---------------------------------------------------
    @PutMapping("/{id}")
    public ResponseEntity<EmployeesDto> updateEmployeeById(
            @PathVariable Long id,
            @Valid @RequestBody EmployeesDto empDto) {
        EmployeesDto empDtoToUpdate = empServices.updateEmployees(id, empDto);
        return new ResponseEntity<>(empDtoToUpdate, HttpStatus.OK);
    }


//-------------------------------------------delete emp----------------------------------------
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployeesById(@PathVariable Long id) {
        empServices.deleteEmployees(id);
        return new ResponseEntity<>("Employee with id " + id + " deleted successfully", HttpStatus.OK);
    }



    @GetMapping("/search/department")
    public ResponseEntity<List<EmployeesDto>> getByDepartment(@RequestParam String department) {
        List<EmployeesDto> employees = empServices.getEmployeesByDepartment(department);
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

  

    @GetMapping("/search/salary")
    public ResponseEntity<List<EmployeesDto>> getBySalaryRange(
            @RequestParam Double min,
            @RequestParam Double max) {
        List<EmployeesDto> employees = empServices.getEmployeesBySalaryRange(min, max);
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

   

    @GetMapping("/page")
    public ResponseEntity<Page<EmployeesDto>> getAllPaginated(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        Page<EmployeesDto> employeePage = empServices.getAllEmployeesPaginated(page, size);
        return new ResponseEntity<>(employeePage, HttpStatus.OK);
    }
}
