package com.oodd.spring.manytomanybidirectional.controller;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.setup.StandaloneMockMvcBuilder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.oodd.spring.manytomanybidirectional.dto.AccountDto;
import com.oodd.spring.manytomanybidirectional.dto.ClientAccountDto;
import com.oodd.spring.manytomanybidirectional.dto.ClientDto;
import com.oodd.spring.manytomanybidirectional.service.AccountService;
import com.oodd.spring.manytomanybidirectional.service.ClientService;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration( locations = { "classpath:context.xml" } )
@TransactionConfiguration(defaultRollback = true)
@Transactional
public class ClientAccountControllerTest {
private Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd hh:mm:ss").create();

	@Autowired
	private ClientService clientService;
	
	@Autowired
	private AccountService accountService;
	
	@Resource
	private WebApplicationContext webApplicationContext;

	private MockMvc mockMvc;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.<StandaloneMockMvcBuilder>webAppContextSetup(webApplicationContext).build();
	}
	
	@Test
	public void testAll() throws Exception {
		testCreate();
		testPresent();
		testDelete();
	}
	
	public void testCreate() throws Exception {
		ClientAccountDto clientAccountDto = new ClientAccountDto();
		
		ClientDto clientDto = new ClientDto();
		clientDto.setName("Sara Tencradi");
		clientService.create(clientDto);
		
		AccountDto accountDto = new AccountDto();
		accountDto.setNumber("Savings Account");
		accountService.create(accountDto);
		
		List<ClientDto> clientDtos = clientService.findAll();
		ClientDto clientDto2 = clientDtos.get(0);
		
		List<AccountDto> accountDtos = accountService.findAll();
		AccountDto accountDto2 = accountDtos.get(0);
		
		clientAccountDto.setClientDto(clientDto2);
		clientAccountDto.setAccountDto(accountDto2);
				
		String json = gson.toJson(clientAccountDto);
		
		MockHttpServletRequestBuilder requestBuilderOne = MockMvcRequestBuilders.post("/manytomanybidirectional/clientaccount/create");
		requestBuilderOne.contentType(MediaType.APPLICATION_JSON);
		requestBuilderOne.content(json.getBytes());
		this.mockMvc.perform(requestBuilderOne).andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	public void testPresent() throws Exception {
		ClientAccountDto clientAccountDto = new ClientAccountDto();
		
		List<ClientDto> clientDtos = clientService.findAll();
		ClientDto clientDto = clientDtos.get(0);
		
		List<AccountDto> accountDtos = accountService.findAll();
		AccountDto accountDto = accountDtos.get(0);
		
		clientAccountDto.setClientDto(clientDto);
		clientAccountDto.setAccountDto(accountDto);
		
		String json = gson.toJson(clientAccountDto);
		
		MockHttpServletRequestBuilder requestBuilderOne = MockMvcRequestBuilders.post("/manytomanybidirectional/clientaccount/isPresent");
		requestBuilderOne.contentType(MediaType.APPLICATION_JSON);
		requestBuilderOne.content(json.getBytes());
		this.mockMvc.perform(requestBuilderOne).andExpect(MockMvcResultMatchers.status().isOk());
	}	
	
	public void testDelete() throws Exception {			
		ClientAccountDto clientAccountDto = new ClientAccountDto();
		
		List<ClientDto> clientDtos = clientService.findAll();
		ClientDto clientDto = clientDtos.get(0);
		
		List<AccountDto> accountDtos = accountService.findAll();
		AccountDto accountDto = accountDtos.get(0);
		
		clientAccountDto.setClientDto(clientDto);
		clientAccountDto.setAccountDto(accountDto);
		
		String json = gson.toJson(clientAccountDto);
		
		MockHttpServletRequestBuilder requestBuilder2 = MockMvcRequestBuilders.post("/manytomanybidirectional/clientaccount/remove");
		requestBuilder2.contentType(MediaType.APPLICATION_JSON);
		requestBuilder2.content(json.getBytes());
		this.mockMvc.perform(requestBuilder2).andExpect(MockMvcResultMatchers.status().is(204));
	}
}
