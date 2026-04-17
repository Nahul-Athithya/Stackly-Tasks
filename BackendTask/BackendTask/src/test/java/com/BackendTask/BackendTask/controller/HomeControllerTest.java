package com.BackendTask.BackendTask.controller;

import com.BackendTask.BackendTask.dto.StudentDto;
import com.BackendTask.BackendTask.exception.GlobalExceptionHandler;
import com.BackendTask.BackendTask.exception.ResourceNotFoundException;
import com.BackendTask.BackendTask.services.servicesInterface.StudentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class HomeControllerTest {

    private MockMvc mockMvc;

    @Mock
    private StudentService studentService;

    @InjectMocks
    private HomeController homeController;

    private ObjectMapper objectMapper = new ObjectMapper();

    private StudentDto validStudentDto;
    private StudentDto invalidStudentDto;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(homeController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();

        validStudentDto = new StudentDto(1L, "Rahul", "1234567890", 20, "CS");
        // Invalid: Empty name, invalid phone number length, age < 16
        invalidStudentDto = new StudentDto(2L, "", "123", 10, "CS");
    }

    @Test
    public void createStudent_ValidInputTest() throws Exception {
        when(studentService.createStudent(any(StudentDto.class))).thenReturn(validStudentDto);

        mockMvc.perform(post("/api/students")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(validStudentDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Rahul"));
    }

    @Test
    public void createStudent_InvalidInputTest() throws Exception {
        mockMvc.perform(post("/api/students")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(invalidStudentDto)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.name").exists())
                .andExpect(jsonPath("$.phoneNumber").exists())
                .andExpect(jsonPath("$.age").exists());
    }

    @Test
    public void getStudentById_FoundTest() throws Exception {
        when(studentService.getStudentById(1L)).thenReturn(validStudentDto);

        mockMvc.perform(get("/api/students/{rollno}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Rahul"));
    }

    @Test
    public void getStudentById_NotFoundTest() throws Exception {
        when(studentService.getStudentById(99L))
                .thenThrow(new ResourceNotFoundException("Student", "rollno", 99L));

        mockMvc.perform(get("/api/students/{rollno}", 99L))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.errorCode").value("RESOURCE_NOT_FOUND"));
    }

    @Test
    public void getAllStudentsTest() throws Exception {
        when(studentService.getAllStudents()).thenReturn(Arrays.asList(validStudentDto));

        mockMvc.perform(get("/api/students"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Rahul"));
    }

    @Test
    public void updateStudent_ValidInputTest() throws Exception {
        validStudentDto.setName("Rahul Updated");
        when(studentService.updateStudent(eq(1L), any(StudentDto.class))).thenReturn(validStudentDto);

        mockMvc.perform(put("/api/students/{rollno}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(validStudentDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Rahul Updated"));
    }
}
