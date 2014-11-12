package com.oodd.spring.onetomanyunidirectional.mock;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.oodd.spring.onetomanyunidirectional.dto.PersonDto;
@Controller
@RequestMapping(value="/onetomanyunidirectional/mock")
public class PersonMockController {
	@RequestMapping(value="/findAll", method=RequestMethod.GET)
	public @ResponseBody List<PersonDto> findAll(){
		return PersonInMemoryDB.INSTANCE.findAll();
	}
	@RequestMapping(value="/findById/{personid}", method=RequestMethod.GET)
	public @ResponseBody PersonDto findById(@PathVariable("personid") Integer personid){
		return PersonInMemoryDB.INSTANCE.findById(personid);
	}
	@RequestMapping(value="/create", method=RequestMethod.POST)
	@ResponseBody
	public void create(@RequestBody PersonDto personDto){
		PersonInMemoryDB.INSTANCE.add(personDto);
	}
	@RequestMapping(value="/remove/{personid}", method=RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void remove(@PathVariable("personid") Integer personid){
		PersonInMemoryDB.INSTANCE.remove(personid);
	}
	@RequestMapping(value="/edit", method=RequestMethod.POST)
	@ResponseBody
	public void edit(@RequestBody PersonDto personDto){
		PersonInMemoryDB.INSTANCE.edit(personDto);
	}
}
