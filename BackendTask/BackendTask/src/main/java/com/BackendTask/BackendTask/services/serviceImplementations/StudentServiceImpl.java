package com.BackendTask.BackendTask.services.serviceImplementations;

import com.BackendTask.BackendTask.dto.StudentDto;
import com.BackendTask.BackendTask.dto.StudentMapper;
import com.BackendTask.BackendTask.entity.Students;
import com.BackendTask.BackendTask.repository.StudentsRepo;
import com.BackendTask.BackendTask.services.servicesInterface.StudentService;
import com.BackendTask.BackendTask.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentsRepo studentsRepo;

    public StudentServiceImpl(StudentsRepo studentsRepo) {
        this.studentsRepo = studentsRepo;
    }

    @Override
    public StudentDto createStudent(StudentDto studentDto) {
        Students student = StudentMapper.mapToStudent(studentDto);
        Students savedStudent = studentsRepo.save(student);
        return StudentMapper.mapToStudentDto(savedStudent);
    }

    @Override
    public StudentDto getStudentById(Long rollno) {
        Students student = studentsRepo.findById(rollno)
                .orElseThrow(() -> new ResourceNotFoundException("Student", "rollno", rollno));
        return StudentMapper.mapToStudentDto(student);
    }

    @Override
    public List<StudentDto> getAllStudents() {
        List<Students> students = studentsRepo.findAll();
        return students.stream()
                .map(StudentMapper::mapToStudentDto)
                .collect(Collectors.toList());
    }

    @Override
    public StudentDto updateStudent(Long rollno, StudentDto studentDto) {
        Students student = studentsRepo.findById(rollno)
                .orElseThrow(() -> new ResourceNotFoundException("Student", "rollno", rollno));

        student.setName(studentDto.getName());
        student.setPhoneNumber(studentDto.getPhoneNumber());
        student.setAge(studentDto.getAge());
        student.setDepartment(studentDto.getDepartment());

        Students updatedStudent = studentsRepo.save(student);
        return StudentMapper.mapToStudentDto(updatedStudent);
    }

    @Override
    public StudentDto updateStudentPartially(Long rollno, StudentDto studentDto) {
        Students student = studentsRepo.findById(rollno)
                .orElseThrow(() -> new ResourceNotFoundException("Student", "rollno", rollno));

        if (studentDto.getName() != null) {
            student.setName(studentDto.getName());
        }
        if (studentDto.getPhoneNumber() != null) {
            student.setPhoneNumber(studentDto.getPhoneNumber());
        }
        if (studentDto.getAge() != 0) {
            student.setAge(studentDto.getAge());
        }
        if (studentDto.getDepartment() != null) {
            student.setDepartment(studentDto.getDepartment());
        }

        Students updatedStudent = studentsRepo.save(student);
        return StudentMapper.mapToStudentDto(updatedStudent);
    }

    @Override
    public void deleteStudent(Long rollno) {
        Students student = studentsRepo.findById(rollno)
                .orElseThrow(() -> new ResourceNotFoundException("Student", "rollno", rollno));
        studentsRepo.delete(student);
    }
}
