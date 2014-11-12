package com.oodd.spring.singletableinheritance.controller;

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

import com.oodd.spring.singletableinheritance.dto.ProtocolDto;
import com.oodd.spring.singletableinheritance.service.ProtocolService;

@Controller
@RequestMapping(value="/singletableinheritance")
@Transactional
public class ProtocolController {
	
	@Autowired
	private ProtocolService service ;
	
	@RequestMapping(value="/findAll", method=RequestMethod.GET)
	public @ResponseBody ProtocolDto[] findAll(){
		List<ProtocolDto> list = service.findAll();
		return list.toArray(new ProtocolDto[list.size()]);
	}
	@RequestMapping(value="/findById/{protocolid}", method=RequestMethod.GET)
	public @ResponseBody ProtocolDto findById(@PathVariable("protocolid") Integer protocolid){
		return service.findById(protocolid);
	}
	@RequestMapping(value="/create", method=RequestMethod.POST)
	@ResponseBody
	public void create(@RequestBody ProtocolDto protocol){
		service.create(protocol);
	}
	@RequestMapping(value="/remove/{protocolid}", method=RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void remove(@PathVariable("protocolid") Integer protocolid){
		service.remove(protocolid);
	}
	@RequestMapping(value="/edit", method=RequestMethod.POST)
	@ResponseBody
	public void edit(@RequestBody ProtocolDto protocol){
		service.edit(protocol);
	}
}
