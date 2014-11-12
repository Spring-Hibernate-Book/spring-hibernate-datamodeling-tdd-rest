package com.oodd.spring.manytomanybidirectional.dao;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.oodd.spring.manytomanybidirectional.entity.Client;

@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration( locations = { "classpath:context.xml" } )
@TransactionConfiguration( defaultRollback = true )
@Transactional
public class ClientDaoImplTest {

	@Autowired
	private ClientDao dao;
	
	@Test
	public void testInsert() {
		Client client1 = new Client();
		client1.setName("Alexander Mahone");
		dao.insert(client1);
		
		Client client2 = new Client();
		client2.setName("Sara Tencradi");
		dao.insert(client2);
	
		Assert.assertEquals(2L, dao.getAll().size());
	}

	@Test
	public void testGetAll() {
		Client client1 = new Client();
		client1.setName("Alexander Mahone");
		dao.insert(client1);
		
		Assert.assertEquals(1L, dao.getAll().size());
	}
	
	@Test
	public void testGetById() {
		Client client1 = new Client();
		client1.setName("Alexander Mahone");
		dao.insert(client1);
	
		List<Client> cList = dao.getAll();
		Client client = cList.get(0);
		
		Client client2 = dao.getById(client.getId());
		Assert.assertEquals("Alexander Mahone", client2.getName());
	}
	
	@Test
	public void testDelete() {
		Client client1 = new Client();
		client1.setName("Alexander Mahone");
		dao.insert(client1);
		
		Client client2 = new Client();
		client2.setName("Sara Tencradi");
		dao.insert(client2);
	
		Assert.assertEquals(2L, dao.getAll().size());
		
		dao.delete(client2);
		
		Assert.assertEquals(1L, dao.getAll().size());
	}
	
	@Test
	public void testUpdate() {
		Client client1 = new Client();
		client1.setName("Alexander Mahone");
		dao.insert(client1);
		
		Assert.assertEquals(1L, dao.getAll().size());

		List<Client> cList = dao.getAll();
		Client client = cList.get(0);
		client.setName("Amritendu De");
		
		dao.update(client);
		
		List<Client> cList2 = dao.getAll();
		Client client2 = cList2.get(0);
		Assert.assertEquals("Amritendu De", client2.getName());
	}
}
