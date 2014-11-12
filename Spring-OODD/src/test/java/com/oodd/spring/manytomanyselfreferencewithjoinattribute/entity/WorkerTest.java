package com.oodd.spring.manytomanyselfreferencewithjoinattribute.entity;

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
public class WorkerTest {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	@Test
	public void testCRUD() {
		Worker worker1 = new Worker();
		worker1.setName("Lalit Narayan Mishra");
		
		Worker worker2 = new Worker();
		worker2.setName("Amritendu De");
		
		sessionFactory.getCurrentSession().save(worker1);
		sessionFactory.getCurrentSession().save(worker2);
		
		worker1.setName("Amish Tripathi");
		sessionFactory.getCurrentSession().merge(worker1);

		List<Worker> list = sessionFactory.getCurrentSession().createQuery("from Worker").list();
		Assert.assertEquals(2L, list.size());
		
		sessionFactory.getCurrentSession().delete(worker1);
		
		List<Worker> list2 = sessionFactory.getCurrentSession().createQuery("from Worker").list();
		Assert.assertEquals(1L, list2.size());
	}
}
