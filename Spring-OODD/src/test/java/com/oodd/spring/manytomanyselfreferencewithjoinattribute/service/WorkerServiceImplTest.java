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

@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration( locations = { "classpath:context.xml" } )
@TransactionConfiguration( defaultRollback = true )
@Transactional
public class WorkerServiceImplTest {

	@Autowired
	private WorkerService workerService;
	
	@Test
	public void testCreate() {
		WorkerDto workerDto = new WorkerDto();
		workerDto.setName("Amritendu De");
		workerService.create(workerDto);
		
		WorkerDto workerDto2 = new WorkerDto();
		workerDto2.setName("Amish Tripathi");
		workerService.create(workerDto2);
	
		Assert.assertEquals(2L, workerService.findAll().size());
	}
	
	@Test
	public void testFindAll() {
		Assert.assertEquals(0L, workerService.findAll().size());
	}
	
	@Test
	public void testFindById() {
		WorkerDto workerDto = new WorkerDto();
		workerDto.setName("Amritendu De");
		workerService.create(workerDto);
	
		List<WorkerDto> mList = workerService.findAll();
		WorkerDto workerDto2 = mList.get(0);
		
		WorkerDto workerDto3 = workerService.findById(workerDto2.getId());
		Assert.assertEquals("Amritendu De", workerDto3.getName());
	}
	
	@Test
	public void testRemove() {
		WorkerDto workerDto = new WorkerDto();
		workerDto.setName("Amritendu De");
		workerService.create(workerDto);
			
		WorkerDto workerDto2 = new WorkerDto();
		workerDto2.setName("Amish Tripathi");
		workerService.create(workerDto2);
	
		Assert.assertEquals(2L, workerService.findAll().size());
		
		List<WorkerDto> mList = workerService.findAll();
		WorkerDto workerDto3 = mList.get(0);
		workerService.remove(workerDto3.getId());
		
		Assert.assertEquals(1L, workerService.findAll().size());
	}
	
	@Test
	public void testEdit() {
		WorkerDto workerDto = new WorkerDto();
		workerDto.setName("Amritendu De");
		workerService.create(workerDto);
		
		Assert.assertEquals(1L, workerService.findAll().size());
		
		List<WorkerDto> mList = workerService.findAll();
		WorkerDto workerDto2 = mList.get(0);
		workerDto2.setName("Amish Tripathi");
		
		workerService.edit(workerDto2);
		
		List<WorkerDto> mList2 = workerService.findAll();
		WorkerDto workerDto3 = mList2.get(0);
		Assert.assertEquals("Amish Tripathi", workerDto3.getName());
	}
}
