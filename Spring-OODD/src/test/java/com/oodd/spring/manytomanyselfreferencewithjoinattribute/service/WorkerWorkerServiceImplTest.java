package com.oodd.spring.manytomanyselfreferencewithjoinattribute.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.oodd.spring.manytomanyselfreferencewithjoinattribute.dto.WorkerDto;
import com.oodd.spring.manytomanyselfreferencewithjoinattribute.dto.WorkerWorkerDto;

@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration( locations = { "classpath:context.xml" } )
@TransactionConfiguration( defaultRollback = true )
@Transactional
public class WorkerWorkerServiceImplTest {

	@Autowired
	private WorkerService workerService;
	
	@Autowired
	private WorkerWorkerService workerWorkerService;
	
	@Test
	public void testFindAll() {
		Assert.assertEquals(0L, workerWorkerService.findAll().size());
	}
	
	@Test
	public void testCreate() {
		WorkerWorkerDto workerWorkerDto = new WorkerWorkerDto();
		
		WorkerDto workerDto1 = new WorkerDto();
		workerDto1.setName("The Immortals of Meluha");
		workerService.create(workerDto1);
		
		WorkerDto workerDto2 = new WorkerDto();
		workerDto2.setName("Amish Tripathi");
		workerService.create(workerDto2);
		
		List<WorkerDto> workerDtos1 = workerService.findAll();
		WorkerDto workerDto3 = workerDtos1.get(0);
		
		List<WorkerDto> workerDtos2 = workerService.findAll();
		WorkerDto workerDto4 = workerDtos2.get(0);
		
		workerWorkerDto.setWorkerId1(workerDto3);
		workerWorkerDto.setWorkerId2(workerDto4);
		workerWorkerDto.setRelationshipType("Friends");
		
		workerWorkerService.create(workerWorkerDto);
		Assert.assertEquals(1L, workerWorkerService.findAll().size());
	}
	
	@Test
	public void testIsPresent() {	
		WorkerWorkerDto workerWorkerDto = new WorkerWorkerDto();
		
		WorkerDto workerDto1 = new WorkerDto();
		workerDto1.setName("The Immortals of Meluha");
		workerService.create(workerDto1);
		
		WorkerDto workerDto2 = new WorkerDto();
		workerDto2.setName("Amish Tripathi");
		workerService.create(workerDto2);
		
		List<WorkerDto> workerDtos1 = workerService.findAll();
		WorkerDto workerDto3 = workerDtos1.get(0);
		
		List<WorkerDto> workerDtos2 = workerService.findAll();
		WorkerDto workerDto4 = workerDtos2.get(0);
		
		workerWorkerDto.setWorkerId1(workerDto3);
		workerWorkerDto.setWorkerId2(workerDto4);
		workerWorkerDto.setRelationshipType("Friends");
		
		workerWorkerService.create(workerWorkerDto);
		Assert.assertEquals(1L, workerWorkerService.findAll().size());		
		
		boolean status = workerWorkerService.isPresent(workerWorkerDto);		
		Assert.assertTrue(status);
	}
	
	@Test
	public void testRemove() {	
		WorkerWorkerDto workerWorkerDto = new WorkerWorkerDto();
		
		WorkerDto workerDto1 = new WorkerDto();
		workerDto1.setName("The Immortals of Meluha");
		workerService.create(workerDto1);
		
		WorkerDto workerDto2 = new WorkerDto();
		workerDto2.setName("Amish Tripathi");
		workerService.create(workerDto2);
		
		List<WorkerDto> workerDtos1 = workerService.findAll();
		WorkerDto workerDto3 = workerDtos1.get(0);
		
		List<WorkerDto> workerDtos2 = workerService.findAll();
		WorkerDto workerDto4 = workerDtos2.get(0);
		
		workerWorkerDto.setWorkerId1(workerDto3);
		workerWorkerDto.setWorkerId2(workerDto4);
		workerWorkerDto.setRelationshipType("Friends");
		
		workerWorkerService.create(workerWorkerDto);
		Assert.assertEquals(1L, workerWorkerService.findAll().size());
		
		List<WorkerWorkerDto> workerWorkerList = workerWorkerService.findAll();
		WorkerWorkerDto workerWorkerDto1 = workerWorkerList.get(0);
		workerWorkerService.remove(workerWorkerDto1);
		
		Assert.assertEquals(0, workerWorkerService.findAll().size());
	}
}
