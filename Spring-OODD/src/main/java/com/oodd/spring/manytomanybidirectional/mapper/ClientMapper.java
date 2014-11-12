package com.oodd.spring.manytomanybidirectional.mapper;

import org.springframework.stereotype.Component;

import com.oodd.spring.manytomanybidirectional.dto.ClientDto;
import com.oodd.spring.manytomanybidirectional.entity.Client;

@Component
public class ClientMapper {

	public Client mapDtoToEntity(ClientDto clientDto) {
		Client client = new Client();
		if(null != clientDto.getId()) client.setId(clientDto.getId());
		if(null != clientDto.getName()) client.setName(clientDto.getName());
		return client;
	}
	
	public ClientDto mapEntityToDto(Client client) {
		ClientDto clientDto = new ClientDto();
		if(null != client.getId()) clientDto.setId(client.getId());
		if(null != client.getName()) clientDto.setName(client.getName());
		return clientDto;
	}
}
