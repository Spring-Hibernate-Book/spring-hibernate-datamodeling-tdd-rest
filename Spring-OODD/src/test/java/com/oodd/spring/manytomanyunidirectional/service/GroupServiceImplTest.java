/**
 * 
 */
package com.oodd.spring.manytomanyunidirectional.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.oodd.spring.manytomanyunidirectional.dto.GroupDto;

@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration( locations = { "classpath:context.xml" } )
@TransactionConfiguration( defaultRollback = true )
@Transactional
public class GroupServiceImplTest {
	@Autowired
	private GroupService service;
	
	@Test
	public void testCreate() {
		GroupDto agroup = new GroupDto();
		agroup.setName("Lord of the Rings");
		service.create(agroup);
		
		GroupDto bgroup = new GroupDto();
		bgroup.setName("Prison Break");
		service.create(bgroup);
	
		Assert.assertEquals(2L, service.findAll().size());
	}

	@Test
	public void testFindAll() {
		Assert.assertEquals(0L, service.findAll().size());
	}
	
	@Test
	public void testFindById() {
		GroupDto agroup = new GroupDto();
		agroup.setName("Lord of the Rings");
		service.create(agroup);
	
		List<GroupDto> gList = service.findAll();
		GroupDto group = gList.get(0);
		
		GroupDto group2 = service.findById(group.getId());
		Assert.assertEquals("Lord of the Rings", group2.getName());
	}
	
	@Test
	public void testRemove() {
		GroupDto agroup = new GroupDto();
		agroup.setName("Lord of the Rings");
		service.create(agroup);
		
		GroupDto bgroup = new GroupDto();
		bgroup.setName("Prison Break");
		service.create(bgroup);
	
		Assert.assertEquals(2L, service.findAll().size());
		
		List<GroupDto> uList = service.findAll();
		GroupDto group = uList.get(0);
		service.remove(group.getId());
		
		Assert.assertEquals(1L, service.findAll().size());
	}
	
	@Test
	public void testEdit() {
		GroupDto agroup = new GroupDto();
		agroup.setName("Lord of the Rings");
		service.create(agroup);
		
		Assert.assertEquals(1L, service.findAll().size());

		List<GroupDto> gList = service.findAll();
		GroupDto group = gList.get(0);
		group.setName("Prison Break");
		
		service.edit(group);
		
		List<GroupDto> gList2 = service.findAll();
		GroupDto group2 = gList2.get(0);
		Assert.assertEquals("Prison Break", group2.getName());
	}	
}