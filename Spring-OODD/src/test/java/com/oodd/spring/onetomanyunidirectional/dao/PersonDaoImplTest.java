package com.oodd.spring.onetomanyunidirectional.dao;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.oodd.spring.onetomanyunidirectional.entity.Person;
import com.oodd.spring.onetomanyunidirectional.entity.Phone;

@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration( locations = { "classpath:context.xml" } )
@TransactionConfiguration( defaultRollback = true )
@Transactional
public class PersonDaoImplTest {
	@Autowired
	private PersonDao dao ;
	@Test
	public void testInsert(){
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
		dao.insert(p1);
		Assert.assertEquals(1L, dao.getAll().size());
	}
	@Test
	public void testGetAll() {
		Assert.assertEquals(0L, dao.getAll().size());
	}
	@Test
	public void testGetById() {
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
		dao.insert(p1);
		
		Person p2 = new Person();
		p2.setName("Fred");
		List<Phone> phones2 = new ArrayList<Phone>();
		Phone phone1 = new Phone();
		phone1.setNumber("8836987");
		phones.add(phone1);
		p2.setPhones(phones2);
		dao.insert(p2);
		Assert.assertEquals(2L,dao.getAll().size());
		
		List<Person> persons = dao.getAll();
		Person p3 = persons.get(1);
		
		Person p4 = dao.getById(p3.getId());
		Assert.assertEquals("Alex",p4.getName());
		
	}
	@Test
	public void testDelete() {
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
		dao.insert(p1);
		
		Person p2 = new Person();
		p2.setName("Fred");
		List<Phone> phones2 = new ArrayList<Phone>();
		Phone phone1 = new Phone();
		phone1.setNumber("8836987");
		phones.add(phone1);
		p2.setPhones(phones2);
		dao.insert(p2);
		Assert.assertEquals(2L,dao.getAll().size());
		
		List<Person> persons = dao.getAll();
		Person p = persons.get(1);
		dao.delete(p);
		Assert.assertEquals(1L,dao.getAll().size());
	}
	@Test
	public void testUpdate() {
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
		dao.insert(p1);
		
		Assert.assertEquals(1L,dao.getAll().size());
		
		List<Person> persons = dao.getAll();
		Person p = persons.get(0);
		p.setName("Jhon");
		List<Phone> phones1 = new ArrayList<Phone>();
		Phone ph3 = new Phone();
		ph1.setNumber("111111111");
		Phone ph4 = new Phone();
		ph2.setNumber("222222222");
		Phone ph5 = new Phone();
		ph2.setNumber("333333333");
		phones1.add(ph3);
		phones1.add(ph4);
		phones1.add(ph5);
		p.setPhones(phones1);
		dao.update(p);
		persons = dao.getAll();
		Person p2=persons.get(0);
		Assert.assertEquals("Jhon",p2.getName());
		Assert.assertEquals(3L, p2.getPhones().size());
	}
}
