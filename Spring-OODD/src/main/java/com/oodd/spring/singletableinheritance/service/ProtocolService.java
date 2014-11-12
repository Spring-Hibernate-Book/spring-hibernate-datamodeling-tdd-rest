package com.oodd.spring.singletableinheritance.service;

import java.util.List;

import com.oodd.spring.singletableinheritance.dto.ProtocolDto;

public interface ProtocolService {
	public void create(ProtocolDto protocolDto);
	public List<ProtocolDto> findAll();
	public ProtocolDto findById(Integer id);
	public void remove(Integer protocolId);
	public void edit(ProtocolDto protocolDto);
}