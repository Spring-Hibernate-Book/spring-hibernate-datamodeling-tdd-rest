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
import com.oodd.spring.concretetableinheritance.entity.Land;

@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration( locations = { "classpath:context.xml" } )
@TransactionConfiguration( defaultRollback = true )
@Transactional
public class LandTest {
	@Autowired
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	@Test
	public void testCRUD()
	{
		Estate p1 = new Estate();
		p1.setName("Estate");		
		sessionFactory.getCurrentSession().save(p1);
		
		Land land = new Land();
		land.setName("Land");
		land.setArea(20);
		sessionFactory.getCurrentSession().save(land);
		
		land.setName("Land Estate");
		land.setArea(30);
		sessionFactory.getCurrentSession().merge(land);

		List<Land> list = sessionFactory.getCurrentSession().createQuery("from Land").list();
		Assert.assertEquals(1L, list.size());
		
		sessionFactory.getCurrentSession().delete(land);
		
		List<Land> list2 = sessionFactory.getCurrentSession().createQuery("from Land").list();
		Assert.assertEquals(0L, list2.size());
	}
}
