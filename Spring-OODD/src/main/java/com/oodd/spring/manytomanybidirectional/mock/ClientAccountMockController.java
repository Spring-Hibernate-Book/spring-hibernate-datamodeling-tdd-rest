package com.oodd.spring.manytomanybidirectional.mock;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.oodd.spring.manytomanybidirectional.dto.ClientAccountDto;

@Controller
@RequestMapping(value="/manytomanybidirectional/clientaccount/mock")
public class ClientAccountMockController {
	
	@RequestMapping(value="/findAll", method=RequestMethod.GET)
	public @ResponseBody List<ClientAccountDto> findAll(){
		return ClientAccountInMemoryDB.INSTANCE.findAll();
	}
	@RequestMapping(value="/create", method=RequestMethod.POST)
	@ResponseBody
	public void create(@RequestBody ClientAccountDto clientAccountDto){
		ClientAccountInMemoryDB.INSTANCE.add(clientAccountDto);
	}
	@RequestMapping(value="/remove", method=RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void remove(@RequestBody ClientAccountDto clientAccountDto){
		ClientAccountInMemoryDB.INSTANCE.remove(clientAccountDto);
	}
	@RequestMapping(value="/isPresent", method=RequestMethod.POST)	
	public @ResponseBody boolean isPresent(@RequestBody ClientAccountDto clientAccountDto){	
		return ClientAccountInMemoryDB.INSTANCE.isPresent(clientAccountDto);
	}
}
