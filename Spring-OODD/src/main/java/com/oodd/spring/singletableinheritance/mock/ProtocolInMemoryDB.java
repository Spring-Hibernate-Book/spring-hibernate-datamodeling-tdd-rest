package com.oodd.spring.singletableinheritance.mock;

import java.util.ArrayList;
import java.util.List;

import com.oodd.spring.singletableinheritance.dto.ProtocolDto;

public enum ProtocolInMemoryDB {

	INSTANCE;
	
	private static List<ProtocolDto> list = new ArrayList<ProtocolDto>();
	private static Integer lastId = 0;
	
	public Integer getId() {
		return ++lastId;
	}
	
	public void add(ProtocolDto protocolDto) {
		protocolDto.setId(getId());
		list.add(protocolDto);
	}

	public void edit(ProtocolDto protocolDto) {
		for (ProtocolDto dto:list) {
			if (dto.getId()==protocolDto.getId())
				dto.setName(protocolDto.getName());
		}
	}
	
	public void remove(Integer id) {
		ProtocolDto toRemove = null;
		for (ProtocolDto dto:list) 
			if (dto.getId()==id) toRemove = dto;
		if (toRemove!=null) list.remove(toRemove);
	}
	
	public List<ProtocolDto> findAll() {
		return list;
	}
	
	public ProtocolDto findById(Integer id) {
		for (ProtocolDto dto:list) {
			if (dto.getId()==id)
				return dto;
		}
		return null;
	}
}
