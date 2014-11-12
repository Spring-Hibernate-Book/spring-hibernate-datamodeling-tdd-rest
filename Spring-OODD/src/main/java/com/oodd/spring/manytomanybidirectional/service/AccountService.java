package com.oodd.spring.manytomanybidirectional.service;

import java.util.List;

import com.oodd.spring.manytomanybidirectional.dto.AccountDto;

public interface AccountService {
	public void create(AccountDto accountDto) ;
	public List<AccountDto> findAll();
	public AccountDto findById(Integer id);
	public void remove(Integer id);
	public void edit(AccountDto accountDto);
}
