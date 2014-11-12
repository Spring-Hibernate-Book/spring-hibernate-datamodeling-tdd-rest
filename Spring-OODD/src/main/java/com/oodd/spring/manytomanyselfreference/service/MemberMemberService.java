package com.oodd.spring.manytomanyselfreference.service;

import java.util.List;

import com.oodd.spring.manytomanyselfreference.dto.MemberMemberDto;

public interface MemberMemberService {
	public boolean isPresent(MemberMemberDto memberMemberDto);
	public List<MemberMemberDto> findAll();
	public void create(MemberMemberDto memberMemberDto);
	public void remove(MemberMemberDto memberMemberDto);
}