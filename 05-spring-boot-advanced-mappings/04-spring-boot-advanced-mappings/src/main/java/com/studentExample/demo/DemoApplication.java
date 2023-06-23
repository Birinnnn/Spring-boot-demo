package com.studentExample.demo;

import com.studentExample.demo.dao.AppDAO;
import com.studentExample.demo.dao.LectureRepository;
import com.studentExample.demo.dao.StudentDetailRepository;
import com.studentExample.demo.dao.StudentRepository;
import com.studentExample.demo.entity.Lecture;
import com.studentExample.demo.entity.Student;
import com.studentExample.demo.entity.StudentDetail;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AppDAO appDAO) {

		return runner -> {
			createStudent(appDAO);

			findStudent(appDAO);

			deleteStudent(appDAO);

			createEmptyLectures(appDAO);

			createLectureWithStudents(appDAO);

			findLecture(appDAO);
		};
	}

	private void createStudent(AppDAO appDAO) {
		//Creating objects
		Student stu = new Student("Osman", "Sonmez", "asdasd@hotmail.com");
		StudentDetail detail = new StudentDetail(3.5, "Soccer");

		// Setting the relationship
		stu.setStudentDetail(detail);
		// Saving the object in the database

		appDAO.save(stu);

		System.out.println("Student saved");
	}

	private void findStudent(AppDAO appDAO) {
		int id = 5;
		//Finding objects
		System.out.println("Finding student with id: " + id);

		Student student = appDAO.findStudentById(id);

		if(student != null) {
			System.out.println("Student found");
			System.out.println(student);
			System.out.println("Now Trying same thing for student detail");
			StudentDetail detail =  student.getStudentDetail();
			if(detail != null)
				System.out.println(detail);
			else
				System.out.println("Student detail not found");
		}
		else
			System.out.println("Student not found");
	}

	private void deleteStudent(AppDAO appDAO){
		//Deleting objects
		int id = 5;
		System.out.println("Deleting student with id: " + id);
		appDAO.deleteStudentById(id);
		System.out.println("Student deleted");
	}

	private void createEmptyLectures(AppDAO appDAO) {
		//Creating objects
		Lecture tempLec1 = new Lecture("Math");
		Lecture tempLec2 = new Lecture("Physics");
		Lecture tempLec3 = new Lecture("Chemistry");
		Lecture tempLec4 = new Lecture("Biology");

		// Saving the lectures
		appDAO.save(tempLec1);
		appDAO.save(tempLec2);
		appDAO.save(tempLec3);
		appDAO.save(tempLec4);

		System.out.println("Lectures saved");
	}

	private void createLectureWithStudents(AppDAO appDAO) {
		//Creating objects
		Lecture tempLec1 = new Lecture("Math2");

		// Saving the lecture
		appDAO.save(tempLec1);

		System.out.println("Lectures created");
		System.out.println("Finding students");

		// Finding students
		List<Integer> studentIds = List.of(1, 2, 3);
		List<Student> students = appDAO.findAllStudentsById(studentIds);

		System.out.println("Students found");
		// Adding students to the lectures
		for (Student student : students) {
			appDAO.addLectureToStudent(student.getId(), tempLec1.getId());
			appDAO.update(tempLec1); // Save each lecture to update the relationship
			appDAO.update(student); // Save each student to update the relationship
		}


		System.out.println("Students added to lectures");
		// Saving the students
		System.out.println(tempLec1.getStudents());


		System.out.println("Lectures created with students");
	}

	public void findLecture(AppDAO appDAO) {
		int lectureId = 5; // ID of the lecture you want to find

		Lecture lecture = appDAO.findLectureById(lectureId);
		if (lecture != null) {
			System.out.println("Lecture found:");
			System.out.println("ID: " + lecture.getId());
			System.out.println("Title: " + lecture.getTitle());
			System.out.println("Students enrolled:");

			List<Integer> myList = appDAO.findStudentsByLectureId(lectureId);
			for (Integer studentId : myList) {
				Student student = appDAO.findStudentById(studentId);
				System.out.println(" - " + student.getFirstName() + " " + student.getLastName());
			}
		} else {
			System.out.println("Lecture not found");
		}
	}
}
