package com.simpletask.service;

import com.simpletask.dto.StudentDto;
import com.simpletask.entity.Student;
import com.simpletask.exception.ResourceNotFoundException;
import com.simpletask.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    // ── Create ──────────────────────────────────────────────────
    public StudentDto createStudent(StudentDto dto) {
        Student student = mapToEntity(dto);
        Student saved = studentRepository.save(student);
        return mapToDto(saved);
    }

    // ── Get by ID ────────────────────────────────────────────────
    public StudentDto getStudentById(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Student not found with id: " + id));
        return mapToDto(student);
    }

    // ── Get All ──────────────────────────────────────────────────
    public List<StudentDto> getAllStudents() {
        return studentRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    // ── Update ───────────────────────────────────────────────────
    public StudentDto updateStudent(Long id, StudentDto dto) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Student not found with id: " + id));

        student.setName(dto.getName());
        student.setEmail(dto.getEmail());
        student.setAge(dto.getAge());
        student.setDepartment(dto.getDepartment());

        Student updated = studentRepository.save(student);
        return mapToDto(updated);
    }

    // ── Delete ───────────────────────────────────────────────────
    public void deleteStudent(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Student not found with id: " + id));
        studentRepository.delete(student);
    }

    // ── Mappers ──────────────────────────────────────────────────
    private Student mapToEntity(StudentDto dto) {
        Student s = new Student();
        s.setName(dto.getName());
        s.setEmail(dto.getEmail());
        s.setAge(dto.getAge());
        s.setDepartment(dto.getDepartment());
        return s;
    }

    private StudentDto mapToDto(Student s) {
        StudentDto dto = new StudentDto();
        dto.setId(s.getId());
        dto.setName(s.getName());
        dto.setEmail(s.getEmail());
        dto.setAge(s.getAge());
        dto.setDepartment(s.getDepartment());
        return dto;
    }
}
