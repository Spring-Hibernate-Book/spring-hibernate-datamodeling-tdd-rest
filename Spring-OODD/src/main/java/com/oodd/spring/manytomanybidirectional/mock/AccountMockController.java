package com.oodd.spring.manytomanybidirectional.mock;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.oodd.spring.manytomanybidirectional.dto.AccountDto;


@Controller
@RequestMapping(value="/manytomanybidirectional/account/mock")
public class AccountMockController {
	
	@RequestMapping(value="/findAll", method=RequestMethod.GET)
	public @ResponseBody List<AccountDto> findAll(){
		return AccountInMemoryDB.INSTANCE.findAll();
	}
	@RequestMapping(value="/findById/{accountid}", method=RequestMethod.GET)
	public @ResponseBody AccountDto findById(@PathVariable("accountid") Integer accountid){
		return AccountInMemoryDB.INSTANCE.findById(accountid);
	}
	@RequestMapping(value="/create", method=RequestMethod.POST)
	@ResponseBody
	public void create(@RequestBody AccountDto account){
		AccountInMemoryDB.INSTANCE.add(account);
	}
	@RequestMapping(value="/remove/{accountid}", method=RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void remove(@PathVariable("accountid") Integer accountid){
		AccountInMemoryDB.INSTANCE.remove(accountid);
	}
	@RequestMapping(value="/edit", method=RequestMethod.POST)
	@ResponseBody
	public void edit(@RequestBody AccountDto account){
		AccountInMemoryDB.INSTANCE.edit(account);
	}
}
