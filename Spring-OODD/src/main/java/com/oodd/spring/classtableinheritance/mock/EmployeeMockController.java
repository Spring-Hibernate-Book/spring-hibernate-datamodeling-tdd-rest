package com.oodd.spring.classtableinheritance.mock;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.oodd.spring.classtableinheritance.dto.EmployeeDto;

@Controller
@RequestMapping(value="/classtableinheritance/mock")
public class EmployeeMockController {
	
	@RequestMapping(value="/findAll", method=RequestMethod.GET)
	public @ResponseBody EmployeeDto[] findAll(){
		List<EmployeeDto> list = EmployeeInMemoryDB.INSTANCE.findAll();
		return list.toArray(new EmployeeDto[list.size()]);
	}
	@RequestMapping(value="/findById/{employeeid}", method=RequestMethod.GET)
	public @ResponseBody EmployeeDto findById(@PathVariable("employeeid") Integer employeeid){
		return EmployeeInMemoryDB.INSTANCE.findById(employeeid);
	}
	@RequestMapping(value="/create", method=RequestMethod.POST)
	@ResponseBody
	public void create(@RequestBody EmployeeDto employee){
		EmployeeInMemoryDB.INSTANCE.add(employee);
	}
	@RequestMapping(value="/remove/{employeeid}", method=RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void remove(@PathVariable("employeeid") Integer employeeid){
		EmployeeInMemoryDB.INSTANCE.remove(employeeid);
	}
	@RequestMapping(value="/edit", method=RequestMethod.POST)
	@ResponseBody
	public void edit(@RequestBody EmployeeDto employee){
		EmployeeInMemoryDB.INSTANCE.edit(employee);
	}
}
