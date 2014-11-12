package com.oodd.spring.manytomanybidirectional.mock;

import java.util.ArrayList;
import java.util.List;

import com.oodd.spring.manytomanybidirectional.dto.ClientAccountDto;

public enum ClientAccountInMemoryDB {

	INSTANCE;
	
	private static List<ClientAccountDto> list = new ArrayList<ClientAccountDto>();
	
	public void add(ClientAccountDto clientAccountDto) {
		list.add(clientAccountDto);
	}

	public boolean isPresent(ClientAccountDto clientAccountDto) {
		for (ClientAccountDto dto:list) {
			if (dto.getClientDto().getId() == clientAccountDto.getClientDto().getId() 
					&& dto.getAccountDto().getId() == clientAccountDto.getAccountDto().getId()) {
				return true;
			}				
		}
		return false;
	}
	
	public void remove(ClientAccountDto clientAccountDto) {
		ClientAccountDto toRemove = null;
		for (ClientAccountDto dto:list) {
			if (dto.getClientDto().getId()==clientAccountDto.getClientDto().getId()
					&& dto.getAccountDto().getId()==clientAccountDto.getAccountDto().getId()) {
				toRemove = dto;
			}
		}			
		if (toRemove!=null) list.remove(toRemove);
	}
	
	public List<ClientAccountDto> findAll() {
		return list;
	}
	public void setList(List<ClientAccountDto> list) {
		ClientAccountInMemoryDB.list = list;
	}
}