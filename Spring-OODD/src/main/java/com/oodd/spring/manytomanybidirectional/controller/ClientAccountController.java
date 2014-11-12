package com.oodd.spring.manytomanybidirectional.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.oodd.spring.manytomanybidirectional.dto.ClientAccountDto;
import com.oodd.spring.manytomanybidirectional.service.ClientAccountService;

@Controller
@RequestMapping(value="/manytomanybidirectional/clientaccount")
@Transactional
public class ClientAccountController {
	
	@Autowired	
	private ClientAccountService service;
	
	@RequestMapping(value="/findAll", method=RequestMethod.GET)
	public @ResponseBody List<ClientAccountDto> findAll(){
		return service.findAll();
	}
	
	@RequestMapping(value="/isPresent", method=RequestMethod.POST)	
	public @ResponseBody boolean isPresent(@RequestBody ClientAccountDto clientAccountDto){	
		return service.isPresent(clientAccountDto);
	}
	
	@RequestMapping(value="/create", method=RequestMethod.POST)
	@ResponseBody
	public void create(@RequestBody ClientAccountDto clientAccountDto){	
		service.create(clientAccountDto);
	}
	
	@RequestMapping(value="/remove", method=RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void remove(@RequestBody ClientAccountDto clientAccountDto){
		service.remove(clientAccountDto);
	}
}
