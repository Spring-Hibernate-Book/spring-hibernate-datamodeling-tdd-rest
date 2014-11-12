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
import com.oodd.spring.manytomanyselfreferencewithjoinattribute.entity.WorkerWorker;
import com.oodd.spring.manytomanyselfreferencewithjoinattribute.entity.WorkerWorkerId;

@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration( locations = { "classpath:context.xml" } )
@TransactionConfiguration( defaultRollback = true )
@Transactional
public class WorkerWorkerDaoImplTest {

	@Autowired
	private WorkerDao workerDao;
	
	@Autowired
	private WorkerWorkerDao workerWorkerDao;
	
	@Test
	public void testInsert() {
		Worker worker1 = new Worker();
		worker1.setName("Lalit Narayan Mishra");
		workerDao.insert(worker1);
		
		Worker worker2 = new Worker();
		worker2.setName("Amritendu De");
		workerDao.insert(worker2);
		
		WorkerWorkerId workerWorkerId = new WorkerWorkerId();
		WorkerWorker workerWorker = new WorkerWorker();
		workerWorkerId.setWorker1(worker1);
		workerWorkerId.setWorker2(worker2);
		workerWorker.setWorkerWorkerId(workerWorkerId);
		workerWorker.setRelationshipType("Collesgues");
		workerWorkerDao.insert(workerWorker);	
		
		Assert.assertEquals(1L, workerWorkerDao.getAll().size());
	}
	
	@Test
	public void testGetAll() {
		Assert.assertEquals(0L, workerWorkerDao.getAll().size());
	}
	
	@Test
	public void testIsPresent() {
		boolean status = false;
		Worker worker1 = new Worker();
		worker1.setName("Lalit Narayan Mishra");
		workerDao.insert(worker1);
		
		Worker worker2 = new Worker();
		worker2.setName("Amritendu De");
		workerDao.insert(worker2);
		
		WorkerWorkerId workerWorkerId = new WorkerWorkerId();
		WorkerWorker workerWorker = new WorkerWorker();
		workerWorkerId.setWorker1(worker1);
		workerWorkerId.setWorker2(worker2);
		workerWorker.setWorkerWorkerId(workerWorkerId);
		workerWorker.setRelationshipType("Colleagues");
		workerWorkerDao.insert(workerWorker);			
				
		Worker worker3 = workerDao.getAll().get(0);
		Worker worker4 = workerDao.getAll().get(1);
		
		List<WorkerWorker> workerWorkerList = workerWorkerDao.isPresent(worker4.getId(), worker3.getId());
		if(null != workerWorkerList) {			
			if(workerWorkerList.size() > 0) {
				status = true;
			}
		}
		Assert.assertTrue(status);
	}
	
	@Test
	public void testDelete() {
		Worker worker1 = new Worker();
		worker1.setName("Lalit Narayan Mishra");
		workerDao.insert(worker1);
		
		Worker worker2 = new Worker();
		worker2.setName("Amritendu De");
		workerDao.insert(worker2);
		
		WorkerWorkerId workerWorkerId = new WorkerWorkerId();
		WorkerWorker workerWorker = new WorkerWorker();
		workerWorkerId.setWorker1(worker1);
		workerWorkerId.setWorker2(worker2);
		workerWorker.setWorkerWorkerId(workerWorkerId);
		workerWorker.setRelationshipType("Collesgues");
		workerWorkerDao.insert(workerWorker);		
		
		List<WorkerWorker> workerList = workerWorkerDao.getAll();
		workerWorkerDao.delete(workerList.get(0));
		Assert.assertEquals(0L, workerWorkerDao.getAll().size());
	}
}