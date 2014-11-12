package com.oodd.spring.manytomanybidirectional.dto;


public class ClientAccountDto {

	private ClientDto clientDto;
	private AccountDto accountDto;
	
	public ClientDto getClientDto() {
		return clientDto;
	}
	public void setClientDto(ClientDto clientDto) {
		this.clientDto = clientDto;
	}
	public AccountDto getAccountDto() {
		return accountDto;
	}
	public void setAccountDto(AccountDto accountDto) {
		this.accountDto = accountDto;
	}
}