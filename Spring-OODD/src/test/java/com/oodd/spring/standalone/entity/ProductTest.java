package com.oodd.spring.standalone.entity;

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
public class ProductTest {
	@Autowired
	private SessionFactory sessionFactory;
	
	@Test
	public void testCRUD()
	{
		Product p1 = new Product();
		p1.setName("A");
		
		Product p2 = new Product();
		p2.setName("B");
		
		sessionFactory.getCurrentSession().save(p1);
		sessionFactory.getCurrentSession().save(p2);
		
		p1.setName("C");
		sessionFactory.getCurrentSession().merge(p1);

		List<Product> list = sessionFactory.getCurrentSession().createQuery("from Product").list();
		Assert.assertEquals(2L, list.size());
		
		sessionFactory.getCurrentSession().delete(p1);
		
		List<Product> list2 = sessionFactory.getCurrentSession().createQuery("from Product").list();
		Assert.assertEquals(1L, list2.size());
	}
}
