package com.oodd.spring.onetomanyunidirectional.entity;

import java.util.ArrayList;
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
public class PersonTest {
	@Autowired
	private SessionFactory sessionFactory;
	@Test
	public void testCRUD()
	{
		Person p1 = new Person();
		p1.setName("Alex");
		List<Phone> phones = new ArrayList<Phone>();
		Phone ph1 = new Phone();
		ph1.setNumber("7798989138");
		Phone ph2 = new Phone();
		ph2.setNumber("7798989169");
		phones.add(ph1);
		phones.add(ph2);
		p1.setPhones(phones);
		sessionFactory.getCurrentSession().save(p1);
		
		Person p2 = new Person();
		p2.setName("Fred");
		List<Phone> phones2 = new ArrayList<Phone>();
		Phone phone1 = new Phone();
		phone1.setNumber("8836987");
		phones2.add(phone1);
		p2.setPhones(phones2);
		sessionFactory.getCurrentSession().save(p2);
		
		@SuppressWarnings("unchecked")
		List<Person> persons = sessionFactory.getCurrentSession()
				.createQuery("select p from Person p order by id asc").list();
		Assert.assertEquals(2L,persons.size());
		
		Person p = persons.get(0);
		p.setName("Jhonson");
		sessionFactory.getCurrentSession().update(p);
		
		@SuppressWarnings("unchecked")
		List<Person> persons2 = sessionFactory.getCurrentSession()
				.createQuery("select p from Person p order by id asc").list();
		
		Person tp = persons2.get(0);
		Assert.assertEquals("Jhonson", tp.getName());
		
		sessionFactory.getCurrentSession().delete(tp);
		
		@SuppressWarnings({ "unchecked" })
		List<Person> persons3 = sessionFactory.getCurrentSession()
				.createQuery("select p from Person p order by id asc").list();
		Assert.assertEquals(1L,persons3.size());
	}
}
