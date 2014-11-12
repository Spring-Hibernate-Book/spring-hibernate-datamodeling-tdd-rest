package com.oodd.spring.classtableinheritance.controller;

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

import com.oodd.spring.classtableinheritance.dto.EmployeeDto;
import com.oodd.spring.classtableinheritance.service.EmployeeService;

@Controller
@RequestMapping(value="/classtableinheritance")
@Transactional
public class EmployeeController {
	
	@Autowired
	private EmployeeService service;
	
	@RequestMapping(value="/findAll", method=RequestMethod.GET)
	public @ResponseBody EmployeeDto[] findAll(){
		List<EmployeeDto> list = service.findAll();
		return list.toArray(new EmployeeDto[list.size()]);
	}
	@RequestMapping(value="/findById/{employeeid}", method=RequestMethod.GET)
	public @ResponseBody EmployeeDto findById(@PathVariable("employeeid") Integer employeeid){
		return service.findById(employeeid);
	}
	@RequestMapping(value="/create", method=RequestMethod.POST)
	@ResponseBody
	public void create(@RequestBody EmployeeDto employee){
		service.create(employee);
	}
	@RequestMapping(value="/remove/{employeeid}", method=RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void remove(@PathVariable("employeeid") Integer employeeid){
		service.remove(employeeid);
	}
	@RequestMapping(value="/edit", method=RequestMethod.POST)
	@ResponseBody
	public void edit(@RequestBody EmployeeDto employee){
		service.edit(employee);
	}
}