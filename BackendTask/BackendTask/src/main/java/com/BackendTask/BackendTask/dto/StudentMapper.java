package com.BackendTask.BackendTask.dto;

import com.BackendTask.BackendTask.entity.Students;

public class StudentMapper {

    public static StudentDto mapToStudentDto(Students student) {
        return new StudentDto(
                student.getRollno(),
                student.getName(),
                student.getPhoneNumber(),
                student.getAge(),
                student.getDepartment()
        );
    }

    public static Students mapToStudent(StudentDto studentDto) {
        return new Students(
                studentDto.getRollno(),
                studentDto.getName(),
                studentDto.getPhoneNumber(),
                studentDto.getAge(),
                studentDto.getDepartment()
        );
    }
}
