package com.studentExample.demo.controller;

import com.studentExample.demo.repository.StudentRepository;
import com.studentExample.demo.dto.StudentDto;
import com.studentExample.demo.dto.CreateStudentRequest;
import com.studentExample.demo.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/student")
public class CustomStudentController {

    private final StudentService studentService;


    public CustomStudentController(StudentService studentService, StudentRepository studentRepository) {
        this.studentService = studentService;
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<StudentDto> createStudent(@RequestBody CreateStudentRequest request) {
        System.out.println("request: " + request);
        return ResponseEntity.ok(studentService.createStudent(request));
    }
}
