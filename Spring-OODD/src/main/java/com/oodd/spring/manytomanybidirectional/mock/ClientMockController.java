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

import com.oodd.spring.manytomanybidirectional.dto.ClientDto;


@Controller
@RequestMapping(value="/manytomanybidirectional/client/mock")
public class ClientMockController {
	
	@RequestMapping(value="/findAll", method=RequestMethod.GET)
	public @ResponseBody List<ClientDto> findAll(){
		return ClientInMemoryDB.INSTANCE.findAll();
	}
	@RequestMapping(value="/findById/{clientid}", method=RequestMethod.GET)
	public @ResponseBody ClientDto findById(@PathVariable("clientid") Integer clientid){
		return ClientInMemoryDB.INSTANCE.findById(clientid);
	}
	@RequestMapping(value="/create", method=RequestMethod.POST)
	@ResponseBody
	public void create(@RequestBody ClientDto client){
		ClientInMemoryDB.INSTANCE.add(client);
	}
	@RequestMapping(value="/remove/{clientid}", method=RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void remove(@PathVariable("clientid") Integer clientid){
		ClientInMemoryDB.INSTANCE.remove(clientid);
	}
	@RequestMapping(value="/edit", method=RequestMethod.POST)
	@ResponseBody
	public void edit(@RequestBody ClientDto client){
		ClientInMemoryDB.INSTANCE.edit(client);
	}
}
