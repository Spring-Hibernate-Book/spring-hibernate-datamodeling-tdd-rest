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

@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration( locations = { "classpath:context.xml" } )
@TransactionConfiguration( defaultRollback = true )
@Transactional
public class AuthorDaoImplTest {

	@Autowired
	private AuthorDao dao;
	
	@Test
	public void testInsert() {
		Author author = new Author();
		author.setName("Amritendu De");
		dao.insert(author);
		
		Author author2 = new Author();
		author2.setName("J.R.R Tolkiens");
		dao.insert(author2);
	
		Assert.assertEquals(2L, dao.getAll().size());
	}

	@Test
	public void testGetAll() {
		Assert.assertEquals(0L, dao.getAll().size());
	}
	
	@Test
	public void testGetById() {
		Author author = new Author();
		author.setName("Amritendu De");
		dao.insert(author);
	
		List<Author> authorList = dao.getAll();
		Author author2 = authorList.get(0);
		
		Author author3 = dao.getById(author2.getId());
		Assert.assertEquals("Amritendu De", author3.getName());
	}
	
	@Test
	public void testDelete() {
		Author author1 = new Author();
		author1.setName("Amritendu De");
		dao.insert(author1);
		
		Author author2 = new Author();
		author2.setName("J.R.R Tolkiens");
		dao.insert(author2);
	
		Assert.assertEquals(2L, dao.getAll().size());
		
		dao.delete(author2);
		
		Assert.assertEquals(1L, dao.getAll().size());
	}
	
	@Test
	public void testUpdate() {
		Author author1 = new Author();
		author1.setName("Amritendu De");
		dao.insert(author1);
		
		Assert.assertEquals(1L, dao.getAll().size());

		List<Author> authorList = dao.getAll();
		Author author2 = authorList.get(0);
		author2.setName("J.R.R Tolkiens");
		
		dao.update(author2);
		
		List<Author> authorList2 = dao.getAll();
		Author author3 = authorList2.get(0);
		Assert.assertEquals("J.R.R Tolkiens", author3.getName());
	}
}