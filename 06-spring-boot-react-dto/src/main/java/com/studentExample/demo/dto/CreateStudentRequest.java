package com.studentExample.demo.dto;

import com.studentExample.demo.entity.StudentDetail;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Getter
public class CreateStudentRequest {
    String firstName;
    String lastName;
    String email;
    Double gpa;
    String hobby;


    public StudentDetail createStudentDetail() {
        if(Objects.equals(this.hobby, "") && this.gpa == null)
            return null;
        if( this.gpa == null)
            this.gpa = 0.0;
        if( this.gpa > 4.0 || this.gpa < 0.0)
            this.gpa = 0.0;
        return new StudentDetail(this.gpa, this.hobby);
    }
}
