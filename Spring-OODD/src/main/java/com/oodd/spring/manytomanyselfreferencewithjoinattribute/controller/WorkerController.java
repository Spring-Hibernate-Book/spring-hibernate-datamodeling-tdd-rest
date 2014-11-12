package com.oodd.spring.manytomanyselfreferencewithjoinattribute.controller;

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

import com.oodd.spring.manytomanyselfreferencewithjoinattribute.dto.WorkerDto;
import com.oodd.spring.manytomanyselfreferencewithjoinattribute.service.WorkerService;

@Controller
@RequestMapping(value="/manytomanyselfreferencewithjoinattribute/worker")
@Transactional
public class WorkerController {

	@Autowired
	private WorkerService service;
	
	@RequestMapping(value="/findAll", method=RequestMethod.GET)
	public @ResponseBody List<WorkerDto> findAll(){
		return service.findAll();
	}
	
	@RequestMapping(value="/findById/{workerId}", method=RequestMethod.GET)
	public @ResponseBody WorkerDto findById(@PathVariable("workerId") Integer workerId){
		return service.findById(workerId);
	}
	
	@RequestMapping(value="/create", method=RequestMethod.POST)
	@ResponseBody
	public void create(@RequestBody WorkerDto clientDto){
		service.create(clientDto);
	}
	
	@RequestMapping(value="/remove/{workerId}", method=RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void remove(@PathVariable("workerId") Integer workerId){
		service.remove(workerId);
	}
	
	@RequestMapping(value="/edit", method=RequestMethod.POST)
	@ResponseBody
	public void edit(@RequestBody WorkerDto workerDto){
		service.edit(workerDto);
	}
}