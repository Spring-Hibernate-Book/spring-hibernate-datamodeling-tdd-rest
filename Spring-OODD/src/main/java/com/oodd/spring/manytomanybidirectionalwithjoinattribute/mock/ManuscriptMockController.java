package com.oodd.spring.manytomanybidirectionalwithjoinattribute.mock;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.oodd.spring.manytomanybidirectionalwithjoinattribute.dto.ManuscriptDto;


@Controller
@RequestMapping(value="/manytomanybidirectionalwithjoinattribute/manuscript/mock")
public class ManuscriptMockController {
	
	@RequestMapping(value="/findAll", method=RequestMethod.GET)
	public @ResponseBody List<ManuscriptDto> findAll(){
		return ManuscriptInMemoryDB.INSTANCE.findAll();
	}
	@RequestMapping(value="/findById/{manuscriptid}", method=RequestMethod.GET)
	public @ResponseBody ManuscriptDto findById(@PathVariable("manuscriptid") Integer manuscriptid){
		return ManuscriptInMemoryDB.INSTANCE.findById(manuscriptid);
	}
	@RequestMapping(value="/create", method=RequestMethod.POST)
	@ResponseBody
	public void create(@RequestBody ManuscriptDto manuscript){
		ManuscriptInMemoryDB.INSTANCE.add(manuscript);
	}
	@RequestMapping(value="/remove/{manuscriptid}", method=RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void remove(@PathVariable("manuscriptid") Integer manuscriptid){
		ManuscriptInMemoryDB.INSTANCE.remove(manuscriptid);
	}
	@RequestMapping(value="/edit", method=RequestMethod.POST)
	@ResponseBody
	public void edit(@RequestBody ManuscriptDto manuscript){
		ManuscriptInMemoryDB.INSTANCE.edit(manuscript);
	}
}
