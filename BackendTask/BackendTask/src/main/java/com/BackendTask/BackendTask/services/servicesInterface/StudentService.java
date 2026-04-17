package com.BackendTask.BackendTask.services.servicesInterface;

import com.BackendTask.BackendTask.dto.StudentDto;

import java.util.List;

public interface StudentService {
    StudentDto createStudent(StudentDto studentDto);
    StudentDto getStudentById(Long rollno);
    List<StudentDto> getAllStudents();
    StudentDto updateStudent(Long rollno, StudentDto studentDto);
    StudentDto updateStudentPartially(Long rollno, StudentDto studentDto);
    void deleteStudent(Long rollno);
}
