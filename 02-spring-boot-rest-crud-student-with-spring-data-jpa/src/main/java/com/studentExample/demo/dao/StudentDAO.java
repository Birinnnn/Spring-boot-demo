package com.studentExample.demo.dao;

import com.studentExample.demo.entity.Student;

import java.util.List;

public interface StudentDAO {

    List<Student> findAll();

    Student findById(int theId);

    Student save(Student theStudent);

    void deleteById(int theId);
}
