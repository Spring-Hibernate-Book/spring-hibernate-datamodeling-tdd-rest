package com.oodd.spring.classtableinheritance.entity;

import java.util.List;

import org.hibernate.SessionFactory;
import org.junit.Assert;
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
public class EmployeeTest {
	@Autowired
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	@Test
	public void testCRUD()
	{
		Employee e1 = new Employee();
		e1.setName("A");
		
		Employee e2 = new Employee();
		e2.setName("B");
		
		sessionFactory.getCurrentSession().save(e1);
		sessionFactory.getCurrentSession().save(e2);
		
		e1.setName("C");
		sessionFactory.getCurrentSession().merge(e1);

		List<Employee> list = sessionFactory.getCurrentSession().createQuery("from Employee").list();
		Assert.assertEquals(2L, list.size());
		
		sessionFactory.getCurrentSession().delete(e1);
		
		List<Employee> list2 = sessionFactory.getCurrentSession().createQuery("from Employee").list();
		Assert.assertEquals(1L, list2.size());
	}
}
