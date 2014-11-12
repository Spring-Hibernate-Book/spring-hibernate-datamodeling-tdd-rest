package com.oodd.spring.manytomanybidirectionalwithjoinattribute.controller;

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

import com.oodd.spring.manytomanybidirectionalwithjoinattribute.dto.ManuscriptDto;
import com.oodd.spring.manytomanybidirectionalwithjoinattribute.service.ManuscriptService;

@Controller
@RequestMapping(value="/manytomanybidirectionalwithjoinattribute/manuscript")
@Transactional
public class ManuscriptController {

	@Autowired
	private ManuscriptService service;
	
	@RequestMapping(value="/findAll", method=RequestMethod.GET)
	public @ResponseBody List<ManuscriptDto> findAll(){
		return service.findAll();
	}
	
	@RequestMapping(value="/findById/{manuscriptId}", method=RequestMethod.GET)
	public @ResponseBody ManuscriptDto findById(@PathVariable("manuscriptId") Integer manuscriptId){
		return service.findById(manuscriptId);
	}
	
	@RequestMapping(value="/create", method=RequestMethod.POST)
	@ResponseBody
	public void create(@RequestBody ManuscriptDto clientDto){
		service.create(clientDto);
	}
	
	@RequestMapping(value="/remove/{manuscriptId}", method=RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void remove(@PathVariable("manuscriptId") Integer manuscriptId){
		service.remove(manuscriptId);
	}
	
	@RequestMapping(value="/edit", method=RequestMethod.POST)
	@ResponseBody
	public void edit(@RequestBody ManuscriptDto manuscriptDto){
		service.edit(manuscriptDto);
	}
}