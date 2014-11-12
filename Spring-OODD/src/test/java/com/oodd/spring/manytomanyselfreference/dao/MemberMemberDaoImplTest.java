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
public class MemberMemberDaoImplTest {

	@Autowired
	private MemberDao memberDao;
	
	@Autowired
	private MemberMemberDao memberMemberDao;
	
	@Test
	public void testGetAll() {
		Assert.assertEquals(0L, memberMemberDao.getAll().size());
	}
	
	@Test
	public void testIsPresent() {
		boolean status = false;
		Member m1 = new Member();
		m1.setName("Alexander Mahone");
		memberDao.insert(m1);
		
		Member m2 = new Member();
		m2.setName("Credit Member");
		memberDao.insert(m2);		
		
		List<Member> memberList = memberDao.getAll();		
		Member member = memberList.get(0);
		
		List<Member> memberList2 = memberDao.getAll();		
		Member member2 = memberList2.get(0);
		
		member.getMembers1().add(member2);
		
		memberDao.insert(member);
		
		List<Member> memberList3 = memberMemberDao.isPresent(member.getId(), member2.getId());
		if(null != memberList3) {			
			if(memberList3.size() > 0) {
				status = true;
			}
		}
		Assert.assertTrue(status);
	}
}
