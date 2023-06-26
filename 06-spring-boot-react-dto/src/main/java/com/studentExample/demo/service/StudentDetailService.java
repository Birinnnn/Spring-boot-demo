package com.studentExample.demo.service;

import com.studentExample.demo.repository.StudentDetailRepository;
import com.studentExample.demo.entity.StudentDetail;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentDetailService {

    private final StudentDetailRepository studentDetailRepository;

    public StudentDetailService(StudentDetailRepository studentDetailRepository) {
        this.studentDetailRepository = studentDetailRepository;
    }

    protected void save(StudentDetail studentDetail) {
        studentDetailRepository.save(studentDetail);
    }

    protected void update(StudentDetail studentDetail) {
        studentDetailRepository.save(studentDetail);
    }

    protected void deleteById(long id) {
        studentDetailRepository.deleteById(id);
    }

    protected StudentDetail findById(long id) {
        return studentDetailRepository.findById(id).orElse(null);
    }

    protected List<StudentDetail> findAll() {
        return studentDetailRepository.findAll();
    }


}
