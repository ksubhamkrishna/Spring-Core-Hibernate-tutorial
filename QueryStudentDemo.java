package com.luv2code.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class QueryStudentDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml").addAnnotatedClass(Student.class)
				.buildSessionFactory();

		// create session
		Session session = factory.getCurrentSession();

		try {

			// start a transaction
			session.beginTransaction();

			// querying student table

			List<Student> theStudents = session.createQuery("from Student").list();

			// display the students
			displayStudent(theStudents);

			// query students: lastName = 'Doe'
			List<Student> particularStudents = session.createQuery("from Student s where s.lastName ='Doe'").list();

			System.out.println("\n\nStudents who have last name of Doe");
			displayStudent(particularStudents);

			// query students: lastName = 'Doe' OR firstName='Daffy'
			particularStudents = session.createQuery("from Student s where" + " s.lastName = 'Doe'"
					+ "OR s.firstName = 'Daffy'" + "OR s.email LIKE 'p%' ").list();

			System.out.println("\n\nStudents who have last name of Doe OR first name Daffy");
			displayStudent(particularStudents);

			// query students where email LIKE '%luv2code.com'
			particularStudents = session.createQuery("from Student s where" + " s.email LIKE '%luv2code.com' ").list();

			System.out.println("\n\nStudents whose email ends with luv2code.com");
			displayStudent(particularStudents);

			// commit transaction
			session.getTransaction().commit();

			System.out.println("Done!");
		} finally {
			factory.close();
		}
	}

	private static void displayStudent(List<Student> theStudents) {
		for (Student Students : theStudents) {
			System.out.println(Students);
		}
	}

}
