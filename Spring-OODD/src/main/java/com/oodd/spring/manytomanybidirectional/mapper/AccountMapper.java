package com.oodd.spring.manytomanybidirectional.mapper;

import org.springframework.stereotype.Component;

import com.oodd.spring.manytomanybidirectional.dto.AccountDto;
import com.oodd.spring.manytomanybidirectional.entity.Account;

@Component
public class AccountMapper {
	public Account mapDtoToEntity(AccountDto accountDto) {
		Account account = new Account();
		if(null != accountDto.getId()) account.setId(accountDto.getId());
		if(null != accountDto.getNumber()) account.setNumber(accountDto.getNumber());
		return account;
	}
	
	public AccountDto mapEntityToDto(Account account) {
		AccountDto AccountDto = new AccountDto();
		if(null != account.getId()) AccountDto.setId(account.getId());
		if(null != account.getNumber()) AccountDto.setNumber(account.getNumber());
		return AccountDto;
	}
}
