package com.oodd.spring.onetoonebidirectional.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.oodd.spring.onetoonebidirectional.dto.CustomerDto;
@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration( locations = { "classpath:context.xml" } )
@TransactionConfiguration( defaultRollback = true )
@Transactional
public class CustomerServiceImplTest {
	@Autowired
	private CustomerService service ;
	@Test
	public void testCreate() {
		CustomerDto customerDto1 = new CustomerDto();
		customerDto1.setName("Alex");
		customerDto1.setAmount(200);
		service.create(customerDto1);
		
		CustomerDto customerDto2 = new CustomerDto();
		customerDto2.setName("Fred");
		customerDto2.setAmount(400);
		service.create(customerDto2);
		
		Assert.assertEquals(2L, service.findAll().size());
	}
	@Test
	public void testFindAll() {
		Assert.assertEquals(0L, service.findAll().size());
	}
	@Test
	public void testFindById() {
		CustomerDto customerDto1 = new CustomerDto();
		customerDto1.setName("Alex");
		customerDto1.setAmount(200);
		service.create(customerDto1);
		
		List<CustomerDto> customerDtos = service.findAll();
		CustomerDto customerDto = customerDtos.get(0);
		
		CustomerDto customerDto2 = service.findById(customerDto.getId());
		Assert.assertEquals("Alex", customerDto2.getName());
	}
	@Test
	public void testRemove() {
		CustomerDto customerDto1 = new CustomerDto();
		customerDto1.setName("Alex");
		customerDto1.setAmount(200);
		service.create(customerDto1);
		
		CustomerDto customerDto2 = new CustomerDto();
		customerDto2.setName("Fred");
		customerDto2.setAmount(400);
		service.create(customerDto2);
		
		Assert.assertEquals(2L, service.findAll().size());
		
		List<CustomerDto> customerDtos = service.findAll();
		
		CustomerDto customerDto = customerDtos.get(1);
		service.remove(customerDto.getId());
		Assert.assertEquals(1L, service.findAll().size());
	}
	@Test
	public void testEdit() {
		CustomerDto customerDto1 = new CustomerDto();
		customerDto1.setName("Alex");
		customerDto1.setAmount(200);
		service.create(customerDto1);
		
		customerDto1.setName("Rose");
		service.edit(customerDto1);
		
		List<CustomerDto> customerDtos = service.findAll();
		CustomerDto customerDto2 = customerDtos.get(0);
		Assert.assertEquals("Rose", customerDto2.getName());
	}
}
