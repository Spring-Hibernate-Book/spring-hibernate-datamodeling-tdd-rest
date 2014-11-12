package com.oodd.spring.manytomanybidirectionalwithjoinattribute.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.oodd.spring.manytomanybidirectionalwithjoinattribute.dto.ManuscriptDto;

@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration( locations = { "classpath:context.xml" } )
@TransactionConfiguration( defaultRollback = true )
@Transactional
public class ManuscriptServiceImplTest {

	@Autowired
	private ManuscriptService manuscriptService;
	
	@Test
	public void testCreate() {
		ManuscriptDto manuscriptDto = new ManuscriptDto();
		manuscriptDto.setName("Test Driven Application Development with Spring and Hibernate");
		manuscriptService.create(manuscriptDto);
		
		ManuscriptDto manuscriptDto2 = new ManuscriptDto();
		manuscriptDto2.setName("The Immortals of Meluha");
		manuscriptService.create(manuscriptDto2);
	
		Assert.assertEquals(2L, manuscriptService.findAll().size());
	}
	
	@Test
	public void testFindAll() {
		Assert.assertEquals(0L, manuscriptService.findAll().size());
	}
	
	@Test
	public void testFindById() {
		ManuscriptDto manuscriptDto = new ManuscriptDto();
		manuscriptDto.setName("Test Driven Application Development with Spring and Hibernate");
		manuscriptService.create(manuscriptDto);
	
		List<ManuscriptDto> mList = manuscriptService.findAll();
		ManuscriptDto manuscriptDto2 = mList.get(0);
		
		ManuscriptDto manuscriptDto3 = manuscriptService.findById(manuscriptDto2.getId());
		Assert.assertEquals("Test Driven Application Development with Spring and Hibernate", manuscriptDto3.getName());
	}
	
	@Test
	public void testRemove() {
		ManuscriptDto manuscriptDto = new ManuscriptDto();
		manuscriptDto.setName("Test Driven Application Development with Spring and Hibernate");
		manuscriptService.create(manuscriptDto);
			
		ManuscriptDto manuscriptDto2 = new ManuscriptDto();
		manuscriptDto2.setName("The Immortals of Meluha");
		manuscriptService.create(manuscriptDto2);
	
		Assert.assertEquals(2L, manuscriptService.findAll().size());
		
		List<ManuscriptDto> mList = manuscriptService.findAll();
		ManuscriptDto manuscriptDto3 = mList.get(0);
		manuscriptService.remove(manuscriptDto3.getId());
		
		Assert.assertEquals(1L, manuscriptService.findAll().size());
	}
	
	@Test
	public void testEdit() {
		ManuscriptDto manuscriptDto = new ManuscriptDto();
		manuscriptDto.setName("Test Driven Application Development with Spring and Hibernate");
		manuscriptService.create(manuscriptDto);
		
		Assert.assertEquals(1L, manuscriptService.findAll().size());
		
		List<ManuscriptDto> mList = manuscriptService.findAll();
		ManuscriptDto manuscriptDto2 = mList.get(0);
		manuscriptDto2.setName("The Immortals of Meluha");
		
		manuscriptService.edit(manuscriptDto2);
		
		List<ManuscriptDto> mList2 = manuscriptService.findAll();
		ManuscriptDto manuscriptDto3 = mList2.get(0);
		Assert.assertEquals("The Immortals of Meluha", manuscriptDto3.getName());
	}
}
