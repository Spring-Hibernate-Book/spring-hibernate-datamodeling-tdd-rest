package com.oodd.spring.manytomanyselfreference.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oodd.spring.manytomanyselfreference.dao.MemberDao;
import com.oodd.spring.manytomanyselfreference.dao.MemberMemberDao;
import com.oodd.spring.manytomanyselfreference.dto.MemberDto;
import com.oodd.spring.manytomanyselfreference.dto.MemberMemberDto;
import com.oodd.spring.manytomanyselfreference.entity.Member;
import com.oodd.spring.manytomanyselfreference.mapper.MemberMapper;
import com.oodd.spring.manytomanyselfreference.service.MemberMemberService;

@Service
@Transactional
public class MemberMemberServiceImpl implements MemberMemberService {

	@Autowired
	private MemberDao memberDao;
	
	@Autowired
	private MemberMapper memberMapper;
	
	@Autowired
	private MemberMemberDao memberMemberDao;	
	
	@Override
	public boolean isPresent(MemberMemberDto memberMemberDto) {
		boolean status = false;
		List<Member> memberList = memberMemberDao.isPresent(memberMemberDto.getMemberId1().getId(), memberMemberDto.getMemberId2().getId());
		if(memberList.size() > 0) {
			status = true;
		} else {
			memberList = memberMemberDao.isPresent(memberMemberDto.getMemberId2().getId(), memberMemberDto.getMemberId1().getId());
			if(memberList.size() > 0) {
				status = true;
			}
		}
		return status;
	}

	@Override
	public List<MemberMemberDto> findAll() {
		List<MemberMemberDto> memberMemberDtos = new ArrayList<MemberMemberDto>();
		List<Member> memberList = memberMemberDao.getAll();
		for(Member member : memberList) {			
			MemberDto memberDto = memberMapper.mapEntityToDto(member);			
			Set<Member> members1 = member.getMembers1();
			for(Member member2 : members1) {
				MemberMemberDto memberMemberDto = new MemberMemberDto();
				memberMemberDto.setMemberId1(memberDto);
				MemberDto memberDto2 = memberMapper.mapEntityToDto(member2);
				memberMemberDto.setMemberId2(memberDto2);
				memberMemberDtos.add(memberMemberDto);
			}			
		}
		return memberMemberDtos;
	}

	@Override
	public void create(MemberMemberDto memberMemberDto) {
		Integer memberid1 = memberMemberDto.getMemberId1().getId();
		Integer memberid2 = memberMemberDto.getMemberId2().getId();
		
		Member member1 = memberDao.getById(memberid1);
		Member member2 = memberDao.getById(memberid2);
		member1.getMembers1().add(member2);
		
		memberDao.insert(member1);
	}

	@Override
	public void remove(MemberMemberDto memberMemberDto) {
		Integer memberid1 = memberMemberDto.getMemberId1().getId();
		Integer memberid2 = memberMemberDto.getMemberId2().getId();
		
		Member member1 = memberDao.getById(memberid1);
		Member member2 = memberDao.getById(memberid2);
		
		member1.getMembers1().remove(member2);
		memberDao.update(member1);
	}
}