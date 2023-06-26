package com.studentExample.demo.dto;

import com.studentExample.demo.entity.Student;
import org.springframework.stereotype.Component;

@Component
public class StudentDtoConverter {

    private final StudentDetailDtoConverter studentDetailDtoConverter;

    public StudentDtoConverter(StudentDetailDtoConverter studentDetailDtoConverter) {
        this.studentDetailDtoConverter = studentDetailDtoConverter;
    }

    public StudentDto convert(Student student) {
        return new StudentDto(student.getFirstName(), student.getLastName(),
                student.getEmail(), studentDetailDtoConverter.convert(student.getStudentDetail()));
    }
}
