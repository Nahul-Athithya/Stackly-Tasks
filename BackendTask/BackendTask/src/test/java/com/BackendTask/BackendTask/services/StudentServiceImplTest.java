package com.BackendTask.BackendTask.services;

import com.BackendTask.BackendTask.dto.StudentDto;
import com.BackendTask.BackendTask.entity.Students;
import com.BackendTask.BackendTask.exception.ResourceNotFoundException;
import com.BackendTask.BackendTask.repository.StudentsRepo;
import com.BackendTask.BackendTask.services.serviceImplementations.StudentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class StudentServiceImplTest {

    @Mock
    private StudentsRepo studentsRepo;

    @InjectMocks
    private StudentServiceImpl studentService;

    private Students student;
    private StudentDto studentDto;

    @BeforeEach
    public void setup() {
        student = new Students(1L, "Rahul", "1234567890", 20, "CS");
        studentDto = new StudentDto(1L, "Rahul", "1234567890", 20, "CS");
    }

    @Test
    public void createStudentTest() {
        when(studentsRepo.save(any(Students.class))).thenReturn(student);

        StudentDto savedDto = studentService.createStudent(studentDto);

        assertNotNull(savedDto);
        assertEquals("Rahul", savedDto.getName());
        verify(studentsRepo, times(1)).save(any(Students.class));
    }

    @Test
    public void getStudentById_FoundTest() {
        when(studentsRepo.findById(1L)).thenReturn(Optional.of(student));

        StudentDto foundDto = studentService.getStudentById(1L);

        assertNotNull(foundDto);
        assertEquals(1L, foundDto.getRollno());
    }

    @Test
    public void getStudentById_NotFoundTest() {
        when(studentsRepo.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> studentService.getStudentById(1L));
    }

    @Test
    public void getAllStudentsTest() {
        when(studentsRepo.findAll()).thenReturn(Arrays.asList(student));

        List<StudentDto> studentList = studentService.getAllStudents();

        assertEquals(1, studentList.size());
    }

    @Test
    public void updateStudentTest() {
        when(studentsRepo.findById(1L)).thenReturn(Optional.of(student));
        when(studentsRepo.save(any(Students.class))).thenReturn(student);

        studentDto.setName("Rahul Updated");
        StudentDto updatedDto = studentService.updateStudent(1L, studentDto);

        assertNotNull(updatedDto);
        verify(studentsRepo, times(1)).save(any(Students.class));
    }
    
    @Test
    public void deleteStudentTest() {
        when(studentsRepo.findById(1L)).thenReturn(Optional.of(student));
        
        studentService.deleteStudent(1L);
        
        verify(studentsRepo, times(1)).delete(student);
    }
}
