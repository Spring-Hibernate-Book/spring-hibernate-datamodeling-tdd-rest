package com.oodd.spring.singletableinheritance.entity;

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
public class SNMPTest {
	@Autowired
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	@Test
	public void testCRUD()
	{
		Protocol p1 = new Protocol();
		p1.setName("Protocol");		
		sessionFactory.getCurrentSession().save(p1);
		
		SNMP snmp = new SNMP();
		snmp.setName("SNMP");
		sessionFactory.getCurrentSession().save(snmp);
		
		snmp.setName("SNMP Protocol");
		sessionFactory.getCurrentSession().merge(snmp);

		List<SNMP> list = sessionFactory.getCurrentSession().createQuery("from SNMP").list();
		Assert.assertEquals(1L, list.size());
		
		sessionFactory.getCurrentSession().delete(snmp);
		
		List<SNMP> list2 = sessionFactory.getCurrentSession().createQuery("from SNMP").list();
		Assert.assertEquals(0L, list2.size());
	}
}
