package com.oodd.spring.manytomanybidirectional.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.oodd.spring.manytomanybidirectional.dto.ClientDto;

@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration( locations = { "classpath:context.xml" } )
@TransactionConfiguration( defaultRollback = true )
@Transactional
public class ClientServiceImplTest {

	@Autowired
	private ClientService service;
	
	@Test
	public void testCreate() {
		ClientDto aclient = new ClientDto();
		aclient.setName("Alex Mahone");
		service.create(aclient);
		
		ClientDto bclient = new ClientDto();
		bclient.setName("Sara Tencradi");
		service.create(bclient);
	
		Assert.assertEquals(2L, service.findAll().size());
	}

	@Test
	public void testFindAll() {
		Assert.assertEquals(0L, service.findAll().size());
	}
	
	@Test
	public void testFindById() {
		ClientDto aclient = new ClientDto();
		aclient.setName("Alex Mahone");
		service.create(aclient);
	
		List<ClientDto> cList = service.findAll();
		ClientDto client = cList.get(0);
		
		ClientDto client2 = service.findById(client.getId());
		Assert.assertEquals("Alex Mahone", client2.getName());
	}
	
	@Test
	public void testRemove() {
		ClientDto aclient = new ClientDto();
		aclient.setName("Frodo Baggins");
		service.create(aclient);
			
		ClientDto bclient = new ClientDto();
		bclient.setName("Gandalf The Grey");
		service.create(bclient);
	
		Assert.assertEquals(2L, service.findAll().size());
		
		List<ClientDto> cList = service.findAll();
		ClientDto client = cList.get(0);
		service.remove(client.getId());
		
		Assert.assertEquals(1L, service.findAll().size());
	}
	
	@Test
	public void testEdit() {
		ClientDto aclient = new ClientDto();
		aclient.setName("Frodo Baggins");
		service.create(aclient);
		
		Assert.assertEquals(1L, service.findAll().size());

		List<ClientDto> cList = service.findAll();
		ClientDto client = cList.get(0);
		client.setName("Samwise Gamgee");
		
		service.edit(client);
		
		List<ClientDto> cList2 = service.findAll();
		ClientDto client2 = cList2.get(0);
		Assert.assertEquals("Samwise Gamgee", client2.getName());
	}
}
