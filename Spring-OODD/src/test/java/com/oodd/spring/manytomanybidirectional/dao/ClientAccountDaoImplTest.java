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
import com.oodd.spring.manytomanybidirectional.entity.Client;

@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration( locations = { "classpath:context.xml" } )
@TransactionConfiguration( defaultRollback = true )
@Transactional
public class ClientAccountDaoImplTest {

	@Autowired
	private ClientDao clientDao;
	
	@Autowired
	private AccountDao accountDao;
	
	@Autowired
	private ClientAccountDao clientAccountDao;
	
	@Test
	public void testGetAll() {
		Assert.assertEquals(0L, clientAccountDao.getAll().size());
	}
	
	@Test
	public void testIsPresent() {
		boolean status = false;
		Client c1 = new Client();
		c1.setName("Alexander Mahone");
		clientDao.insert(c1);
		
		Account a1 = new Account();
		a1.setNumber("Credit Account");
		accountDao.insert(a1);
		
		List<Client> clientList = clientDao.getAll();		
		Client client = clientList.get(0);
		
		List<Account> accountList = accountDao.getAll();		
		Account account = accountList.get(0);
		client.getAccounts().add(account);
		
		clientDao.insert(client);
		
		List<Client> clientList2 = clientAccountDao.isPresent(client.getId(), account.getId());
		if(null != clientList2) {			
			if(clientList2.size() > 0) {
				status = true;
			}
		}
		Assert.assertTrue(status);
	}
}
