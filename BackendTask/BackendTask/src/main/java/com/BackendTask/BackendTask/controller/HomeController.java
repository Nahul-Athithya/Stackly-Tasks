package com.BackendTask.BackendTask.controller;

import com.BackendTask.BackendTask.dto.StudentDto;
import com.BackendTask.BackendTask.services.servicesInterface.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class HomeController {

    private final StudentService studentService;

    public HomeController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<StudentDto> createStudent(@RequestBody StudentDto studentDto) {
        StudentDto savedStudent = studentService.createStudent(studentDto);
        return new ResponseEntity<>(savedStudent, HttpStatus.CREATED);
    }

    @GetMapping("/{rollno}")
    public ResponseEntity<StudentDto> getStudentById(@PathVariable("rollno") Long rollno) {
        StudentDto studentDto = studentService.getStudentById(rollno);
        return ResponseEntity.ok(studentDto);
    }

    @GetMapping
    public ResponseEntity<List<StudentDto>> getAllStudents() {
        List<StudentDto> students = studentService.getAllStudents();
        return ResponseEntity.ok(students);
    }

    @PutMapping("/{rollno}")
    public ResponseEntity<StudentDto> updateStudent(@PathVariable("rollno") Long rollno, @RequestBody StudentDto studentDto) {
        StudentDto updatedStudent = studentService.updateStudent(rollno, studentDto);
        return ResponseEntity.ok(updatedStudent);
    }

    @PatchMapping("/{rollno}")
    public ResponseEntity<StudentDto> updateStudentPartially(@PathVariable("rollno") Long rollno, @RequestBody StudentDto studentDto) {
        StudentDto updatedStudent = studentService.updateStudentPartially(rollno, studentDto);
        return ResponseEntity.ok(updatedStudent);
    }

    @DeleteMapping("/{rollno}")
    public ResponseEntity<String> deleteStudent(@PathVariable("rollno") Long rollno) {
        studentService.deleteStudent(rollno);
        return ResponseEntity.ok("Student deleted successfully.");
    }
}
