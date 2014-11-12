package com.oodd.spring.manytomanybidirectional.entity;

import java.util.List;

import org.hibernate.SessionFactory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration( locations = { "classpath:context.xml" } )
@TransactionConfiguration( defaultRollback = true )
@Transactional
public class ClientTest {

	@Autowired
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	@Test
	public void testCRUD() {
		Client client1 = new Client();
		client1.setName("Amritendu De");
		
		Client client2 = new Client();
		client2.setName("Lalit Narayan Mishra");
		
		sessionFactory.getCurrentSession().save(client1);
		sessionFactory.getCurrentSession().save(client2);
		
		client1.setName("Hazekul Alam");
		sessionFactory.getCurrentSession().merge(client1);

		List<Client> list = sessionFactory.getCurrentSession().createQuery("from Client").list();
		Assert.assertEquals(2L, list.size());
		
		sessionFactory.getCurrentSession().delete(client1);
		
		List<Client> list2 = sessionFactory.getCurrentSession().createQuery("from Client").list();
		Assert.assertEquals(1L, list2.size());
		
		Account account1 = new Account();
		account1.setNumber("Account 1");
		
		Account account2 = new Account();
		account2.setNumber("Account 2");
		
		sessionFactory.getCurrentSession().save(account1);
		sessionFactory.getCurrentSession().save(account2);
		
		Client client3 = list2.get(0);
		client3.getAccounts().add(account1);
		client3.getAccounts().add(account2);
		
		sessionFactory.getCurrentSession().merge(client3);
		
		List<Client> list3 = sessionFactory.getCurrentSession().createQuery("from Client").list();
		Assert.assertEquals(1L, list2.size());
		
		Client client4 = list3.get(0);

		Assert.assertEquals(2L, client4.getAccounts().size());
	}
}
