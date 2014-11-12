package com.oodd.spring.concretetableinheritance.controller;

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

import com.oodd.spring.concretetableinheritance.dto.EstateDto;
import com.oodd.spring.concretetableinheritance.service.EstateService;

@Controller
@RequestMapping(value="/concretetableinheritance")
@Transactional
public class EstateController {
	
	@Autowired
	private EstateService service;
	
	@RequestMapping(value="/findAll", method=RequestMethod.GET)
	public @ResponseBody EstateDto[] findAll(){
		List<EstateDto> list = service.findAll();
		return list.toArray(new EstateDto[list.size()]);
	}
	@RequestMapping(value="/findById/{estateid}", method=RequestMethod.GET)
	public @ResponseBody EstateDto findById(@PathVariable("estateid") Integer estateid){
		return service.findById(estateid);
	}
	@RequestMapping(value="/create", method=RequestMethod.POST)
	@ResponseBody
	public void create(@RequestBody EstateDto estate){
		service.create(estate);
	}
	@RequestMapping(value="/remove/{estateid}", method=RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void remove(@PathVariable("estateid") Integer estateid){
		service.remove(estateid);
	}
	@RequestMapping(value="/edit", method=RequestMethod.POST)
	@ResponseBody
	public void edit(@RequestBody EstateDto estate){
		service.edit(estate);
	}
}
