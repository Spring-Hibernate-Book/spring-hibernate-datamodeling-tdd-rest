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

import com.oodd.spring.manytomanybidirectional.dto.AccountDto;

@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration( locations = { "classpath:context.xml" } )
@TransactionConfiguration( defaultRollback = true )
@Transactional
public class AccountServiceImplTest {
	
	@Autowired
	private AccountService service;
	
	@Test
	public void testCreate() {
		AccountDto account1 = new AccountDto();
		account1.setNumber("Savings Account");
		service.create(account1);
		
		AccountDto account2 = new AccountDto();
		account2.setNumber("Credit Account");
		service.create(account2);
	
		Assert.assertEquals(2L, service.findAll().size());
	}

	@Test
	public void testFindAll() {
		Assert.assertEquals(0L, service.findAll().size());
	}
	
	@Test
	public void testFindById() {
		AccountDto account1 = new AccountDto();
		account1.setNumber("Savings Account");
		service.create(account1);
	
		List<AccountDto> aList = service.findAll();
		AccountDto account = aList.get(0);
		
		AccountDto account2 = service.findById(account.getId());
		Assert.assertEquals("Savings Account", account2.getNumber());
	}
	
	@Test
	public void testRemove() {
		AccountDto account1 = new AccountDto();
		account1.setNumber("Savings Account");
		service.create(account1);
			
		AccountDto account2 = new AccountDto();
		account2.setNumber("Credit Account");
		service.create(account2);
	
		Assert.assertEquals(2L, service.findAll().size());
		
		List<AccountDto> aList = service.findAll();
		AccountDto account = aList.get(0);
		service.remove(account.getId());
		
		Assert.assertEquals(1L, service.findAll().size());
	}
	
	@Test
	public void testEdit() {
		AccountDto account1 = new AccountDto();
		account1.setNumber("Savings Account");
		service.create(account1);
		
		Assert.assertEquals(1L, service.findAll().size());

		List<AccountDto> aList = service.findAll();
		AccountDto account = aList.get(0);
		account.setNumber("Credit Account");
		
		service.edit(account);
		
		List<AccountDto> aList2 = service.findAll();
		AccountDto account2 = aList2.get(0);
		Assert.assertEquals("Credit Account", account2.getNumber());
	}
}
