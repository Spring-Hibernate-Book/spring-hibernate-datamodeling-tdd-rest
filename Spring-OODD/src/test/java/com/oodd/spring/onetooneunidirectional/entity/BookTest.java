package com.oodd.spring.onetooneunidirectional.entity;
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
@SuppressWarnings("deprecation")
@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration( locations = { "classpath:context.xml" } )
@TransactionConfiguration( defaultRollback = true )
@Transactional
public class BookTest {
	@Autowired
	private SessionFactory sessionFactory;
	@SuppressWarnings({ "unchecked" })
	@Test
	public void testCRUD()
	{
		Book book1 = new Book();
		book1.setName("Java SE");
		Shipping shipping1 = new Shipping();
		shipping1.setCity("US");
		book1.setShipping(shipping1);
		sessionFactory.getCurrentSession().save(book1);
		
		Book book2 = new Book();
		book2.setName("EJB 3.0");
		Shipping shipping2 = new Shipping();
		shipping2.setCity("CAN");
		book2.setShipping(shipping2);
		sessionFactory.getCurrentSession().save(book2);
		
		book1.setName("JEE");
		shipping1 = book1.getShipping();
		shipping1.setCity("UK");
		sessionFactory.getCurrentSession().merge(book1);
		
		List<Book> books = sessionFactory.getCurrentSession().createQuery("select book from Book book").list();
		Assert.assertEquals(2L, books.size());
		
		sessionFactory.getCurrentSession().delete(book2);
		
	    books = sessionFactory.getCurrentSession().createQuery("select book from Book book").list();
		Assert.assertEquals(1L, books.size());
		
		
	}
	
}
