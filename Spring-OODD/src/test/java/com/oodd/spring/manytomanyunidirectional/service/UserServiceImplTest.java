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

import com.oodd.spring.manytomanyunidirectional.dto.UserDto;

@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration( locations = { "classpath:context.xml" } )
@TransactionConfiguration( defaultRollback = true )
@Transactional
public class UserServiceImplTest {
	@Autowired
	private UserService service;
	
	@Test
	public void testCreate() {
		UserDto auser = new UserDto();
		auser.setName("Alex Mahone");
		service.create(auser);
		
		UserDto buser = new UserDto();
		buser.setName("Sara Tencradi");
		service.create(buser);
	
		Assert.assertEquals(2L, service.findAll().size());
	}

	@Test
	public void testFindAll() {
		Assert.assertEquals(0L, service.findAll().size());
	}
	
	@Test
	public void testFindById() {
		UserDto auser = new UserDto();
		auser.setName("Alex Mahone");
		service.create(auser);
	
		List<UserDto> uList = service.findAll();
		UserDto user = uList.get(0);
		
		UserDto user2 = service.findById(user.getId());
		Assert.assertEquals("Alex Mahone", user2.getName());
	}
	
	@Test
	public void testRemove() {
		UserDto auser = new UserDto();
		auser.setName("Frodo Baggins");
		service.create(auser);
		
		UserDto buser = new UserDto();
		buser.setName("Gandalf The Grey");
		service.create(buser);
	
		Assert.assertEquals(2L, service.findAll().size());
		
		List<UserDto> uList = service.findAll();
		UserDto user = uList.get(0);
		service.remove(user.getId());
		
		Assert.assertEquals(1L, service.findAll().size());
	}
	
	@Test
	public void testEdit() {
		UserDto auser = new UserDto();
		auser.setName("Frodo Baggins");
		service.create(auser);
		
		Assert.assertEquals(1L, service.findAll().size());

		List<UserDto> uList = service.findAll();
		UserDto user = uList.get(0);
		user.setName("Samwise Gamgee");
		
		service.edit(user);
		
		List<UserDto> uList2 = service.findAll();
		UserDto user2 = uList2.get(0);
		Assert.assertEquals("Samwise Gamgee", user2.getName());
	}	
}