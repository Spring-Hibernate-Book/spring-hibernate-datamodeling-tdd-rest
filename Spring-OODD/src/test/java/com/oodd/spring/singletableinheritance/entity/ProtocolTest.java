package com.oodd.spring.singletableinheritance.entity;

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
public class ProtocolTest {
	@Autowired
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	@Test
	public void testCRUD()
	{
		Protocol p1 = new Protocol();
		p1.setName("A");
		
		Protocol p2 = new Protocol();
		p2.setName("B");
		
		sessionFactory.getCurrentSession().save(p1);
		sessionFactory.getCurrentSession().save(p2);
		
		p1.setName("C");
		sessionFactory.getCurrentSession().merge(p1);

		List<Protocol> list = sessionFactory.getCurrentSession().createQuery("from Protocol").list();
		Assert.assertEquals(2L, list.size());
		
		sessionFactory.getCurrentSession().delete(p1);
		
		List<Protocol> list2 = sessionFactory.getCurrentSession().createQuery("from Protocol").list();
		Assert.assertEquals(1L, list2.size());
	}
}
