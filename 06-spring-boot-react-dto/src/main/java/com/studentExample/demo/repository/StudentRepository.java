package com.studentExample.demo.repository;

import com.studentExample.demo.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@CrossOrigin
public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findStudentsByLecturesId(long lectureId);

}
