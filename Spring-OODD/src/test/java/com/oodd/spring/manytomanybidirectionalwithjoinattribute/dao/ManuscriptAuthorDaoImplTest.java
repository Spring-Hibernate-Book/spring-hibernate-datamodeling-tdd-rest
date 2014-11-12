package com.oodd.spring.manytomanybidirectionalwithjoinattribute.dao;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.oodd.spring.manytomanybidirectionalwithjoinattribute.entity.Author;
import com.oodd.spring.manytomanybidirectionalwithjoinattribute.entity.Manuscript;
import com.oodd.spring.manytomanybidirectionalwithjoinattribute.entity.ManuscriptAuthor;

@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration( locations = { "classpath:context.xml" } )
@TransactionConfiguration( defaultRollback = true )
@Transactional
public class ManuscriptAuthorDaoImplTest {

	@Autowired
	private AuthorDao authorDao;
	
	@Autowired
	private ManuscriptDao manuscriptDao;
	
	@Autowired
	private ManuscriptAuthorDao manuscriptAuthorDao;
	
	@Test
	public void testGetAll() {
		Assert.assertEquals(0L, manuscriptAuthorDao.getAll().size());
	}
	
	@Test
	public void testIsPresent() {
		boolean status = false;
		Manuscript manuscript = new Manuscript();
		manuscript.setName("Test Driven Application Development with Spring and Hibernate");
		
		Author author = new Author();
		author.setName("Amritendu De");
		authorDao.insert(author);
		
		ManuscriptAuthor manuscriptAuthor = new ManuscriptAuthor();
		manuscriptAuthor.setManuscript(manuscript);
		manuscriptAuthor.setAuthor(author);
		manuscriptAuthor.setPublisher("Createspace");
		
		manuscript.getManuscriptAuthors().add(manuscriptAuthor);
		manuscriptDao.insert(manuscript);	
		
		Manuscript manuscript2 = manuscriptDao.getAll().get(0);
		Author author2 = authorDao.getAll().get(0);
		
		List<ManuscriptAuthor> manuscriptAuthorList = manuscriptAuthorDao.isPresent(manuscript2.getId(), author2.getId());
		if(null != manuscriptAuthorList) {			
			if(manuscriptAuthorList.size() > 0) {
				status = true;
			}
		}
		Assert.assertTrue(status);
	}
	
	@Test
	public void testDelete() {
		Manuscript manuscript = new Manuscript();
		manuscript.setName("Test Driven Application Development with Spring and Hibernate");
		
		Author author = new Author();
		author.setName("Amritendu De");
		authorDao.insert(author);
		
		ManuscriptAuthor manuscriptAuthor = new ManuscriptAuthor();
		manuscriptAuthor.setManuscript(manuscript);
		manuscriptAuthor.setAuthor(author);
		manuscriptAuthor.setPublisher("Createspace");
		
		manuscript.getManuscriptAuthors().add(manuscriptAuthor);
		manuscriptDao.insert(manuscript);	
		
		List<Manuscript> manuscriptList = manuscriptDao.getAll();
		Manuscript tmpManuscript = manuscriptList.get(0);
		for(ManuscriptAuthor manuscriptAuthor2 : tmpManuscript.getManuscriptAuthors()) {
			tmpManuscript.getManuscriptAuthors().remove(manuscriptAuthor2);
			manuscriptAuthorDao.delete(manuscriptAuthor2);
		}
		Assert.assertEquals(0L, manuscriptAuthorDao.getAll().size());
	}
}