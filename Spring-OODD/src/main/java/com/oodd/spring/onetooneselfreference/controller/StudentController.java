package com.oodd.spring.onetooneselfreference.controller;

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

import com.oodd.spring.onetooneselfreference.dto.StudentDto;
import com.oodd.spring.onetooneselfreference.service.StudentService;

@Controller
@RequestMapping(value="/onetooneselfreference")
public class StudentController {
	@Autowired
	private StudentService service ;
	@RequestMapping(value="/findAll", method=RequestMethod.GET)
	public @ResponseBody List<StudentDto>  findAll(){
		return service.findAll();
	}
	@RequestMapping(value="/findById/{stuid}", method=RequestMethod.GET)
	public @ResponseBody StudentDto findById(@PathVariable("stuid") Integer stuid){
		return service.findById(stuid);
	}
	@RequestMapping(value="/create", method=RequestMethod.POST)
	@ResponseBody
	public void create(@RequestBody StudentDto student){
		service.create(student);
	}
	@RequestMapping(value="/remove/{stuid}", method=RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void remove(@PathVariable("stuid") Integer stuid){
		service.remove(stuid);
	}
	@RequestMapping(value="/edit", method=RequestMethod.POST)
	@ResponseBody
	public void edit(@RequestBody StudentDto student){
		service.edit(student);
	}
}
