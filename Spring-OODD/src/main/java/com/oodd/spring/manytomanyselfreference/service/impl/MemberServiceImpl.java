package com.oodd.spring.manytomanyselfreference.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oodd.spring.manytomanyselfreference.dao.MemberDao;
import com.oodd.spring.manytomanyselfreference.dto.MemberDto;
import com.oodd.spring.manytomanyselfreference.entity.Member;
import com.oodd.spring.manytomanyselfreference.mapper.MemberMapper;
import com.oodd.spring.manytomanyselfreference.service.MemberService;

@Service
@Transactional
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberDao memberDao;
	
	@Autowired
	private MemberMapper memberMapper;
	
	
	@Override
	public void create(MemberDto memberDto) {
		Member member = memberMapper.mapDtoToEntity(memberDto);
		memberDao.insert(member);
	}

	@Override
	public List<MemberDto> findAll() {
		List<Member> members = memberDao.getAll();
		List<MemberDto> memberDtos = new ArrayList<MemberDto>();
		for(Member member : members) {
			memberDtos.add(memberMapper.mapEntityToDto(member));
		}
		return memberDtos;
	}

	@Override
	public MemberDto findById(Integer id) {
		Member member = memberDao.getById(id);
		if(null != member) {			
			return memberMapper.mapEntityToDto(member);
		}
		return null;
	}

	@Override
	public void remove(Integer id) {
		Member member = memberDao.getById(id);
		memberDao.delete(member);
	}

	@Override
	public void edit(MemberDto memberDto) {
		Member memberCurrent = memberDao.getById(memberDto.getId());
		Member memberToBeEdited = memberMapper.mapDtoToEntity(memberDto);
		memberToBeEdited.setMembers1(memberCurrent.getMembers1());
		memberDao.update(memberToBeEdited);
	}
}