package com.studentExample.demo.service;

import com.studentExample.demo.repository.InstructorRepository;
import com.studentExample.demo.repository.LectureRepository;
import com.studentExample.demo.entity.Instructor;
import com.studentExample.demo.entity.Lecture;
import com.studentExample.demo.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AppService{

    private final StudentService studentService;
    private final LectureRepository lectureRepository;
    private final InstructorRepository instructorRepository;

    @Autowired
    public AppService(
            StudentService studentService,
            LectureRepository lectureRepository,
            InstructorRepository instructorRepository) {
        this.studentService = studentService;
        this.lectureRepository = lectureRepository;
        this.instructorRepository = instructorRepository;
    }

    public void saveStudent(Student student) {
        studentService.save(student);
    }

    public Student findStudentById(long id) {
        return studentService.findById(id);
    }

    public List<Student> findAllStudents() {
        return studentService.findAll();
    }

    public void deleteStudentById(long id) {
        studentService.deleteById(id);
    }

    public void saveLecture(Lecture lecture) {
        lectureRepository.save(lecture);
    }

    public Lecture findLectureById(long id) {
        return lectureRepository.findById(id).orElse(null);
    }

    public List<Lecture> findAllLectures() {
        return lectureRepository.findAll();
    }

    public void deleteLectureById(long id) {
        lectureRepository.deleteById(id);
    }

    public void updateStudent(Student student) {
        studentService.save(student);
    }

    public void updateLecture(Lecture lecture) {
        lectureRepository.save(lecture);
    }

    public void saveInstructor(Instructor instructor) {
        instructorRepository.save(instructor);
    }

    public List<Lecture> findLecturesByInstructorId(int instructorId) {
        return lectureRepository.findLecturesByInstructorId(instructorId);
    }

    public List<Student> findStudentsByLectureId(int lectureId) {
        return studentService.findStudentsByLecturesId(lectureId);
    }

    public List<Lecture> findLecturesByStudentId(int studentId) {
        return lectureRepository.findLecturesByStudentsId(studentId);
    }
}
