package com.oodd.spring.manytomanyselfreference.mock;

import java.util.ArrayList;
import java.util.List;

import com.oodd.spring.manytomanyselfreference.dto.MemberDto;
import com.oodd.spring.manytomanyselfreference.dto.MemberMemberDto;



public enum MemberInMemoryDB {

	INSTANCE;
	
	private static List<MemberDto> list = new ArrayList<MemberDto>();
	private static Integer lastId = 0;
	
	public Integer getId() {
		return ++lastId;
	}
	
	public void add(MemberDto memberDto) {
		memberDto.setId(getId());
		list.add(memberDto);
	}

	public void edit(MemberDto memberDto) {
		for (MemberDto dto:list) {
			if (dto.getId()==memberDto.getId()) {
				dto.setName(memberDto.getName());
				
				List<MemberMemberDto> memberMemberList = MemberMemberInMemoryDB.INSTANCE.findAll();
				List<MemberMemberDto> modifiedMemberMemberList = new ArrayList<MemberMemberDto>();
				for(MemberMemberDto memberMemberDto : memberMemberList) {
					if(dto.getId() == memberMemberDto.getMemberId1().getId()) {
						memberMemberDto.getMemberId1().setName(memberDto.getName());
					}
					if(dto.getId() == memberMemberDto.getMemberId2().getId()) {
						memberMemberDto.getMemberId2().setName(memberDto.getName());
					}
					modifiedMemberMemberList.add(memberMemberDto);
				}
				MemberMemberInMemoryDB.INSTANCE.setList(modifiedMemberMemberList);
			}
		}
	}
	
	public void remove(Integer id) {
		MemberDto toRemove = null;
		for (MemberDto dto : list) {
			if (dto.getId()==id) {
				toRemove = dto;
			}
		}			
		if (toRemove!=null) {
			list.remove(toRemove);
			List<MemberMemberDto> memberMemberList = MemberMemberInMemoryDB.INSTANCE.findAll();
			List<MemberMemberDto> modifiedMemberMemberList = new ArrayList<MemberMemberDto>();
			for(MemberMemberDto memberMemberDto : memberMemberList) {
				if(toRemove.getId() != memberMemberDto.getMemberId1().getId()
						&& toRemove.getId() != memberMemberDto.getMemberId2().getId()) {
					modifiedMemberMemberList.add(memberMemberDto);
				}
			}
			MemberMemberInMemoryDB.INSTANCE.setList(modifiedMemberMemberList);
		}
	}
	
	public List<MemberDto> findAll() {
		return list;
	}
	
	public MemberDto findById(Integer id) {
		for (MemberDto dto:list) {
			if (dto.getId()==id)
				return dto;
		}
		return null;
	}	
}