package com.oodd.spring.manytomanyselfreference.service;

import java.util.List;

import com.oodd.spring.manytomanyselfreference.dto.MemberDto;

public interface MemberService {
	public void create(MemberDto memberDto) ;
	public List<MemberDto> findAll();
	public MemberDto findById(Integer id);
	public void remove(Integer id);
	public void edit(MemberDto memberDto);
}
