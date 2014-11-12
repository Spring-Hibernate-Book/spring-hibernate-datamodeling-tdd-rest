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
public class AuthorTest {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	@Test
	public void testCRUD() {
		Author author1 = new Author();
		author1.setName("Amritendu De");
		
		Author author2 = new Author();
		author2.setName("J. R. R. Tolkien");
		
		sessionFactory.getCurrentSession().save(author1);
		sessionFactory.getCurrentSession().save(author2);
		
		author1.setName("Amish Tripathi");
		sessionFactory.getCurrentSession().merge(author1);

		List<Author> list = sessionFactory.getCurrentSession().createQuery("from Author").list();
		Assert.assertEquals(2L, list.size());
		
		sessionFactory.getCurrentSession().delete(author1);
		
		List<Author> list2 = sessionFactory.getCurrentSession().createQuery("from Author").list();
		Assert.assertEquals(1L, list2.size());
	}
}
