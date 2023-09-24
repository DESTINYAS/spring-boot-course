package com.luv2code.cruddemo;

import com.luv2code.cruddemo.dao.StudentDAO;
import com.luv2code.cruddemo.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}
	@Bean
	public CommandLineRunner commandLineRunner(StudentDAO studentDAO){
		return runner->{

//			createStudent(studentDAO);
			createMultipleStudent(studentDAO);
//			readStudent(studentDAO);

//			queryForStudents(studentDAO);
//			queryForStudentsByLastName(studentDAO);
//			updateStudent(studentDAO);
//			deleteStudent(studentDAO);
//			deleteAllStudents(studentDAO);
		};


	}

	private void deleteAllStudents(StudentDAO studentDAO) {
		System.out.println("Deleting all students....");
		int numOfRows=studentDAO.deleteAll();
		System.out.println("Deleted rows count: "+numOfRows);
	}

	private void deleteStudent(StudentDAO studentDAO) {
		int studentId=3;
		System.out.println("Deleting student with id of: "+studentId);
		studentDAO.delete(studentId);
	}

	private void updateStudent(StudentDAO studentDAO) {
//		retrieve student based on the id: primary key
	int studentId=1;
		System.out.println("Getting student with id: "+studentId);
		Student myStudent=studentDAO.findById(studentId);
//		change firstName to Ninja
		System.out.println("Updating student......");
		myStudent.setFirstName("Ninja");
//		update the student
		studentDAO.update(myStudent);
//		display the updated student
		System.out.println("Updated student: "+myStudent);
	}

	private void queryForStudentsByLastName(StudentDAO studentDAO) {
//		get a list of students
		List<Student> studentList = studentDAO.findByLastname("Oldman");
//		display the list of students
		for (Student tempStudent : studentList) {
			System.out.println(tempStudent);
		}
	}
	private void queryForStudents(StudentDAO studentDAO) {
//		get a list of students
		List<Student> theStudents=studentDAO.findAll();
//		display the students
		for(Student tempStudent:theStudents){
			System.out.println(tempStudent);
		}
	}

	private void readStudent(StudentDAO studentDAO) {
//		create a student object
		System.out.println("Creating a new student");
		Student tempStudent = new Student("Will","Wilberforce","willy@wil.com");
//		save the student
		System.out.println("Saving new student");
		studentDAO.save(tempStudent);
//		display id of the student
		int theId =tempStudent.getId();
		System.out.println("Saved student. Generated id : "+theId);
//		retrieve student based on id : primary key
		System.out.println("Retrieving student with id : "+ theId);
		Student	myStudent = studentDAO.findById(theId);
//		display student
		System.out.println("Found the student "+myStudent);
	}

	private void createMultipleStudent(StudentDAO studentDAO) {
//		create multiple students
		System.out.println("Creating multiple student objects....");
		Student tempStudent1 =new Student("Chief","Donatus","chiefdonatus@luv2code.com");
		Student tempStudent2 =new Student("Olorugun","Oldman","olorogunoldman@luv2code.com");
		Student tempStudent3 =new Student("cletus","barman","cletusbarman@luv2code.com");
//		Save multiple students
		System.out.println("Saving student objects");
		studentDAO.save(tempStudent1);
		studentDAO.save(tempStudent2);
		studentDAO.save(tempStudent3);
	}

	private void createStudent(StudentDAO studentDAO){
//		create the student object
		System.out.println("Creating new student object....");
		Student tempStudent =new Student("Prosper","Titus","prospertitus@luv2code.com");
//		save the student object
		System.out.println("Saving the student...");
		studentDAO.save(tempStudent);
//		display the id of the saved student
		System.out.println("Saved student. Generated id: "+ tempStudent.getId());
	}
}
