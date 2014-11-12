package com.oodd.spring.onetoonebidirectional.dao;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.oodd.spring.onetoonebidirectional.entity.Cart;
import com.oodd.spring.onetoonebidirectional.entity.Customer;

@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration( locations = { "classpath:context.xml" } )
@TransactionConfiguration( defaultRollback = true )
@Transactional
public class CustomerDaoImplTest {
	@Autowired
	private CustomerDao dao ;
	@Test
	public void testInsert(){
		Customer customer1 = new Customer();
		customer1.setName("Alex");
		Cart cart1 = new Cart();
		cart1.setAmount(300.0);
		customer1.setCart(cart1);
		dao.insert(customer1);
		
		Customer customer2 = new Customer();
		customer2.setName("Fred");
		Cart cart2 = new Cart();
		cart2.setAmount(500.0);
		customer2.setCart(cart2);
		dao.insert(customer2);
		
		Assert.assertEquals(2L, dao.getAll().size());
	}
	@Test
	public void testGetAll() {
		Assert.assertEquals(0L, dao.getAll().size());
	}
	@Test
	public void testGetById() {
		Customer customer1 = new Customer();
		customer1.setName("Alex");
		Cart cart1 = new Cart();
		cart1.setAmount(300.0);
		customer1.setCart(cart1);
		dao.insert(customer1);
		
		List<Customer> customers = dao.getAll();
		Customer tempCustomer = customers.get(0);
		Customer cust = dao.getById(tempCustomer.getId());
		Assert.assertEquals(tempCustomer.getName(), cust.getName());
	}
	@Test
	public void testDelete() {
		Customer customer1 = new Customer();
		customer1.setName("Alex");
		Cart cart1 = new Cart();
		cart1.setAmount(300.0);
		customer1.setCart(cart1);
		dao.insert(customer1);
		
		Customer customer2 = new Customer();
		customer2.setName("Fred");
		Cart cart2 = new Cart();
		cart2.setAmount(500.0);
		customer2.setCart(cart2);
		dao.insert(customer2);
		
		Assert.assertEquals(2L, dao.getAll().size());
		
		dao.delete(customer2);
		Assert.assertEquals(1L, dao.getAll().size());
	}
	@Test
	public void testUpdate() {
		Customer customer1 = new Customer();
		customer1.setName("Alex");
		Cart cart1 = new Cart();
		cart1.setAmount(300.0);
		customer1.setCart(cart1);
		dao.insert(customer1);
		Assert.assertEquals(1L, dao.getAll().size());
		
		List<Customer> customers = dao.getAll();
		Customer customer2 = customers.get(0);
		customer2.setName("Fred");
		dao.update(customer2);
		
		List<Customer> customers1 = dao.getAll();
		Customer customer3 = customers1.get(0);
		Assert.assertEquals("Fred", customer3.getName());
	}

}
