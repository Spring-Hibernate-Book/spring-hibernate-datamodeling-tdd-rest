package com.oodd.spring.manytomanybidirectional.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.oodd.spring.manytomanybidirectional.dto.AccountDto;
import com.oodd.spring.manytomanybidirectional.service.AccountService;

@Controller
@RequestMapping(value="/manytomanybidirectional/account")
@Transactional
public class AccountController {

	@Autowired
	private AccountService service;
	
	@RequestMapping(value="/findAll", method=RequestMethod.GET)
	public @ResponseBody List<AccountDto> findAll(){
		return service.findAll();
	}
	
	@RequestMapping(value="/findById/{accountid}", method=RequestMethod.GET)
	public @ResponseBody AccountDto findById(@PathVariable("accountid") Integer accountid){
		return service.findById(accountid);
	}
	
	@RequestMapping(value="/create", method=RequestMethod.POST)
	@ResponseBody
	public void create(@RequestBody AccountDto accountDto){
		service.create(accountDto);
	}
	
	@RequestMapping(value="/remove/{accountid}", method=RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void remove(@PathVariable("accountid") Integer accountid){
		service.remove(accountid);
	}
	
	@RequestMapping(value="/edit", method=RequestMethod.POST)
	@ResponseBody
	public void edit(@RequestBody AccountDto accountDto){
		service.edit(accountDto);
	}
}
