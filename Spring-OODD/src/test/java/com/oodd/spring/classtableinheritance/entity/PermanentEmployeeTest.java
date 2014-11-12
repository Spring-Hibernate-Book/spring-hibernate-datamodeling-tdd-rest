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
public class PermanentEmployeeTest {
	@Autowired
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	@Test
	public void testCRUD()
	{
		Employee e1 = new Employee();
		e1.setName("A");		
		sessionFactory.getCurrentSession().save(e1);
		
		PermanentEmployee permanentEmployee = new PermanentEmployee();
		permanentEmployee.setName("Lalit Narayan Mishra");
		permanentEmployee.setLeaves(20);
		permanentEmployee.setSalary(500000);
		sessionFactory.getCurrentSession().save(permanentEmployee);
		
		permanentEmployee.setName("Amritendu De");
		sessionFactory.getCurrentSession().merge(permanentEmployee);

		List<PermanentEmployee> list = sessionFactory.getCurrentSession().createQuery("from PermanentEmployee").list();
		Assert.assertEquals(1L, list.size());
		
		sessionFactory.getCurrentSession().delete(permanentEmployee);
		
		List<PermanentEmployee> list2 = sessionFactory.getCurrentSession().createQuery("from PermanentEmployee").list();
		Assert.assertEquals(0L, list2.size());
	}
}
