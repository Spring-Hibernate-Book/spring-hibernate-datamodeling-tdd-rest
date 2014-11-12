package com.oodd.spring.manytomanybidirectionalwithjoinattribute.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.oodd.spring.manytomanybidirectionalwithjoinattribute.dto.ManuscriptAuthorDto;
import com.oodd.spring.manytomanybidirectionalwithjoinattribute.service.ManuscriptAuthorService;

@Controller
@RequestMapping(value="/manytomanybidirectionalwithjoinattribute/manuscriptauthor")
@Transactional
public class ManuscriptAuthorController {
	
	@Autowired	
	private ManuscriptAuthorService service;
	
	@RequestMapping(value="/findAll", method=RequestMethod.GET)
	public @ResponseBody List<ManuscriptAuthorDto> findAll(){
		return service.findAll();
	}
	
	@RequestMapping(value="/isPresent", method=RequestMethod.POST)	
	public @ResponseBody boolean isPresent(@RequestBody ManuscriptAuthorDto manuscriptAuthorDto){	
		return service.isPresent(manuscriptAuthorDto);
	}
	
	@RequestMapping(value="/create", method=RequestMethod.POST)
	@ResponseBody
	public void create(@RequestBody ManuscriptAuthorDto manuscriptAuthorDto){	
		service.create(manuscriptAuthorDto);
	}
	
	@RequestMapping(value="/remove", method=RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void remove(@RequestBody ManuscriptAuthorDto manuscriptAuthorDto){
		service.remove(manuscriptAuthorDto);
	}
}
