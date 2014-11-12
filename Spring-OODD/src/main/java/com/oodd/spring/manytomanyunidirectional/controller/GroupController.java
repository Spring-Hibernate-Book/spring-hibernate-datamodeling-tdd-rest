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

import com.oodd.spring.manytomanyunidirectional.dto.GroupDto;
import com.oodd.spring.manytomanyunidirectional.service.GroupService;

@Controller
@RequestMapping(value="/manytomanyunidirectional/group")
@Transactional
public class GroupController {

	@Autowired
	private GroupService service;
	
	@RequestMapping(value="/findAll", method=RequestMethod.GET)
	public @ResponseBody List<GroupDto> findAll(){
		return service.findAll();
	}
	
	@RequestMapping(value="/findById/{groupid}", method=RequestMethod.GET)
	public @ResponseBody GroupDto findById(@PathVariable("groupid") Integer groupid){
		return service.findById(groupid);
	}
	
	@RequestMapping(value="/create", method=RequestMethod.POST)
	@ResponseBody
	public void create(@RequestBody GroupDto group){
		service.create(group);
	}
	
	@RequestMapping(value="/remove/{groupid}", method=RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void remove(@PathVariable("groupid") Integer groupid){
		service.remove(groupid);
	}
	
	@RequestMapping(value="/edit", method=RequestMethod.POST)
	@ResponseBody
	public void edit(@RequestBody GroupDto groupDto){
		service.edit(groupDto);
	}
}
