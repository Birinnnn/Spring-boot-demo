package com.studentExample.demo.repository;

import com.studentExample.demo.entity.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@CrossOrigin
public interface LectureRepository extends JpaRepository<Lecture, Long> {

    List<Lecture> findLecturesByInstructorId(long instructorId);


    List<Lecture> findLecturesByStudentsId(long studentId);
}
