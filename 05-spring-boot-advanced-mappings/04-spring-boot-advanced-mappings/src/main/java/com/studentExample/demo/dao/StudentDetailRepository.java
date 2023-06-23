package com.studentExample.demo.dao;

import com.studentExample.demo.entity.StudentDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentDetailRepository extends JpaRepository<StudentDetail, Integer> {

}
