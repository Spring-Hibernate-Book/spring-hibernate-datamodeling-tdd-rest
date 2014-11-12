package com.oodd.spring.manytomanybidirectional.service;

import java.util.List;

import com.oodd.spring.manytomanybidirectional.dto.ClientAccountDto;

public interface ClientAccountService {
	public boolean isPresent(ClientAccountDto clientAccountDto);
	public List<ClientAccountDto> findAll();
	public void create(ClientAccountDto clientAccountDto);
	public void remove(ClientAccountDto clientAccountDto);
}
