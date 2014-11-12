package com.oodd.spring.manytomanybidirectional.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.oodd.spring.manytomanybidirectional.dto.AccountDto;
import com.oodd.spring.manytomanybidirectional.dto.ClientAccountDto;
import com.oodd.spring.manytomanybidirectional.dto.ClientDto;

@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration( locations = { "classpath:context.xml" } )
@TransactionConfiguration( defaultRollback = true )
@Transactional
public class ClientAccountServiceImplTest {

	@Autowired
	private ClientService clientService;
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private ClientAccountService clientAccountService;
	
	@Test
	public void testFindAll() {
		Assert.assertEquals(0L, clientAccountService.findAll().size());
	}
	
	@Test
	public void testCreate() {
		ClientAccountDto clientAccountDto = new ClientAccountDto();
		
		ClientDto clientDto = new ClientDto();
		clientDto.setName("Sara Tencradi");
		clientService.create(clientDto);
		
		AccountDto accountDto = new AccountDto();
		accountDto.setNumber("Savings Account");
		accountService.create(accountDto);
		
		List<ClientDto> clientDtos = clientService.findAll();
		ClientDto clientDto1 = clientDtos.get(0);
		
		List<AccountDto> accountDtos = accountService.findAll();
		AccountDto accountDto1 = accountDtos.get(0);
		
		clientAccountDto.setClientDto(clientDto1);
		clientAccountDto.setAccountDto(accountDto1);		
		
		clientAccountService.create(clientAccountDto);	
		Assert.assertEquals(1L, clientAccountService.findAll().size());
	}
	
	@Test
	public void testIsPresent() {	
		ClientAccountDto clientAccountDto = new ClientAccountDto();
		
		ClientDto clientDto = new ClientDto();
		clientDto.setName("Sara Tencradi");
		clientService.create(clientDto);
		
		AccountDto accountDto = new AccountDto();
		accountDto.setNumber("Savings Account");
		accountService.create(accountDto);
		
		List<ClientDto> clientDtos = clientService.findAll();
		ClientDto clientDto1 = clientDtos.get(0);
		
		List<AccountDto> accountDtos = accountService.findAll();
		AccountDto accountDto1 = accountDtos.get(0);
		
		clientAccountDto.setClientDto(clientDto1);
		clientAccountDto.setAccountDto(accountDto1);		
		
		clientAccountService.create(clientAccountDto);	
		Assert.assertEquals(1L, clientAccountService.findAll().size());		
		
		boolean status = clientAccountService.isPresent(clientAccountDto);		
		Assert.assertTrue(status);
	}
	
	@Test
	public void testRemove() {	
		ClientAccountDto clientAccountDto = new ClientAccountDto();
		
		ClientDto clientDto = new ClientDto();
		clientDto.setName("Sara Tencradi");
		clientService.create(clientDto);
		
		AccountDto accountDto = new AccountDto();
		accountDto.setNumber("Savings Account");
		accountService.create(accountDto);
		
		List<ClientDto> clientDtos = clientService.findAll();
		ClientDto clientDto1 = clientDtos.get(0);
		
		List<AccountDto> accountDtos = accountService.findAll();
		AccountDto accountDto1 = accountDtos.get(0);
		
		clientAccountDto.setClientDto(clientDto1);
		clientAccountDto.setAccountDto(accountDto1);		
		
		clientAccountService.create(clientAccountDto);	
		Assert.assertEquals(1L, clientAccountService.findAll().size());
		
		List<ClientAccountDto> clientAccountList = clientAccountService.findAll();
		ClientAccountDto clientAccountDto1 = clientAccountList.get(0);
		clientAccountService.remove(clientAccountDto1);
		
		Assert.assertEquals(0, clientAccountService.findAll().size());
	}
}
