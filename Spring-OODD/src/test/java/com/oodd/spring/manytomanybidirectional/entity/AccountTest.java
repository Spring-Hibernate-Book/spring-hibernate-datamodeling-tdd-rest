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

import com.oodd.spring.manytomanyunidirectional.entity.User;

@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration( locations = { "classpath:context.xml" } )
@TransactionConfiguration( defaultRollback = true )
@Transactional
public class AccountTest {
	@Autowired
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	@Test
	public void testCRUD() {
		Account account1 = new Account();
		account1.setNumber("Account 1");
		
		Account account2 = new Account();
		account2.setNumber("Account 2");
		
		sessionFactory.getCurrentSession().save(account1);
		sessionFactory.getCurrentSession().save(account2);
		
		account1.setNumber("Account 3");
		sessionFactory.getCurrentSession().merge(account1);

		List<Account> list = sessionFactory.getCurrentSession().createQuery("from Account").list();
		Assert.assertEquals(2L, list.size());
		
		sessionFactory.getCurrentSession().delete(account1);
		
		List<Account> list2 = sessionFactory.getCurrentSession().createQuery("from Account").list();
		Assert.assertEquals(1L, list2.size());
	}
}
