package com.oodd.spring.manytomanyselfreference.mapper;

import org.springframework.stereotype.Component;

import com.oodd.spring.manytomanyselfreference.dto.MemberDto;
import com.oodd.spring.manytomanyselfreference.entity.Member;

@Component
public class MemberMapper {

	public Member mapDtoToEntity(MemberDto memberDto) {
		Member member = new Member();
		if(null != memberDto.getId()) member.setId(memberDto.getId());
		if(null != memberDto.getName()) member.setName(memberDto.getName());
		return member;
	}
	
	public MemberDto mapEntityToDto(Member member) {
		MemberDto memberDto = new MemberDto();
		if(null != member.getId()) memberDto.setId(member.getId());
		if(null != member.getName()) memberDto.setName(member.getName());
		return memberDto;
	}
}
