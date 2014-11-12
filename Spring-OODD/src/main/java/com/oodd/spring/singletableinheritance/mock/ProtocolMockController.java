package com.oodd.spring.singletableinheritance.mock;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.oodd.spring.singletableinheritance.dto.ProtocolDto;

@Controller
@RequestMapping(value="/singletableinheritance/mock")
public class ProtocolMockController {
	
	@RequestMapping(value="/findAll", method=RequestMethod.GET)
	public @ResponseBody ProtocolDto[] findAll(){
		List<ProtocolDto> list = ProtocolInMemoryDB.INSTANCE.findAll();
		return list.toArray(new ProtocolDto[list.size()]);
	}
	@RequestMapping(value="/findById/{protocolid}", method=RequestMethod.GET)
	public @ResponseBody ProtocolDto findById(@PathVariable("protocolid") Integer protocolid){
		return ProtocolInMemoryDB.INSTANCE.findById(protocolid);
	}
	@RequestMapping(value="/create", method=RequestMethod.POST)
	@ResponseBody
	public void create(@RequestBody ProtocolDto protocol){
		ProtocolInMemoryDB.INSTANCE.add(protocol);
	}
	@RequestMapping(value="/remove/{protocolid}", method=RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void remove(@PathVariable("protocolid") Integer protocolid){
		ProtocolInMemoryDB.INSTANCE.remove(protocolid);
	}
	@RequestMapping(value="/edit", method=RequestMethod.POST)
	@ResponseBody
	public void edit(@RequestBody ProtocolDto protocol){
		ProtocolInMemoryDB.INSTANCE.edit(protocol);
	}
}
