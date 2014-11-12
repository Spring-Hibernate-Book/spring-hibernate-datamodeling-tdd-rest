package com.oodd.spring.concretetableinheritance.entity;

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

import com.oodd.spring.concretetableinheritance.entity.Estate;

@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration( locations = { "classpath:context.xml" } )
@TransactionConfiguration( defaultRollback = true )
@Transactional
public class EstateTest {
	@Autowired
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	@Test
	public void testCRUD()
	{
		Estate p1 = new Estate();
		p1.setName("A");
		
		Estate p2 = new Estate();
		p2.setName("B");
		
		sessionFactory.getCurrentSession().save(p1);
		sessionFactory.getCurrentSession().save(p2);
		
		p1.setName("C");
		sessionFactory.getCurrentSession().merge(p1);

		List<Estate> list = sessionFactory.getCurrentSession().createQuery("from Estate").list();
		Assert.assertEquals(2L, list.size());
		
		sessionFactory.getCurrentSession().delete(p1);
		
		List<Estate> list2 = sessionFactory.getCurrentSession().createQuery("from Estate").list();
		Assert.assertEquals(1L, list2.size());
	}
}
