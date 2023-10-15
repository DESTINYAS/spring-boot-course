package com.luv2code.cruddemo;

import com.luv2code.cruddemo.dao.AppDAO;
import com.luv2code.cruddemo.entity.Course;
import com.luv2code.cruddemo.entity.Instructor;
import com.luv2code.cruddemo.entity.InstructorDetail;
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
//			createInstructor(appDAO);
//			findInstructor(appDAO);
//			deleteInstructor(appDAO);
//			findInstructorDetail(appDAO);
//			deleteInstructorDetail(appDAO);
//			createInstructorWithCurses(appDAO);
//			findInstructorWithCourses(appDAO);
//			findCoursesForInstructor(appDAO);
//			findInstructorWithCoursesJoinFetch(appDAO);
//			updateInstructor(appDAO);
//			updateCourse(appDAO);
//			deleteInstructor(appDAO);
			deleteCourse(appDAO);
		};
	}

	private void deleteCourse(AppDAO appDAO) {
		int theId = 12;
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
