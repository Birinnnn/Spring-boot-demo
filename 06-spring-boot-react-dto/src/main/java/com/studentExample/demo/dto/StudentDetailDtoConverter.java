package com.studentExample.demo.dto;

import com.studentExample.demo.entity.StudentDetail;
import org.springframework.stereotype.Component;

@Component
public class StudentDetailDtoConverter {
    public StudentDetailDto convert(StudentDetail studentDetail) {
        if (studentDetail == null) {
            return null;
        }
        return new StudentDetailDto(studentDetail.getGpa(), studentDetail.getHobby());
    }
}
