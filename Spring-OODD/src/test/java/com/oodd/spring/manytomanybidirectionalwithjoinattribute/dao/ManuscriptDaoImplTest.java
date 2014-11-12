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

import com.oodd.spring.manytomanybidirectionalwithjoinattribute.entity.Manuscript;

@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration( locations = { "classpath:context.xml" } )
@TransactionConfiguration( defaultRollback = true )
@Transactional
public class ManuscriptDaoImplTest {

	@Autowired
	private ManuscriptDao dao;
	
	@Test
	public void testInsert() {
		Manuscript manuscript1 = new Manuscript();
		manuscript1.setName("Test Driven Application Development with Spring and Hibernate");
		dao.insert(manuscript1);
		
		Manuscript manuscript2 = new Manuscript();
		manuscript2.setName("The Lord of the Rings");
		dao.insert(manuscript2);
	
		Assert.assertEquals(2L, dao.getAll().size());
	}

	@Test
	public void testGetAll() {
		Assert.assertEquals(0L, dao.getAll().size());
	}
	
	@Test
	public void testGetById() {
		Manuscript manuscript1 = new Manuscript();
		manuscript1.setName("Test Driven Application Development with Spring and Hibernate");
		dao.insert(manuscript1);
	
		List<Manuscript> msList = dao.getAll();
		Manuscript manuscript = msList.get(0);
		
		Manuscript manuscript2 = dao.getById(manuscript.getId());
		Assert.assertEquals("Test Driven Application Development with Spring and Hibernate", manuscript2.getName());
	}
	
	@Test
	public void testDelete() {
		Manuscript manuscript1 = new Manuscript();
		manuscript1.setName("Test Driven Application Development with Spring and Hibernate");
		dao.insert(manuscript1);
		
		Manuscript manuscript2 = new Manuscript();
		manuscript2.setName("The Lord of the Rings");
		dao.insert(manuscript2);
	
		Assert.assertEquals(2L, dao.getAll().size());
		
		dao.delete(manuscript2);
		
		Assert.assertEquals(1L, dao.getAll().size());
	}
	
	@Test
	public void testUpdate() {
		Manuscript manuscript1 = new Manuscript();
		manuscript1.setName("Test Driven Application Development with Spring and Hibernate");
		dao.insert(manuscript1);
		
		Assert.assertEquals(1L, dao.getAll().size());

		List<Manuscript> msList = dao.getAll();
		Manuscript manuscript = msList.get(0);
		manuscript.setName("The Lord of the Rings");
		
		dao.update(manuscript);
		
		List<Manuscript> msList2 = dao.getAll();
		Manuscript manuscript2 = msList2.get(0);
		Assert.assertEquals("The Lord of the Rings", manuscript2.getName());
	}
}