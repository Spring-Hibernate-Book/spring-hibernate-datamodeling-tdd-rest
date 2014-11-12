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

import com.oodd.spring.manytomanybidirectionalwithjoinattribute.dto.AuthorDto;
import com.oodd.spring.manytomanybidirectionalwithjoinattribute.service.AuthorService;

@Controller
@RequestMapping(value="/manytomanybidirectionalwithjoinattribute/author")
@Transactional
public class AuthorController {

	@Autowired
	private AuthorService service;
	
	@RequestMapping(value="/findAll", method=RequestMethod.GET)
	public @ResponseBody List<AuthorDto> findAll(){
		return service.findAll();
	}
	
	@RequestMapping(value="/findById/{authorId}", method=RequestMethod.GET)
	public @ResponseBody AuthorDto findById(@PathVariable("authorId") Integer authorId){
		return service.findById(authorId);
	}
	
	@RequestMapping(value="/create", method=RequestMethod.POST)
	@ResponseBody
	public void create(@RequestBody AuthorDto authorDto){
		service.create(authorDto);
	}
	
	@RequestMapping(value="/remove/{authorId}", method=RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void remove(@PathVariable("authorId") Integer authorId){
		service.remove(authorId);
	}
	
	@RequestMapping(value="/edit", method=RequestMethod.POST)
	@ResponseBody
	public void edit(@RequestBody AuthorDto authorDto){
		service.edit(authorDto);
	}
}
