package com.oodd.spring.manytomanyunidirectional.entity;

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
public class GroupTest {
	@Autowired
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	@Test
	public void testCRUD()
	{
		Group g1 = new Group();
		g1.setName("A");
		
		Group g2 = new Group();
		g2.setName("B");
		
		sessionFactory.getCurrentSession().save(g1);
		sessionFactory.getCurrentSession().save(g2);
		
		g1.setName("C");
		sessionFactory.getCurrentSession().merge(g2);

		List<User> list = sessionFactory.getCurrentSession().createQuery("from Group").list();
		Assert.assertEquals(2L, list.size());
		
		sessionFactory.getCurrentSession().delete(g1);
		
		List<User> list2 = sessionFactory.getCurrentSession().createQuery("from Group").list();
		Assert.assertEquals(1L, list2.size());
	}
}
