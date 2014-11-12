package com.oodd.spring.onetomanyselfreference.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.oodd.spring.onetomanyselfreference.dto.CategoryDto;
import com.oodd.spring.onetomanyselfreference.service.CategoryService;

@Controller
@RequestMapping(value="/onetomanyselfreference")
public class CategoryController {
	@Autowired
	private CategoryService service;
	@RequestMapping(value="/findAll", method=RequestMethod.GET)
	public @ResponseBody List<CategoryDto> findAll(){
		return service.findAll();
	}
	@RequestMapping(value="/findById/{Id}", method=RequestMethod.GET)
	public @ResponseBody CategoryDto findById(@PathVariable("Id") Integer Id){
		return service.findById(Id);
	}
	@RequestMapping(value="/create", method=RequestMethod.POST)
	@ResponseBody
	public void create(@RequestBody CategoryDto categoryDto){
		service.create(categoryDto);
	}
	@RequestMapping(value="/remove/{Id}", method=RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void remove(@PathVariable("Id") Integer Id){
		service.remove(Id);
	}
	@RequestMapping(value="/edit", method=RequestMethod.POST)
	@ResponseBody
	public void edit(@RequestBody CategoryDto categoryDto){
		service.edit(categoryDto);
	}
}
