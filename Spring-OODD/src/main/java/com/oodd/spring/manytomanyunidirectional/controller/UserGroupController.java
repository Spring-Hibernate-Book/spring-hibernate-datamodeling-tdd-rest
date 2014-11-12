package com.oodd.spring.manytomanyunidirectional.controller;

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

import com.oodd.spring.manytomanyunidirectional.dto.UserGroupDto;
import com.oodd.spring.manytomanyunidirectional.service.UserGroupService;

@Controller
@RequestMapping(value="/manytomanyunidirectional/usergroup")
@Transactional
public class UserGroupController {

	@Autowired	
	private UserGroupService service;
	
	@RequestMapping(value="/findAll", method=RequestMethod.GET)
	public @ResponseBody List<UserGroupDto> findAll(){
		return service.findAll();
	}
	
	@RequestMapping(value="/isPresent", method=RequestMethod.POST)	
	public @ResponseBody boolean isPresent(@RequestBody UserGroupDto userGroupDto){	
		return service.isPresent(userGroupDto);
	}
	
	@RequestMapping(value="/create", method=RequestMethod.POST)
	@ResponseBody
	public void create(@RequestBody UserGroupDto userGroupDto){	
		service.create(userGroupDto);
	}
	
	@RequestMapping(value="/remove", method=RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void remove(@RequestBody UserGroupDto userGroupDto){
		service.remove(userGroupDto);
	}
}
