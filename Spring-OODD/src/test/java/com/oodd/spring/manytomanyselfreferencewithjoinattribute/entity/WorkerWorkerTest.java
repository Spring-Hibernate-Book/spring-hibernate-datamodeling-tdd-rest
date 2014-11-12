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
public class WorkerWorkerTest {

	@Autowired
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	@Test
	public void testCRUD() {
		Worker worker1 = new Worker();
		worker1.setName("Lalit Narayan Mishra");
		sessionFactory.getCurrentSession().save(worker1);
		
		Worker worker2 = new Worker();
		worker2.setName("Amritendu De");
		sessionFactory.getCurrentSession().save(worker2);
		
		WorkerWorkerId workerWorkerId1 = new WorkerWorkerId();
		WorkerWorker workerWorker1 = new WorkerWorker();
		workerWorkerId1.setWorker1(worker1);
		workerWorkerId1.setWorker2(worker2);
		workerWorker1.setWorkerWorkerId(workerWorkerId1);
		workerWorker1.setRelationshipType("Createspace");
		sessionFactory.getCurrentSession().save(workerWorker1);
		
		Worker worker3 = new Worker();
		worker3.setName("Amish Tripathi");
		sessionFactory.getCurrentSession().save(worker3);
		
		Worker worker4 = new Worker();
		worker4.setName("Amritendu De");
		sessionFactory.getCurrentSession().save(worker4);
		
		WorkerWorkerId workerWorkerId2 = new WorkerWorkerId();
		WorkerWorker workerWorker2 = new WorkerWorker();
		workerWorkerId2.setWorker1(worker3);
		workerWorkerId2.setWorker2(worker4);
		workerWorker2.setWorkerWorkerId(workerWorkerId2);
		workerWorker2.setRelationshipType("Createspace");
		sessionFactory.getCurrentSession().save(workerWorker2);
		
		worker3.setName("Hazekul Alam");
		sessionFactory.getCurrentSession().save(worker3);
		
		List<WorkerWorker> list = sessionFactory.getCurrentSession().createQuery("from WorkerWorker").list();
		Assert.assertEquals(2L, list.size());
		
		List<WorkerWorker> workerWorkerList = sessionFactory.getCurrentSession().createQuery("from WorkerWorker").list();
		WorkerWorker tmpWorkerWorker = workerWorkerList.get(0);		
		sessionFactory.getCurrentSession().delete(tmpWorkerWorker);
		
		List<WorkerWorker> list2 = sessionFactory.getCurrentSession().createQuery("from WorkerWorker").list();
		Assert.assertEquals(1L, list2.size());
	}
}