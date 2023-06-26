package com.studentExample.demo.service;

import com.studentExample.demo.repository.StudentRepository;
import com.studentExample.demo.dto.CreateStudentRequest;
import com.studentExample.demo.dto.StudentDto;
import com.studentExample.demo.dto.StudentDtoConverter;
import com.studentExample.demo.entity.Student;
import com.studentExample.demo.entity.StudentDetail;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final StudentDetailService studentDetailService;
    private final StudentDtoConverter converter;

    public StudentService(StudentRepository studentRepository, StudentDetailService studentDetailService, StudentDtoConverter converter) {
        this.studentRepository = studentRepository;
        this.studentDetailService = studentDetailService;
        this.converter = converter;
    }

    protected void save(Student student) {
        studentRepository.save(student);

    }

    protected void update(Student student) {
        studentRepository.save(student);
    }

    protected void deleteById(long id) {
        studentRepository.deleteById(id);
    }

    protected Student findById(long id) {
        return studentRepository.findById(id).orElse(null);
    }

    protected List<Student> findAll() {
        return studentRepository.findAll();
    }

    public List<Student> findStudentsByLecturesId(int lectureId) {
        return studentRepository.findStudentsByLecturesId(lectureId);
    }

    public StudentDto createStudent(CreateStudentRequest createStudentRequest){
        StudentDetail detail = createStudentRequest.createStudentDetail();

        Student student = new Student(createStudentRequest.getFirstName(), createStudentRequest.getLastName(),
                            createStudentRequest.getEmail());

        if(detail != null){
            System.out.println("Gpa: " + detail.getGpa() + "Hobby: " + detail.getHobby());
            student.setStudentDetail(detail);
        }

        return converter.convert(studentRepository.save(student));
    }
}
