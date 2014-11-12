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
public class ManuscriptAuthorTest {

	@Autowired
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	@Test
	public void testCRUD() {
		Manuscript manuscript1 = new Manuscript();
		manuscript1.setName("Test Driven Application Development with Spring and Hibernate");
		
		Author author1 = new Author();
		author1.setName("Amritendu De");
		sessionFactory.getCurrentSession().save(author1);
		
		ManuscriptAuthor manuscriptAuthor1 = new ManuscriptAuthor();
		manuscriptAuthor1.setManuscript(manuscript1);
		manuscriptAuthor1.setAuthor(author1);
		manuscriptAuthor1.setPublisher("Createspace");
		
		manuscript1.getManuscriptAuthors().add(manuscriptAuthor1);
		
		Manuscript manuscript2 = new Manuscript();
		manuscript2.setName("The Lord of the Rings");
		
		Author author2 = new Author();
		author2.setName("J. R. R. Tolkien");
		sessionFactory.getCurrentSession().save(author2);
		
		ManuscriptAuthor manuscriptAuthor2 = new ManuscriptAuthor();
		manuscriptAuthor2.setManuscript(manuscript2);
		manuscriptAuthor2.setAuthor(author2);
		manuscriptAuthor2.setPublisher("Createspace");		
		manuscript2.getManuscriptAuthors().add(manuscriptAuthor2);
		
		sessionFactory.getCurrentSession().save(manuscript1);
		sessionFactory.getCurrentSession().save(manuscript2);
		
		author1.setName("Amish Tripathi");
		sessionFactory.getCurrentSession().merge(author1);
		
		List<ManuscriptAuthor> list = sessionFactory.getCurrentSession().createQuery("from ManuscriptAuthor").list();
		Assert.assertEquals(2L, list.size());
		
		List<Manuscript> manuscriptList = sessionFactory.getCurrentSession().createQuery("from Manuscript").list();
		Manuscript tmpManuscript = manuscriptList.get(0);
		for(ManuscriptAuthor manuscriptAuthor : tmpManuscript.getManuscriptAuthors()) {
			tmpManuscript.getManuscriptAuthors().remove(manuscriptAuthor);
			sessionFactory.getCurrentSession().delete(manuscriptAuthor);
		}
		sessionFactory.getCurrentSession().merge(tmpManuscript);
		
		List<ManuscriptAuthor> list2 = sessionFactory.getCurrentSession().createQuery("from ManuscriptAuthor").list();
		Assert.assertEquals(1L, list2.size());
	}
}