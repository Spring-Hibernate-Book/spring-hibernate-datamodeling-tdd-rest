package com.oodd.spring.onetomanyunidirectional.service;

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

import com.oodd.spring.onetomanyunidirectional.dto.PersonDto;

@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration( locations = { "classpath:context.xml" } )
@TransactionConfiguration( defaultRollback = true )
@Transactional
public class PersonServiceImplTest {
	@Autowired
	private PersonService service;
	@Test
	public void testCreate(){
		PersonDto personDto1 = new PersonDto();
		personDto1.setName("Alex");
		List<String> phones = new ArrayList<String>();
		phones.add("9158798405");
		phones.add("7798989134");
		personDto1.setNumbers(phones);
		service.create(personDto1);
		Assert.assertEquals(1L, service.findAll().size());
		
		List<PersonDto> personDtos = service.findAll();
		PersonDto dto = personDtos.get(0);
		Assert.assertEquals(2L, dto.getNumbers().size());
	}
	@Test
	public void testEdit(){
		PersonDto personDto1 = new PersonDto();
		personDto1.setName("Alex");
		List<String> phones = new ArrayList<String>();
		phones.add("9158798405");
		phones.add("7798989134");
		personDto1.setNumbers(phones);
		service.create(personDto1);
		Assert.assertEquals(1L, service.findAll().size());
		
		List<PersonDto> personDtos = service.findAll();
		PersonDto dto = personDtos.get(0);
		phones = new ArrayList<String>();
		phones.add("9158798406");
		phones.add("9158798407");
		phones.add("9158798408");
		dto.setNumbers(phones);
		service.edit(dto);
		Assert.assertEquals(3L, service.findAll().get(0).getNumbers().size());
	}
	@Test
	public void testFindAll(){
		Assert.assertEquals(0L, service.findAll().size());
	}
	@Test
	public void testFindById() {
		PersonDto personDto1 = new PersonDto();
		personDto1.setName("Alex");
		List<String> phones = new ArrayList<String>();
		phones.add("9158798405");
		phones.add("7798989134");
		personDto1.setNumbers(phones);
		service.create(personDto1);
		
		List<PersonDto> personDtos = service.findAll();
		PersonDto dto = personDtos.get(0);
		PersonDto dto1 = service.findById(dto.getId());
		Assert.assertEquals("Alex", dto1.getName());
		Assert.assertEquals(2L, dto1.getNumbers().size());
	}
	@Test
	public void testRemove() {
		PersonDto personDto1 = new PersonDto();
		personDto1.setName("Alex");
		List<String> phones = new ArrayList<String>();
		phones.add("9158798405");
		phones.add("7798989134");
		personDto1.setNumbers(phones);
		service.create(personDto1);
		
		PersonDto personDto2 = new PersonDto();
		personDto2.setName("Fred");
		List<String> phones2 = new ArrayList<String>();
		phones2.add("9158798408");
		phones2.add("7798989139");
		personDto2.setNumbers(phones2);
		service.create(personDto1);
		Assert.assertEquals(2L, service.findAll().size());
		
		List<PersonDto> personDtos = service.findAll();
		PersonDto deletePerson = personDtos.get(1);
		service.remove(deletePerson.getId());
		Assert.assertEquals(1L, service.findAll().size());
	}
}
