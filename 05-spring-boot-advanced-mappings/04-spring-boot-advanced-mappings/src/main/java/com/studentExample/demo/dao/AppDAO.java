package com.studentExample.demo.dao;

import com.studentExample.demo.entity.Lecture;
import com.studentExample.demo.entity.Student;

import java.util.List;

public interface AppDAO {

    void save(Student theStudent);

    void save(Lecture theLecture);

    Student findStudentById(int theId);

    void deleteStudentById(int theId);

    List<Student> findAllStudentsById(List<Integer> list);

    Lecture findLectureById(int theId);

    void update(Student theStudent);

    void update(Lecture theLecture);

    void addLectureToStudent(int studentId, int lectureId);

    List<Integer> findStudentsByLectureId(int lectureId);


}
