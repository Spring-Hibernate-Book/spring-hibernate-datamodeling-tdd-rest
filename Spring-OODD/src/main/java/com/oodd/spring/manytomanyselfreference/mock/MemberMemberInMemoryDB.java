package com.oodd.spring.manytomanyselfreference.mock;

import java.util.ArrayList;
import java.util.List;

import com.oodd.spring.manytomanyselfreference.dto.MemberMemberDto;

public enum MemberMemberInMemoryDB {

	INSTANCE;
	
	private static List<MemberMemberDto> list = new ArrayList<MemberMemberDto>();
	
	public void add(MemberMemberDto memberMemberDto) {
		list.add(memberMemberDto);
	}

	public boolean isPresent(MemberMemberDto memberMemberDto) {
		for (MemberMemberDto dto:list) {
			if (dto.getMemberId1().getId() == memberMemberDto.getMemberId1().getId()
					&& dto.getMemberId2().getId() == memberMemberDto.getMemberId2().getId()) {
				return true;
			}  else if (dto.getMemberId1().getId() == memberMemberDto.getMemberId2().getId()
					&& dto.getMemberId2().getId() == memberMemberDto.getMemberId1().getId()) {
				return true;
			}
			
		}
		return false;
	}
	
	public void remove(MemberMemberDto memberMemberDto) {
		MemberMemberDto toRemove = null;
		for (MemberMemberDto dto:list) {
			if (dto.getMemberId1().getId()==memberMemberDto.getMemberId1().getId()
					&& dto.getMemberId2().getId() == memberMemberDto.getMemberId2().getId()) {
				toRemove = dto;
			}
		}			
		if (toRemove!=null) list.remove(toRemove);
	}
	
	public List<MemberMemberDto> findAll() {
		return list;
	}
	public void setList(List<MemberMemberDto> list) {
		MemberMemberInMemoryDB.list = list;
	}
}