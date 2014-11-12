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

import com.oodd.spring.manytomanybidirectional.entity.Account;

@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration( locations = { "classpath:context.xml" } )
@TransactionConfiguration( defaultRollback = true )
@Transactional
public class AccountDaoImplTest {

	@Autowired
	private AccountDao dao;
	
	@Test
	public void testInsert() {
		Account account1 = new Account();
		account1.setNumber("Savings Account");
		dao.insert(account1);
		
		Account account2 = new Account();
		account2.setNumber("Credit Account");
		dao.insert(account2);
	
		Assert.assertEquals(2L, dao.getAll().size());
	}

	@Test
	public void testGetAll() {
		Account account1 = new Account();
		account1.setNumber("Savings Account");
		dao.insert(account1);
		
		Assert.assertEquals(1L, dao.getAll().size());
	}
	
	@Test
	public void testGetById() {
		Account account1 = new Account();
		account1.setNumber("Savings Account");
		dao.insert(account1);
	
		List<Account> accountList = dao.getAll();
		Account account2 = accountList.get(0);
		
		Account account3 = dao.getById(account2.getId());
		Assert.assertEquals("Savings Account", account3.getNumber());
	}
	
	@Test
	public void testDelete() {
		Account account1 = new Account();
		account1.setNumber("Savings Account");
		dao.insert(account1);
		
		Account account2 = new Account();
		account2.setNumber("Credit Account");
		dao.insert(account2);
	
		Assert.assertEquals(2L, dao.getAll().size());
		
		dao.delete(account2);
		
		Assert.assertEquals(1L, dao.getAll().size());
	}
	
	@Test
	public void testUpdate() {
		Account account1 = new Account();
		account1.setNumber("Savings Account");
		dao.insert(account1);
		
		Assert.assertEquals(1L, dao.getAll().size());

		List<Account> accountList = dao.getAll();
		Account account2 = accountList.get(0);
		account2.setNumber("Credit Account");
		
		dao.update(account2);
		
		List<Account> accountList2 = dao.getAll();
		Account account3 = accountList2.get(0);
		Assert.assertEquals("Credit Account", account3.getNumber());
	}
}
