package com.oodd.spring.manytomanybidirectional.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oodd.spring.manytomanybidirectional.dao.ClientDao;
import com.oodd.spring.manytomanybidirectional.dto.ClientDto;
import com.oodd.spring.manytomanybidirectional.entity.Client;
import com.oodd.spring.manytomanybidirectional.mapper.ClientMapper;
import com.oodd.spring.manytomanybidirectional.service.ClientService;

@Service
@Transactional
public class ClientServiceImpl implements ClientService {

	@Autowired
	private ClientDao clientDao;
	
	@Autowired
	private ClientMapper clientMapper;
	
	@Override
	public void create(ClientDto clientDto) {
		clientDao.insert(clientMapper.mapDtoToEntity(clientDto));
	}

	@Override
	public List<ClientDto> findAll() {
		List<Client> clients = clientDao.getAll();
		List<ClientDto> clientDtos = new ArrayList<ClientDto>();
		for(Client client : clients) {
			clientDtos.add(clientMapper.mapEntityToDto(client));
		}
		return clientDtos;
	}

	@Override
	public ClientDto findById(Integer id) {
		Client client = clientDao.getById(id);
		if(null != client) {
			return clientMapper.mapEntityToDto(client);
		}
		return null;
	}

	@Override
	public void remove(Integer id) {
		Client client = clientDao.getById(id);
		clientDao.delete(client);
	}

	@Override
	public void edit(ClientDto clientDto) {
		Client clientCurrent = clientDao.getById(clientDto.getId());
		Client clientToBeEdited = clientMapper.mapDtoToEntity(clientDto);
		clientToBeEdited.setAccounts(clientCurrent.getAccounts());
		clientDao.update(clientToBeEdited);
	}
}