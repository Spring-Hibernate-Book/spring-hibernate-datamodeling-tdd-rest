package com.oodd.spring.manytomanyunidirectional.controller;

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

import com.oodd.spring.manytomanyunidirectional.dto.UserDto;
import com.oodd.spring.manytomanyunidirectional.service.UserService;

@Controller
@RequestMapping(value="/manytomanyunidirectional/user")
@Transactional
public class UserController {

	@Autowired
	private UserService service;
	
	@RequestMapping(value="/findAll", method=RequestMethod.GET)
	public @ResponseBody List<UserDto> findAll(){
		return service.findAll();
	}
	
	@RequestMapping(value="/findById/{userid}", method=RequestMethod.GET)
	public @ResponseBody UserDto findById(@PathVariable("userid") Integer userid){
		return service.findById(userid);
	}
	
	@RequestMapping(value="/create", method=RequestMethod.POST)
	@ResponseBody
	public void create(@RequestBody UserDto user){
		service.create(user);
	}
	
	@RequestMapping(value="/remove/{userid}", method=RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void remove(@PathVariable("userid") Integer userid){
		service.remove(userid);
	}
	
	@RequestMapping(value="/edit", method=RequestMethod.POST)
	@ResponseBody
	public void edit(@RequestBody UserDto userDto){
		service.edit(userDto);
	}
}
