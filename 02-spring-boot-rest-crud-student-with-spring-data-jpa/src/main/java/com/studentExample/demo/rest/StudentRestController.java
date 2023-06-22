package com.studentExample.demo.rest;

import com.studentExample.demo.dao.StudentDAO;
import com.studentExample.demo.entity.Student;
import com.studentExample.demo.service.StudentService;
import com.studentExample.demo.service.StudentServiceImpl;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {

    private StudentService studentService;
    //inject StudentService
    @Autowired
    public StudentRestController(StudentService studentService){
        this.studentService = studentService;
    }

    //Return a list of students
    @GetMapping("/students")
    public List<Student> findAll(){
        return studentService.findAll();

    }

    //Add mapping for GET /students/{studentId}
    @GetMapping("/students/{studentId}")
    public Student getStudent(@PathVariable int studentId){
        Student student = studentService.findById(studentId);

        //Check if found
        if( student == null){
            throw new StudentNotFoundException("Student id not found - " + studentId);
        }

        return student;
    }

    //Add mapping for POST /students/ - add new student
    @PostMapping("/students")
    public Student addStudent(@RequestBody Student theStudent){

        //Also just in case pass an id in JSON ... set id to 0
        //To force a save of new item ... instead of update
        theStudent.setId(0);

        Student dbStudent = studentService.save(theStudent);

        return dbStudent;
    }

    //Add mapping for PUT /students/ - update an existing student
    @PutMapping("/students")
    public Student updateStudent(@RequestBody Student theStudent){
        Student dbStudent = studentService.save(theStudent);

        return dbStudent;
    }


    //Add mapping for DELETE /students/{studentId} - delete an existing student
    @DeleteMapping("/students/{studentId}")
    public String deleteStudent(@PathVariable int studentId){
        Student student = studentService.findById(studentId);

        //Throw exception if student not found
        if(student == null){
            throw new StudentNotFoundException("Student id not found - " + studentId);
        }
        studentService.deleteById(studentId);

        return "Deleted Student with id - " + studentId;
    }


    /*
    // define @PostConstruct to load student data (just once!)

    @PostConstruct
    public void loadStudents(){
        students = new ArrayList<>();
        students.add(new Student("Anıl", "İlağa"));
        students.add(new Student("Onur", "İlağa"));
        students.add(new Student("Öykü", "Başak"));
        students.add(new Student("Semet", "Özata"));
    }
    @GetMapping("/students")
    public List<Student> getStudents(){
        return students;
    }

    // define endpoint for "students/{studentId}" - return student at index

    @GetMapping("/students/{studentId}")
    public Student getStudent(@PathVariable int studentId){

        //Checking student id
        if ((students.size() <= studentId) || studentId < 0){
            throw new StudentNotFoundException("Student id not found - " + studentId);
        }
        return students.get(studentId);
    }
    */


}
