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
public class UserTest {
	@Autowired
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	@Test
	public void testCRUD()
	{
		User u1 = new User();
		u1.setName("A");
		
		User u2 = new User();
		u2.setName("B");
		
		sessionFactory.getCurrentSession().save(u1);
		sessionFactory.getCurrentSession().save(u2);
		
		u1.setName("C");
		sessionFactory.getCurrentSession().merge(u1);

		List<User> list = sessionFactory.getCurrentSession().createQuery("from User").list();
		Assert.assertEquals(2L, list.size());
		
		sessionFactory.getCurrentSession().delete(u1);
		
		List<User> list2 = sessionFactory.getCurrentSession().createQuery("from User").list();
		Assert.assertEquals(1L, list2.size());
		
		Group g1 = new Group();
		g1.setName("A");
		
		Group g2 = new Group();
		g2.setName("B");
		
		sessionFactory.getCurrentSession().save(g1);
		sessionFactory.getCurrentSession().save(g2);
		
		User u3 = list2.get(0);
		u3.getGroups().add(g1);
		u3.getGroups().add(g2);
		sessionFactory.getCurrentSession().merge(u3);
		
		List<User> list3 = sessionFactory.getCurrentSession().createQuery("from User").list();
		Assert.assertEquals(1L, list3.size());
		Assert.assertEquals(2L, list3.get(0).getGroups().size());
	}
}
