package com.studentExample.demo.dto;


import jakarta.persistence.Entity;


public class StudentDto{
    private String firstName;
    private String lastName;
    private String email;
    private StudentDetailDto studentDetailDto;

    public StudentDto(String firstName, String lastName, String email, StudentDetailDto studentDetailDto) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.studentDetailDto = studentDetailDto;
    }
}


