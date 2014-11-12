package com.oodd.spring.manytomanyselfreferencewithjoinattribute.dao;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.oodd.spring.manytomanyselfreferencewithjoinattribute.entity.Worker;

@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration( locations = { "classpath:context.xml" } )
@TransactionConfiguration( defaultRollback = true )
@Transactional
public class WorkerDaoImplTest {

	@Autowired
	private WorkerDao dao;
	
	@Test
	public void testInsert() {
		Worker worker1 = new Worker();
		worker1.setName("Test Driven Application Development with Spring and Hibernate");
		dao.insert(worker1);
		
		Worker worker2 = new Worker();
		worker2.setName("The Lord of the Rings");
		dao.insert(worker2);
	
		Assert.assertEquals(2L, dao.getAll().size());
	}

	@Test
	public void testGetAll() {
		Assert.assertEquals(0L, dao.getAll().size());
	}
	
	@Test
	public void testGetById() {
		Worker worker1 = new Worker();
		worker1.setName("Test Driven Application Development with Spring and Hibernate");
		dao.insert(worker1);
	
		List<Worker> msList = dao.getAll();
		Worker worker = msList.get(0);
		
		Worker worker2 = dao.getById(worker.getId());
		Assert.assertEquals("Test Driven Application Development with Spring and Hibernate", worker2.getName());
	}
	
	@Test
	public void testDelete() {
		Worker worker1 = new Worker();
		worker1.setName("Test Driven Application Development with Spring and Hibernate");
		dao.insert(worker1);
		
		Worker worker2 = new Worker();
		worker2.setName("The Lord of the Rings");
		dao.insert(worker2);
	
		Assert.assertEquals(2L, dao.getAll().size());
		
		dao.delete(worker2);
		
		Assert.assertEquals(1L, dao.getAll().size());
	}
	
	@Test
	public void testUpdate() {
		Worker worker1 = new Worker();
		worker1.setName("Test Driven Application Development with Spring and Hibernate");
		dao.insert(worker1);
		
		Assert.assertEquals(1L, dao.getAll().size());

		List<Worker> msList = dao.getAll();
		Worker worker = msList.get(0);
		worker.setName("The Lord of the Rings");
		
		dao.update(worker);
		
		List<Worker> msList2 = dao.getAll();
		Worker worker2 = msList2.get(0);
		Assert.assertEquals("The Lord of the Rings", worker2.getName());
	}
}