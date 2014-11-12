package com.oodd.spring.manytomanyselfreferencewithjoinattribute.controller;

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

import com.oodd.spring.manytomanyselfreferencewithjoinattribute.dto.WorkerWorkerDto;
import com.oodd.spring.manytomanyselfreferencewithjoinattribute.service.WorkerWorkerService;

@Controller
@RequestMapping(value="/manytomanyselfreferencewithjoinattribute/workerworker")
@Transactional
public class WorkerWorkerController {
	
	@Autowired	
	private WorkerWorkerService service;
	
	@RequestMapping(value="/findAll", method=RequestMethod.GET)
	public @ResponseBody List<WorkerWorkerDto> findAll(){
		return service.findAll();
	}
	
	@RequestMapping(value="/isPresent", method=RequestMethod.POST)	
	public @ResponseBody boolean isPresent(@RequestBody WorkerWorkerDto workerWorkerDto){	
		return service.isPresent(workerWorkerDto);
	}
	
	@RequestMapping(value="/create", method=RequestMethod.POST)
	@ResponseBody
	public void create(@RequestBody WorkerWorkerDto workerWorkerDto){	
		service.create(workerWorkerDto);
	}
	
	@RequestMapping(value="/remove", method=RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void remove(@RequestBody WorkerWorkerDto workerWorkerDto){
		service.remove(workerWorkerDto);
	}
}
