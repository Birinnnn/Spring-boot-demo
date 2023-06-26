package com.studentExample.demo;

import com.studentExample.demo.entity.Instructor;
import com.studentExample.demo.entity.Lecture;
import com.studentExample.demo.entity.Student;
import com.studentExample.demo.entity.StudentDetail;
import com.studentExample.demo.service.AppService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class DemoApplication {


	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AppService service) {

		return runner -> {
			createStudent(service);

			findStudent(service);

			deleteStudent(service);

			createEmptyLectures(service);

			createLectureAndStudents(service);

			findLecture(service);

			findLecturesOfStudent(service);
		};
	}

	private void createStudent(AppService service) {
		//Creating objects
		Student stu = new Student("Osman", "Sonmez", "asdasd@hotmail.com");
		StudentDetail detail = new StudentDetail(3.5, "Soccer");

		// Setting the relationship
		stu.setStudentDetail(detail);
		// Saving the object in the database

		service.saveStudent(stu);

		System.out.println("Student saved");
	}

	private void findStudent(AppService service) {
		int id = 5;
		//Finding objects
		System.out.println("Finding student with id: " + id);

		Student student = service.findStudentById(id);

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

	private void deleteStudent(AppService service){
		//Deleting objects
		int id = 5;
		System.out.println("Deleting student with id: " + id);
		service.deleteStudentById(id);
		System.out.println("Student deleted");
	}

	private void createEmptyLectures(AppService service) {
		//Creating objects
		Instructor tempIns = new Instructor("Ahmet", "Yilmaz", "");
		Lecture tempLec1 = new Lecture("Math", tempIns);
		Lecture tempLec2 = new Lecture("Physics", tempIns);
		Lecture tempLec3 = new Lecture("Chemistry", tempIns);
		Lecture tempLec4 = new Lecture("Biology", tempIns);

		// Saving the lectures
		tempIns.add(tempLec1);
		tempIns.add(tempLec2);
		tempIns.add(tempLec3);
		tempIns.add(tempLec4);

		service.saveInstructor(tempIns);


		System.out.println("Lectures saved");
	}

	private void createLectureAndStudents(AppService service) {
		Instructor tempIns = new Instructor("Veli", "Hoca", "");
		//Creating lecture
		Lecture tempLec1 = new Lecture("Math2", tempIns);

		// Creating the students
		Student stu1 = new Student("Ali", "Veli", "");
		Student stu2 = new Student("Ayse", "Fatma", "hayriye");

		// Creating the student details
		StudentDetail detail1 = new StudentDetail(2.0, "Chess");
		StudentDetail detail2 = new StudentDetail(4.0, "Math");

		// Setting the relationships
		stu1.setStudentDetail(detail1);
		stu2.setStudentDetail(detail2);


		// Adding the students to the lecture
		tempLec1.addStudent(stu1);
		tempLec1.addStudent(stu2);
		stu1.addLecture(tempLec1);
		stu2.addLecture(tempLec1);

		System.out.println("Saving the lecture: " + tempLec1.getTitle());
		System.out.println("associated students: " + tempLec1.getStudents());
		// Save the lecture and students
		service.saveLecture(tempLec1);
	}

	public void findLecture(AppService service) {
		int lectureId = 5; // ID of the lecture you want to find

		Lecture lecture = service.findLectureById(lectureId);
		if (lecture != null) {
			System.out.println("Lecture found:");
			System.out.println("ID: " + lecture.getId());
			System.out.println("Title: " + lecture.getTitle());
			System.out.println("Instructor: " + lecture.getInstructor().getFirstName() + " " + lecture.getInstructor().getLastName());
			System.out.println("Students enrolled:");

			List<Student> myList = service.findStudentsByLectureId(lectureId);
			for (Student student : myList) {
				System.out.println(" - " + student.getFirstName() + " " + student.getLastName());
			}
		} else {
			System.out.println("Lecture not found");
		}
	}

	public void findLecturesOfStudent(AppService appService){
		int studentId = 7; // ID of the student you want to find

		Student student = appService.findStudentById(studentId);
		if (student != null) {
			System.out.println("Student found:");
			System.out.println("ID: " + student.getId());
			System.out.println("First name: " + student.getFirstName());
			System.out.println("Last name: " + student.getLastName());
			System.out.println("Lectures enrolled:");

			List<Lecture> myList = appService.findLecturesByStudentId(studentId);
			for (Lecture lecture : myList) {
				System.out.println(" - " + lecture.getTitle());
			}
		} else {
			System.out.println("Student not found");
		}
	}
}
