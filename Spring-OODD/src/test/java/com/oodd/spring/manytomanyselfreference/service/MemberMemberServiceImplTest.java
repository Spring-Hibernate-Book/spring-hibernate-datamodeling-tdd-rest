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
import com.oodd.spring.manytomanyselfreference.dto.MemberMemberDto;

@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration( locations = { "classpath:context.xml" } )
@TransactionConfiguration( defaultRollback = true )
@Transactional
public class MemberMemberServiceImplTest {

	@Autowired
	private MemberService memberService;
	
	@Autowired
	private MemberMemberService memberMemberService;
	
	@Test
	public void testFindAll() {
		Assert.assertEquals(0L, memberMemberService.findAll().size());
	}
	
	@Test
	public void testCreate() {
		MemberMemberDto memberMemberDto = new MemberMemberDto();
		
		MemberDto memberDto1 = new MemberDto();
		memberDto1.setName("Sara Tencradi");
		memberService.create(memberDto1);
		
		MemberDto memberDto2 = new MemberDto();
		memberDto2.setName("Mike Scofield");
		memberService.create(memberDto2);
		
		List<MemberDto> memberDtos1 = memberService.findAll();
		MemberDto memberDto3 = memberDtos1.get(0);
		
		List<MemberDto> memberDtos2 = memberService.findAll();
		MemberDto memberDto4 = memberDtos2.get(0);
		
		memberMemberDto.setMemberId1(memberDto3);
		memberMemberDto.setMemberId2(memberDto4);
		
		memberMemberService.create(memberMemberDto);	
		Assert.assertEquals(1L, memberMemberService.findAll().size());
	}
	
	@Test
	public void testIsPresent() {	
		MemberMemberDto memberMemberDto = new MemberMemberDto();
		
		MemberDto memberDto1 = new MemberDto();
		memberDto1.setName("Sara Tencradi");
		memberService.create(memberDto1);
		
		MemberDto memberDto2 = new MemberDto();
		memberDto2.setName("Mike Scofield");
		memberService.create(memberDto2);
		
		List<MemberDto> memberDtos1 = memberService.findAll();
		MemberDto memberDto3 = memberDtos1.get(0);
		
		List<MemberDto> memberDtos2 = memberService.findAll();
		MemberDto memberDto4 = memberDtos2.get(0);
		
		memberMemberDto.setMemberId1(memberDto3);
		memberMemberDto.setMemberId2(memberDto4);		
		
		memberMemberService.create(memberMemberDto);	
		Assert.assertEquals(1L, memberMemberService.findAll().size());		
		
		boolean status = memberMemberService.isPresent(memberMemberDto);		
		Assert.assertTrue(status);
	}
	
	@Test
	public void testRemove() {	
		MemberMemberDto memberMemberDto = new MemberMemberDto();
		
		MemberDto memberDto1 = new MemberDto();
		memberDto1.setName("Sara Tencradi");
		memberService.create(memberDto1);
		
		MemberDto memberDto2 = new MemberDto();
		memberDto2.setName("Mike Scofield");
		memberService.create(memberDto2);
		
		List<MemberDto> memberDtos1 = memberService.findAll();
		MemberDto memberDto3 = memberDtos1.get(0);
		
		List<MemberDto> memberDtos2 = memberService.findAll();
		MemberDto memberDto4 = memberDtos2.get(0);
		
		memberMemberDto.setMemberId1(memberDto3);
		memberMemberDto.setMemberId2(memberDto4);		
		
		memberMemberService.create(memberMemberDto);	
		Assert.assertEquals(1L, memberMemberService.findAll().size());
		
		List<MemberMemberDto> memberMemberList = memberMemberService.findAll();
		MemberMemberDto memberMemberDto1 = memberMemberList.get(0);
		memberMemberService.remove(memberMemberDto1);
		
		Assert.assertEquals(0L, memberMemberService.findAll().size());
	}
}