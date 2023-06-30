// Updating objects from database using hibernate single row/column.

package com.luv2code.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class DeleteStudentDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class)
				.buildSessionFactory();
		Session session = factory.getCurrentSession();

		try {

			// int StudentId = 1;

			// now get a new session and start transaction
			session = factory.getCurrentSession();

			// begin the session
			session.beginTransaction();

			List<Student> student_before_update = session.createQuery("from Student").list();

			System.out.println("\n\nStudent table before update : ");

			studentTable(student_before_update);

			// retrieve student based on the id : primary key
			// Student myStudent = session.get(Student.class, StudentId);

			// delete the student
			// System.out.println("Deleting student:" + myStudent);
			// session.delete(myStudent);

			// delete student id =2
			System.out.println("\n\nDeleting student id=2");

			session.createQuery("delete from Student where id =2").executeUpdate();

			// commit the transaction
			session.getTransaction().commit();

			System.out.println("Done!\n");

			session = factory.getCurrentSession();

			session.beginTransaction();

			List<Student> updated_table = session.createQuery("from Student").list();

			System.out.println("\n\nUpdated Table:");

			studentTable(updated_table);

			session.getTransaction().commit();

		} finally {
			factory.close();
		}
	}

	private static void studentTable(List<Student> student_before_update) {
		for (Student students : student_before_update) {
			System.out.println(students);
		}
	}

}
