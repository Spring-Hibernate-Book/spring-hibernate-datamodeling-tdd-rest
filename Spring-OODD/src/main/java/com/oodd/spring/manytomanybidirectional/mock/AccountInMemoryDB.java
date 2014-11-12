package com.oodd.spring.manytomanybidirectional.mock;

import java.util.ArrayList;
import java.util.List;

import com.oodd.spring.manytomanybidirectional.dto.AccountDto;
import com.oodd.spring.manytomanybidirectional.dto.ClientAccountDto;



public enum AccountInMemoryDB {

	INSTANCE;
	
	private static List<AccountDto> list = new ArrayList<AccountDto>();
	private static Integer lastId = 0;
	
	public Integer getId() {
		return ++lastId;
	}
	
	public void add(AccountDto accountDto) {
		accountDto.setId(getId());
		list.add(accountDto);
	}

	public void edit(AccountDto accountDto) {
		for (AccountDto dto:list) {
			if (dto.getId()==accountDto.getId()) {
				dto.setNumber(accountDto.getNumber());
				
				List<ClientAccountDto> clientAccountList = ClientAccountInMemoryDB.INSTANCE.findAll();
				List<ClientAccountDto> modifiedClientAccountList = new ArrayList<ClientAccountDto>();
				for(ClientAccountDto clientAccountDto : clientAccountList) {
					if(dto.getId() == clientAccountDto.getAccountDto().getId()) {
						clientAccountDto.getAccountDto().setNumber(accountDto.getNumber());
					}
					modifiedClientAccountList.add(clientAccountDto);
				}
				ClientAccountInMemoryDB.INSTANCE.setList(modifiedClientAccountList);
			}
		}
	}
	
	public void remove(Integer id) {
		AccountDto toRemove = null;
		for (AccountDto dto:list) {
			if (dto.getId()==id) toRemove = dto;
		}
		if (toRemove!=null) {
			list.remove(toRemove);
		}
		if (toRemove!=null) {
			list.remove(toRemove);
			List<ClientAccountDto> clientAccountList = ClientAccountInMemoryDB.INSTANCE.findAll();
			List<ClientAccountDto> modifiedClientAccountList = new ArrayList<ClientAccountDto>();
			for(ClientAccountDto clientAccountDto : clientAccountList) {
				if(toRemove.getId() != clientAccountDto.getAccountDto().getId()) {
					modifiedClientAccountList.add(clientAccountDto);
				}
			}
			ClientAccountInMemoryDB.INSTANCE.setList(modifiedClientAccountList);
		}
	}
	
	public List<AccountDto> findAll() {
		return list;
	}
	
	public AccountDto findById(Integer id) {
		for (AccountDto dto:list) {
			if (dto.getId()==id)
				return dto;
		}
		return null;
	}
}
