package com.luv2code.cruddemo;

import com.luv2code.cruddemo.dao.AppDAO;
import com.luv2code.cruddemo.entity.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;

import java.util.List;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}
	@Bean
	CommandLineRunner commandLineRunner(AppDAO appDAO){
		return  runner ->{
//			createCourseAndStudent(appDAO);
//			findCourseAndStudents(appDAO);
//			findStudentAndCourses(appDAO);
//			addMoreCoursesForStudent(appDAO);
//			deleteCourse(appDAO);
			deleteStudent(appDAO);
		};
	}

	private void deleteStudent(AppDAO appDAO) {
		int theId=1;
		System.out.println("Deleting student with id: "+theId);
		appDAO.deleteStudentById(theId);
		System.out.println("Done");
	}

	private void addMoreCoursesForStudent(AppDAO appDAO) {
		int theId=2;
		Student student=appDAO.findStudentAndCourseByStudentId(theId);
		//create more courses
		Course course1=new Course("NSE");
		Course course2=new Course("PHD");
		Course course3=new Course("NIMECHI");
		student.addCourse(course1);
		student.addCourse(course2);
		student.addCourse(course3);
		System.out.println("updating student: "+student);
		System.out.println("associated courses: "+student.getCourses());
		appDAO.update(student);
		System.out.println("Done");
	}

	private void findStudentAndCourses(AppDAO appDAO) {
		int theId=2;
		Student student =appDAO.findStudentAndCourseByStudentId(theId);
		System.out.println("Loaded student: "+student);
		System.out.println("Associated courses: "+student.getCourses());
		System.out.println("Done");
	}


	private void findCourseAndStudents(AppDAO appDAO) {
		int theId=10;
		Course course =appDAO.findCourseAndStudentByCourseId(theId);
		System.out.println("Loaded course: "+course);
		System.out.println("Associated student: "+course.getStudents());
		System.out.println("Done");
	}

	private void createCourseAndStudent(AppDAO appDAO) {
		//create a course
		Course course=new Course("COREN");
		//create the students
		Student student1=new Student("Abuja","Lagos","abuja@lag.com");
		Student student2=new Student("Delta","Benin","delta@ben.com");
		Student student3=new Student("Agbor","Uni","agbor@uni.com");
		//add students to the courses
		course.addStudent(student1);
		course.addStudent(student2);
		course.addStudent(student3);
		//save the course and associated students
		System.out.println("Saving course "+course);
		System.out.println("Associated students "+course.getStudents());
		appDAO.save(course);
		System.out.println("Done");
	}

	private void deleteCourseAndReviews(AppDAO appDAO) {
		int theId = 10;
		System.out.println("deleting course id: "+theId);
		appDAO.deleteCourseById(theId);
		System.out.println("Done!");
	}

	private void retrieveCourseAndReviews(AppDAO appDAO) {
		int theId=10;
		//get the course and reviews
		Course course=appDAO.findCourseAndReviewsByCourseId(theId);
		//print the course
		System.out.println(course);
		//print the reviews
		System.out.println(course.getReviews());
		System.out.println("Done");
	}

	private void createCourseAndReviews(AppDAO appDAO) {
		//create a course
		Course course=new Course("The Ultimate Guide");
		//add some reviews
		course.addReview(new Review("Great course!"));
		course.addReview(new Review("Invaluable knowledge!"));
		course.addReview(new Review("Wooh Nice!"));
		course.addReview(new Review("poor presentation!"));
		//save the course ... and leverage the cascade all
		System.out.println("Saving the course");
		System.out.println(course);
		System.out.println(course.getReviews());
		appDAO.save(course);
		System.out.println("Done");
	}

	private void deleteCourse(AppDAO appDAO) {
		int theId = 10;
		System.out.println("Deleting course id: "+theId);
		appDAO.deleteCourseById(theId);
		System.out.println("Done");
	}

	private void updateCourse(AppDAO appDAO) {
		int theId = 10;
		// find the course
		System.out.println("Finding course with id: "+theId);
		Course course = appDAO.findCourseByid(theId);
		//update course
		System.out.println("Updating course with id: "+theId);
		course.setTitle("Enjoy the simple things");
		appDAO.update(course);
		System.out.println("Done ");
	}

	private void updateInstructor(AppDAO appDAO) {
		int theId = 1;
		//find the instructor
		System.out.println("finding instructor with id: "+theId);
		Instructor instructor=appDAO.findInstructorById(theId);
		//update instructor
		instructor.setLastName("RONALDO");
		appDAO.update(instructor);
		System.out.println("Done!");
	}

	private void findInstructorWithCoursesJoinFetch(AppDAO appDAO) {
		int theId=1;
		// find the instructor
		System.out.println("Finding instructor id: "+theId);
		Instructor instructor = appDAO.findInstructorByIdJoinFetch(theId);
		System.out.println("Instructor : "+instructor);
		System.out.println("Instructor courses: "+instructor.getCourses());
		System.out.println("Instructor Detail: "+instructor.getInstructorDetail());
		System.out.println("Done");
	}

	private void findCoursesForInstructor(AppDAO appDAO) {
		int theId = 1;
		System.out.println("Finding instructor id: "+theId);
		Instructor instructor =appDAO.findInstructorById(theId);
		System.out.println("instructor: "+instructor);
		// find courses for instructor
		System.out.println("Finding courses for instructor id: "+theId);
		List<Course> courses = appDAO.findCoursesByInstructorId(theId);
		//associate object
		instructor.setCourses(courses);
		System.out.println("Instructor courses: "+ instructor.getCourses());
		System.out.println("Done");
	}

	private void findInstructorWithCourses(AppDAO appDAO) {
		int theId = 1;
		System.out.println("Finding instructor id: "+theId);
		Instructor instructor =appDAO.findInstructorById(theId);
		System.out.println("instructor: "+instructor);
		System.out.println("Instructor courses: "+instructor.getCourses());
		System.out.println("Done");
	}

	private void createInstructorWithCurses(AppDAO appDAO) {
		// create the instructor
		Instructor tempInstructor = new Instructor("John","Mary","john@mary.com");

		// create InstructorDetail
		InstructorDetail tempInstructorDetail=new InstructorDetail(
				"http://luv2code.com/youtube.com",
				"luv 2 code");

		// associate the objects
		tempInstructor.setInstructorDetail(tempInstructorDetail);

		// create some courses
		Course tempCourse1=new Course("JAVA-FOR NEWBIES");
		Course tempCourse2=new Course("JAVASCRIPT-FOR LAGENDS");

		// Add courses to instructor
		tempInstructor.add(tempCourse1);
		tempInstructor.add(tempCourse2);
		//Save instructor
		// this will also save the courses because of cascadeType.PERSIST
		System.out.println("Saving instructor: "+tempInstructor);
		System.out.println("the courses: "+tempInstructor.getCourses());
		appDAO.save(tempInstructor);
		System.out.println("Done");
	}

	private void deleteInstructorDetail(AppDAO appDAO) {
		int theId=6;
		System.out.println("Deleting Instructor Detail with id: "+theId);
		appDAO.deleteInstructorDetailById(theId);
		System.out.println("Done");

	}

	private void findInstructorDetail(AppDAO appDAO) {
		// get instructor detail
		int theId = 2;
		System.out.println("Finding instrucorDetail with id : "+theId);
		InstructorDetail instructorDetail = appDAO.findInstructorDetailById(theId);
		// print instructor detail
		System.out.println("instructor detail: "+instructorDetail);
		System.out.println("instructor : "+instructorDetail.getInstructor());
		System.out.println("done");
	}

	private void deleteInstructor(AppDAO appDAO) {
		int theId = 3;
		System.out.println("deleting instructor with Id: "+theId);
		appDAO.deleteInstructorById(theId);
		System.out.println("Done");
	}

	private void findInstructor(AppDAO appDAO) {
		int theId = 1;
		System.out.println("Finding instrucor with id : "+theId);
		Instructor tempInstructor = appDAO.findInstructorById(theId);
		System.out.println(tempInstructor);
		//print only instructor detail
		System.out.println("Instructor detail: "+tempInstructor.getInstructorDetail());
	}

	private void createInstructor(AppDAO appDAO) {

		// create the instructor
		Instructor tempInstructor = new Instructor("John","Mary","john@mary.com");

		// create InstructorDetail
		InstructorDetail tempInstructorDetail=new InstructorDetail(
				"http://luv2code.com/youtube",
				"luv 2 code");

		// associate the objects
		tempInstructor.setInstructorDetail(tempInstructorDetail);

		// save the instructor
		//this will also save the details object
		//because of CascadeType.All
		System.out.println("saving instructor "+ tempInstructor);
		appDAO.save(tempInstructor);

		System.out.println("Done");
	}
}
