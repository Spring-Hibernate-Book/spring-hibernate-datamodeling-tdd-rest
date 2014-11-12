package com.oodd.spring.manytomanybidirectional.mock;

import java.util.ArrayList;
import java.util.List;

import com.oodd.spring.manytomanybidirectional.dto.ClientAccountDto;
import com.oodd.spring.manytomanybidirectional.dto.ClientDto;



public enum ClientInMemoryDB {

	INSTANCE;
	
	private static List<ClientDto> list = new ArrayList<ClientDto>();
	private static Integer lastId = 0;
	
	public Integer getId() {
		return ++lastId;
	}
	
	public void add(ClientDto clientDto) {
		clientDto.setId(getId());
		list.add(clientDto);
	}

	public void edit(ClientDto clientDto) {
		for (ClientDto dto:list) {
			if (dto.getId()==clientDto.getId()) {
				dto.setName(clientDto.getName());
				
				List<ClientAccountDto> clientAccountList = ClientAccountInMemoryDB.INSTANCE.findAll();
				List<ClientAccountDto> modifiedClientAccountList = new ArrayList<ClientAccountDto>();
				for(ClientAccountDto clientAccountDto : clientAccountList) {
					if(dto.getId() == clientAccountDto.getClientDto().getId()) {
						clientAccountDto.getClientDto().setName(clientDto.getName());
					}
					modifiedClientAccountList.add(clientAccountDto);
				}
				ClientAccountInMemoryDB.INSTANCE.setList(modifiedClientAccountList);
			}
		}
	}
	
	public void remove(Integer id) {
		ClientDto toRemove = null;
		for (ClientDto dto : list) {
			if (dto.getId()==id) {
				toRemove = dto;
			}
		}			
		if (toRemove!=null) {
			list.remove(toRemove);
			List<ClientAccountDto> clientAccountList = ClientAccountInMemoryDB.INSTANCE.findAll();
			List<ClientAccountDto> modifiedClientAccountList = new ArrayList<ClientAccountDto>();
			for(ClientAccountDto clientAccountDto : clientAccountList) {
				if(toRemove.getId() != clientAccountDto.getClientDto().getId()) {
					modifiedClientAccountList.add(clientAccountDto);
				}
			}
			ClientAccountInMemoryDB.INSTANCE.setList(modifiedClientAccountList);
		}
	}
	
	public List<ClientDto> findAll() {
		return list;
	}
	
	public ClientDto findById(Integer id) {
		for (ClientDto dto:list) {
			if (dto.getId()==id)
				return dto;
		}
		return null;
	}	
}