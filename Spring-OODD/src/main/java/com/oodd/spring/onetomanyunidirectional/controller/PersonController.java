package com.oodd.spring.onetomanyunidirectional.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.oodd.spring.onetomanyunidirectional.dto.PersonDto;
import com.oodd.spring.onetomanyunidirectional.service.PersonService;

@Controller
@RequestMapping(value="/onetomanyunidirectional")
public class PersonController {
	@Autowired
	private PersonService service;
	@RequestMapping(value="/findAll", method=RequestMethod.GET)
	public @ResponseBody List<PersonDto> findAll(){
		return service.findAll();
	}
	@RequestMapping(value="/findById/{personid}", method=RequestMethod.GET)
	public @ResponseBody PersonDto findById(@PathVariable("personid") Integer personid){
		return service.findById(personid);
	}
	@RequestMapping(value="/create", method=RequestMethod.POST)
	@ResponseBody
	public void creat(@RequestBody PersonDto personDto){
		service.create(personDto);
	}
	@RequestMapping(value="/remove/{personid}", method=RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void remove(@PathVariable("personid") Integer personid){
		service.remove(personid);
	}
	@RequestMapping(value="/edit", method=RequestMethod.POST)
	@ResponseBody
	public void edit(@RequestBody PersonDto personDto){
		service.edit(personDto);
	}
}
