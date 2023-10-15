package com.luv2code.cruddemo;

import com.luv2code.cruddemo.dao.AppDAO;
import com.luv2code.cruddemo.entity.Instructor;
import com.luv2code.cruddemo.entity.InstructorDetail;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

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
			deleteInstructorDetail(appDAO);
		};
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
		int theId = 1;
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
