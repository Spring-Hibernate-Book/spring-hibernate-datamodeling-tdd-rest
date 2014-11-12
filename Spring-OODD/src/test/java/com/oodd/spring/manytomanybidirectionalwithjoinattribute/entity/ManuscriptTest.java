package com.oodd.spring.manytomanybidirectionalwithjoinattribute.entity;

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
public class ManuscriptTest {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	@Test
	public void testCRUD() {
		Manuscript manuscript1 = new Manuscript();
		manuscript1.setName("Test Driven Application Development with Spring and Hibernate");
		
		Manuscript manuscript2 = new Manuscript();
		manuscript2.setName("The Lord of the Rings");
		
		sessionFactory.getCurrentSession().save(manuscript1);
		sessionFactory.getCurrentSession().save(manuscript2);
		
		manuscript1.setName("The Immortals of Meluha");
		sessionFactory.getCurrentSession().merge(manuscript1);

		List<Manuscript> list = sessionFactory.getCurrentSession().createQuery("from Manuscript").list();
		Assert.assertEquals(2L, list.size());
		
		sessionFactory.getCurrentSession().delete(manuscript1);
		
		List<Manuscript> list2 = sessionFactory.getCurrentSession().createQuery("from Manuscript").list();
		Assert.assertEquals(1L, list2.size());
	}
}
