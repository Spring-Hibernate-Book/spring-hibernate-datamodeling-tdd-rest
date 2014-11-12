package com.oodd.spring.manytomanybidirectional.service;

import java.util.List;

import com.oodd.spring.manytomanybidirectional.dto.ClientDto;

public interface ClientService {
	public void create(ClientDto clientDto) ;
	public List<ClientDto> findAll();
	public ClientDto findById(Integer id);
	public void remove(Integer id);
	public void edit(ClientDto clientDto);
}
