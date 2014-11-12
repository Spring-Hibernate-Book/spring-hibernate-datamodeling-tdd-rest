package com.oodd.spring.onetoonebidirectional.controller;

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

import com.oodd.spring.onetoonebidirectional.dto.CustomerDto;
import com.oodd.spring.onetoonebidirectional.service.CustomerService;

@Controller
@RequestMapping(value="/onetoonebidirectional")
@Transactional
public class CustomerController {
	@Autowired
	private CustomerService service ;
	@RequestMapping(value="/findAll", method=RequestMethod.GET)
	public @ResponseBody List<CustomerDto> findAll(){
		return service.findAll();
	}
	@RequestMapping(value="/findById/{customerid}", method=RequestMethod.GET)
	public @ResponseBody CustomerDto findById(@PathVariable("customerid") Integer customerid){
		return service.findById(customerid);
	}
	@RequestMapping(value="/create", method=RequestMethod.POST)
	@ResponseBody
	public void creat(@RequestBody CustomerDto customerDto){
		service.create(customerDto);
	}
	@RequestMapping(value="/remove/{customerid}", method=RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void remove(@PathVariable("customerid") Integer customerid){
		service.remove(customerid);
	}
	@RequestMapping(value="/edit", method=RequestMethod.POST)
	@ResponseBody
	public void edit(@RequestBody CustomerDto customerDto){
		service.edit(customerDto);
	}
}
