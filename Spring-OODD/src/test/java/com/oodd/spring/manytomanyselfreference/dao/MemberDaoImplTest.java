package com.oodd.spring.manytomanyselfreference.dao;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.oodd.spring.manytomanyselfreference.entity.Member;

@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration( locations = { "classpath:context.xml" } )
@TransactionConfiguration( defaultRollback = true )
@Transactional
public class MemberDaoImplTest {

	@Autowired
	private MemberDao dao;
	
	@Test
	public void testInsert() {
		Member member1 = new Member();
		member1.setName("Alexander Mahone");
		dao.insert(member1);
		
		Member member2 = new Member();
		member2.setName("Sara Tencradi");
		dao.insert(member2);
	
		Assert.assertEquals(2L, dao.getAll().size());
	}

	@Test
	public void testGetAll() {
		Assert.assertEquals(0L, dao.getAll().size());
	}
	
	@Test
	public void testGetById() {
		Member member1 = new Member();
		member1.setName("Alexander Mahone");
		dao.insert(member1);
	
		List<Member> cList = dao.getAll();
		Member member = cList.get(0);
		
		Member member2 = dao.getById(member.getId());
		Assert.assertEquals("Alexander Mahone", member2.getName());
	}
	
	@Test
	public void testDelete() {
		Member member1 = new Member();
		member1.setName("Alexander Mahone");
		dao.insert(member1);
		
		Member member2 = new Member();
		member2.setName("Sara Tencradi");
		dao.insert(member2);
	
		Assert.assertEquals(2L, dao.getAll().size());
		
		dao.delete(member2);
		
		Assert.assertEquals(1L, dao.getAll().size());
	}
	
	@Test
	public void testUpdate() {
		Member member1 = new Member();
		member1.setName("Alexander Mahone");
		dao.insert(member1);
		
		Assert.assertEquals(1L, dao.getAll().size());

		List<Member> cList = dao.getAll();
		Member member = cList.get(0);
		member.setName("Amritendu De");
		
		dao.update(member);
		
		List<Member> cList2 = dao.getAll();
		Member member2 = cList2.get(0);
		Assert.assertEquals("Amritendu De", member2.getName());
	}
}
