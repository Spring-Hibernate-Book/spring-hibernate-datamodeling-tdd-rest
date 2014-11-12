package com.oodd.spring.manytomanyselfreference.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.oodd.spring.manytomanyselfreference.dto.MemberDto;

@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration( locations = { "classpath:context.xml" } )
@TransactionConfiguration( defaultRollback = true )
@Transactional
public class MemberServiceImplTest {

	@Autowired
	private MemberService service;
	
	@Test
	public void testCreate() {
		MemberDto amember = new MemberDto();
		amember.setName("Alex Mahone");
		service.create(amember);
		
		MemberDto bmember = new MemberDto();
		bmember.setName("Sara Tencradi");
		service.create(bmember);
	
		Assert.assertEquals(2L, service.findAll().size());
	}

	@Test
	public void testFindAll() {
		Assert.assertEquals(0L, service.findAll().size());
	}
	
	@Test
	public void testFindById() {
		MemberDto amember = new MemberDto();
		amember.setName("Alex Mahone");
		service.create(amember);
	
		List<MemberDto> cList = service.findAll();
		MemberDto member = cList.get(0);
		
		MemberDto member2 = service.findById(member.getId());
		Assert.assertEquals("Alex Mahone", member2.getName());
	}
	
	@Test
	public void testRemove() {
		MemberDto amember = new MemberDto();
		amember.setName("Frodo Baggins");
		service.create(amember);
			
		MemberDto bmember = new MemberDto();
		bmember.setName("Gandalf The Grey");
		service.create(bmember);
	
		Assert.assertEquals(2L, service.findAll().size());
		
		List<MemberDto> cList = service.findAll();
		MemberDto member = cList.get(0);
		service.remove(member.getId());
		
		Assert.assertEquals(1L, service.findAll().size());
	}
	
	@Test
	public void testEdit() {
		MemberDto amember = new MemberDto();
		amember.setName("Frodo Baggins");
		service.create(amember);
		
		Assert.assertEquals(1L, service.findAll().size());

		List<MemberDto> cList = service.findAll();
		MemberDto member = cList.get(0);
		member.setName("Samwise Gamgee");
		
		service.edit(member);
		
		List<MemberDto> cList2 = service.findAll();
		MemberDto member2 = cList2.get(0);
		Assert.assertEquals("Samwise Gamgee", member2.getName());
	}
}
