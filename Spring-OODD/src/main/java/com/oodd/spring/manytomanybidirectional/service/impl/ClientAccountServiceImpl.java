package com.oodd.spring.manytomanybidirectional.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oodd.spring.manytomanybidirectional.dao.AccountDao;
import com.oodd.spring.manytomanybidirectional.dao.ClientAccountDao;
import com.oodd.spring.manytomanybidirectional.dao.ClientDao;
import com.oodd.spring.manytomanybidirectional.dto.AccountDto;
import com.oodd.spring.manytomanybidirectional.dto.ClientAccountDto;
import com.oodd.spring.manytomanybidirectional.dto.ClientDto;
import com.oodd.spring.manytomanybidirectional.entity.Account;
import com.oodd.spring.manytomanybidirectional.entity.Client;
import com.oodd.spring.manytomanybidirectional.mapper.AccountMapper;
import com.oodd.spring.manytomanybidirectional.mapper.ClientMapper;
import com.oodd.spring.manytomanybidirectional.service.ClientAccountService;

@Service
@Transactional
public class ClientAccountServiceImpl implements ClientAccountService {

	@Autowired
	private ClientDao clientDao;
	
	@Autowired
	private ClientMapper clientMapper;
	
	@Autowired
	private AccountDao accountDao;
	
	@Autowired
	private AccountMapper accountMapper;
	
	@Autowired
	private ClientAccountDao clientAccountDao;

	@Override
	public boolean isPresent(ClientAccountDto clientAccountDto) {
		boolean status = false;
		List<Client> clientList = clientAccountDao.isPresent(clientAccountDto.getClientDto().getId(), clientAccountDto.getAccountDto().getId());
		if(null != clientList) {
			if(clientList.size() > 0) {
				status = true;
			}
		}
		return status;
	}

	@Override
	public List<ClientAccountDto> findAll() {
		List<ClientAccountDto> clientAccountDtos = new ArrayList<ClientAccountDto>();
		List<Client> clientList = clientAccountDao.getAll();
		for(Client client : clientList) {			
			ClientDto clientDto = clientMapper.mapEntityToDto(client);			
			Set<Account> accounts = client.getAccounts();
			for(Account account : accounts) {
				ClientAccountDto clientAccountDto = new ClientAccountDto();
				clientAccountDto.setClientDto(clientDto);
				AccountDto accountDto = accountMapper.mapEntityToDto(account);
				clientAccountDto.setAccountDto(accountDto);
				clientAccountDtos.add(clientAccountDto);
			}			
		}
		return clientAccountDtos;
	}

	@Override
	public void create(ClientAccountDto clientAccountDto) {
		Integer clientid = clientAccountDto.getClientDto().getId();
		Integer accountid = clientAccountDto.getAccountDto().getId();
		
		Client client = clientDao.getById(clientid);
		Account account = accountDao.getById(accountid);
		client.getAccounts().add(account);
		
		clientDao.insert(client);
	}

	@Override
	public void remove(ClientAccountDto clientAccountDto) {
		Integer clientid = clientAccountDto.getClientDto().getId();
		Integer accountid = clientAccountDto.getAccountDto().getId();
		
		Client client = clientDao.getById(clientid);
		Account account = accountDao.getById(accountid);
		client.getAccounts().remove(account);
		
		clientDao.update(client);
	}
}