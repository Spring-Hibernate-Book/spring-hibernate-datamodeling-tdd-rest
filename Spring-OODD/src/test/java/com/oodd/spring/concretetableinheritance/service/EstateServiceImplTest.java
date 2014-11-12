/**
 * 
 */
package com.oodd.spring.concretetableinheritance.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.oodd.spring.concretetableinheritance.dto.BuildingDto;
import com.oodd.spring.concretetableinheritance.dto.EstateDto;
import com.oodd.spring.concretetableinheritance.dto.LandDto;
import com.oodd.spring.concretetableinheritance.service.EstateService;

@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration( locations = { "classpath:context.xml" } )
@TransactionConfiguration( defaultRollback = true )
@Transactional
public class EstateServiceImplTest {
	@Autowired
	private EstateService service;
	
	@Test
	public void testCreate() {
		EstateDto bestate = new BuildingDto();
		bestate.setName("Majestic Estate");
		((BuildingDto)bestate).setFloors(10);
		service.create(bestate);
		
		EstateDto hEstate = new LandDto();
		hEstate.setName("Royal Estate");
		((LandDto)hEstate).setArea(200);
		service.create(hEstate);
	
		Assert.assertEquals(2L, service.findAll().size());
	}

	@Test
	public void testFindAll() {
		Assert.assertEquals(0L, service.findAll().size());
	}
	
	@Test
	public void testFindById() {
		EstateDto bestate = new BuildingDto();
		bestate.setName("Majestic Estate");
		((BuildingDto)bestate).setFloors(10);
		service.create(bestate);
	
		List<EstateDto> pList = service.findAll();
		EstateDto estate = pList.get(0);
		
		EstateDto estate2 = service.findById(estate.getId());
		Assert.assertEquals("Majestic Estate", estate2.getName());
	}
	
	@Test
	public void testRemove() {
		EstateDto bestate = new BuildingDto();
		bestate.setName("Majestic Estate");
		((BuildingDto)bestate).setFloors(10);
		service.create(bestate);
		
		EstateDto hEstate = new LandDto();
		hEstate.setName("Royal Estate");
		((LandDto)hEstate).setArea(200);
		service.create(hEstate);
	
		Assert.assertEquals(2L, service.findAll().size());
		
		List<EstateDto> pList = service.findAll();
		EstateDto estate = pList.get(0);
		service.remove(estate.getId());
		
		Assert.assertEquals(1L, service.findAll().size());
	}
	
	@Test
	public void testEdit() {
		EstateDto bestate = new BuildingDto();
		bestate.setName("Majestic Estate");
		((BuildingDto)bestate).setFloors(10);
		service.create(bestate);
		
		Assert.assertEquals(1L, service.findAll().size());

		List<EstateDto> pList = service.findAll();
		EstateDto estate = pList.get(0);
		estate.setName("Royal Estate");
		
		service.edit(estate);
		
		List<EstateDto> pList2 = service.findAll();
		EstateDto estate2 = pList2.get(0);
		Assert.assertEquals("Royal Estate", estate2.getName());
	}
	
}
