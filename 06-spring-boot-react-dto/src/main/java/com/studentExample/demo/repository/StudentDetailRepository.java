package com.studentExample.demo.repository;

import com.studentExample.demo.entity.StudentDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
public interface StudentDetailRepository extends JpaRepository<StudentDetail, Long> {

}
