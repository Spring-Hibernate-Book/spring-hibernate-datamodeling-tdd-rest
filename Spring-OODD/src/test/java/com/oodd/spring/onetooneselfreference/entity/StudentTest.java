package com.oodd.spring.onetooneselfreference.entity;

import java.util.List;

import org.junit.Assert;

import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration( locations = { "classpath:context.xml" } )
@TransactionConfiguration( defaultRollback = true )
@Transactional
public class StudentTest {
	@Autowired
	private SessionFactory sessionFactory;
	@SuppressWarnings({ "unchecked", "deprecation" })
	@Test
	public void testCRUD()
	{
		Student student1 = new Student();
		student1.setName("Alex");
		Student mentor1 = new Student();
		mentor1.setName("Fred");
		student1.setMentor(mentor1);
		sessionFactory.getCurrentSession().save(student1);

		Student student2 = new Student();
		student2.setName("Michel");
		Student mentor2 = new Student();
		mentor2.setName("Mac");
		student2.setMentor(mentor2);
		sessionFactory.getCurrentSession().save(student2);
		List<Student> students = sessionFactory.getCurrentSession()
				.createQuery("select student from Student student").list();
		Assert.assertEquals(4L, students.size());

		Student tempStudent = students.get(1);
		tempStudent.setName("Alex James");
		Student tempmentor = tempStudent.getMentor();
		tempmentor.setName("Fred James");
		sessionFactory.getCurrentSession().merge(tempStudent);
		students = sessionFactory.getCurrentSession()
				.createQuery("select student from Student student").list();
		Assert.assertEquals(4L, students.size());

		tempStudent = students.get(3);
		sessionFactory.getCurrentSession().delete(tempStudent);
		students = sessionFactory.getCurrentSession()
				.createQuery("select student from Student student").list();
		Assert.assertEquals(2L, students.size());
		
	}
}
