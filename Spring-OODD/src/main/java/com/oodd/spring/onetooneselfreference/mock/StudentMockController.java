package com.oodd.spring.onetooneselfreference.mock;

import java.util.List;

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
@RequestMapping(value="/onetooneselfreference/mock")
public class StudentMockController {
	@RequestMapping(value="/findAll", method=RequestMethod.GET)
	public @ResponseBody List<StudentDto>  findAll(){
		return StudentInMemoryDB.INSTANCE.findAll();
	}
	@RequestMapping(value="/findById/{stuid}", method=RequestMethod.GET)
	public @ResponseBody StudentDto findById(@PathVariable("stuid") Integer stuid){
		return StudentInMemoryDB.INSTANCE.findById(stuid);
	}
	@RequestMapping(value="/create", method=RequestMethod.POST)
	@ResponseBody
	public void create(@RequestBody StudentDto student){
		StudentInMemoryDB.INSTANCE.add(student);
	}
	@RequestMapping(value="/remove/{stuid}", method=RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void remove(@PathVariable("stuid") Integer stuid){
		StudentInMemoryDB.INSTANCE.remove(stuid);
	}
	@RequestMapping(value="/edit", method=RequestMethod.POST)
	@ResponseBody
	public void edit(@RequestBody StudentDto student){
		StudentInMemoryDB.INSTANCE.edit(student);
	}
}
