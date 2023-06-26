package com.studentExample.demo.repository;

import com.studentExample.demo.entity.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
public interface InstructorRepository extends JpaRepository<Instructor, Long> {
}
