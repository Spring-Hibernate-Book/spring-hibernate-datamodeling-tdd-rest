package com.oodd.spring.onetooneselfreference.dao;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.oodd.spring.onetooneselfreference.entity.Student;

@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration( locations = { "classpath:context.xml" } )
@TransactionConfiguration( defaultRollback = true )
@Transactional
public class StudentDaoImplTest {
	@Autowired
	private StudentDao dao ;
	@Test
	public void testInsert(){
		Student student1 = new Student();
		student1.setName("Alex");
		Student mentor1 = new Student();
		mentor1.setName("Fred");
		student1.setMentor(mentor1);
		dao.insert(student1);
		
		Student student2 = new Student();
		student2.setName("Michel");
		Student mentor2 = new Student();
		mentor2.setName("Mac");
		student2.setMentor(mentor2);
		dao.insert(student2);
		Assert.assertEquals(4L, dao.getAll().size());
	}
	@Test
	public void testGetAll() {
		Assert.assertEquals(0L, dao.getAll().size());
	}
	@Test
	public void testGetById() {
		Student student1 = new Student();
		student1.setName("Alex");
		Student mentor1 = new Student();
		mentor1.setName("Fred");
		student1.setMentor(mentor1);
		dao.insert(student1);
		List<Student> students = dao.getAll();
		Student student = students.get(1);
		Student student2 = dao.getById(student.getId());
		Assert.assertEquals("Alex", student2.getName());
		Assert.assertEquals("Fred", student2.getMentor().getName());
	}
	@Test
	public void testDelete() {
		Student student1 = new Student();
		student1.setName("Alex");
		Student mentor1 = new Student();
		mentor1.setName("Fred");
		student1.setMentor(mentor1);
		dao.insert(student1);
		
		Student student2 = new Student();
		student2.setName("Michel");
		Student mentor2 = new Student();
		mentor2.setName("Mac");
		student2.setMentor(mentor2);
		dao.insert(student2);
		Assert.assertEquals(4L, dao.getAll().size());
		
		List<Student> students = dao.getAll();
		Student tempStudent = students.get(3);
		dao.delete(tempStudent);
		Assert.assertEquals(2L, dao.getAll().size());
	}
	@Test
	public void testUpdate() {
		Student student1 = new Student();
		student1.setName("Alex");
		Student mentor1 = new Student();
		mentor1.setName("Fred");
		student1.setMentor(mentor1);
		dao.insert(student1);
		Assert.assertEquals(2L, dao.getAll().size());
		
		List<Student> students = dao.getAll();
		Student tempStudent = students.get(1);
		tempStudent.setName("Alex James");
		Student tempMentor = tempStudent.getMentor();
		tempMentor.setName("Fred James");
		tempStudent.setMentor(tempMentor);
		dao.update(tempStudent);
		Assert.assertEquals(2L, dao.getAll().size());
	}
}
