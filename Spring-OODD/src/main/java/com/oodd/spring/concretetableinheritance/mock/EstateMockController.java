package com.oodd.spring.concretetableinheritance.mock;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.oodd.spring.concretetableinheritance.dto.EstateDto;

@Controller
@RequestMapping(value="/concretetableinheritance/mock")
public class EstateMockController {
	
	@RequestMapping(value="/findAll", method=RequestMethod.GET)
	public @ResponseBody EstateDto[] findAll(){
		List<EstateDto> list = EstateInMemoryDB.INSTANCE.findAll();
		return list.toArray(new EstateDto[list.size()]);
	}
	@RequestMapping(value="/findById/{estateid}", method=RequestMethod.GET)
	public @ResponseBody EstateDto findById(@PathVariable("estateid") Integer estateid){
		return EstateInMemoryDB.INSTANCE.findById(estateid);
	}
	@RequestMapping(value="/create", method=RequestMethod.POST)
	@ResponseBody
	public void create(@RequestBody EstateDto estate){
		EstateInMemoryDB.INSTANCE.add(estate);
	}
	@RequestMapping(value="/remove/{estateid}", method=RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void remove(@PathVariable("estateid") Integer estateid){
		EstateInMemoryDB.INSTANCE.remove(estateid);
	}
	@RequestMapping(value="/edit", method=RequestMethod.POST)
	@ResponseBody
	public void edit(@RequestBody EstateDto estate){
		EstateInMemoryDB.INSTANCE.edit(estate);
	}
}
