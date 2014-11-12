package com.oodd.spring.onetoonebidirectional.entity;

import java.util.List;

import org.junit.Assert;

import org.hibernate.SessionFactory;
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
public class CustomerTest {
	@Autowired
	private SessionFactory sessionFactory;
	@SuppressWarnings({ "unchecked", "deprecation" })
	@Test
	public void testCRUD()
	{
		Customer customer1 = new Customer();
		customer1.setName("Alex");
		Cart cart1 = new Cart();
		cart1.setAmount(500.0);
		customer1.setCart(cart1);
		sessionFactory.getCurrentSession().save(cart1);
		
		Customer customer2 = new Customer();
		customer2.setName("Fred");
		Cart cart2 = new Cart();
		cart2.setAmount(700.0);
		customer2.setCart(cart2);
		sessionFactory.getCurrentSession().save(customer2);
		
		
		customer1.setName("Alex");
		cart1 = customer1.getCart();
		cart1.setAmount(500.0);
		customer1.setCart(cart1);
		sessionFactory.getCurrentSession().merge(customer1);
		
		List<Customer> customers = sessionFactory.getCurrentSession()
				.createQuery("select customer from Customer customer order by customer.id desc").list();
		Assert.assertEquals(2L, customers.size());
		
		sessionFactory.getCurrentSession().delete(customer2);
		
		 customers = sessionFactory.getCurrentSession()
					.createQuery("select customer from Customer customer order by customer.id desc").list();
		 Assert.assertEquals(1L, customers.size());
	}
}
