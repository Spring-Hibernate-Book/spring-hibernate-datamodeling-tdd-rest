package com.oodd.spring.manytomanybidirectionalwithjoinattribute.mock;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.oodd.spring.manytomanybidirectionalwithjoinattribute.dto.ManuscriptAuthorDto;

@Controller
@RequestMapping(value="/manytomanybidirectionalwithjoinattribute/manuscriptauthor/mock")
public class ManuscriptAuthorMockController {
	
	@RequestMapping(value="/findAll", method=RequestMethod.GET)
	public @ResponseBody List<ManuscriptAuthorDto> findAll(){
		return ManuscriptAuthorInMemoryDB.INSTANCE.findAll();
	}
	@RequestMapping(value="/create", method=RequestMethod.POST)
	@ResponseBody
	public void create(@RequestBody ManuscriptAuthorDto manuscriptAuthorDto){
		ManuscriptAuthorInMemoryDB.INSTANCE.add(manuscriptAuthorDto);
	}
	@RequestMapping(value="/remove", method=RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void remove(@RequestBody ManuscriptAuthorDto manuscriptAuthorDto){
		ManuscriptAuthorInMemoryDB.INSTANCE.remove(manuscriptAuthorDto);
	}
	@RequestMapping(value="/isPresent", method=RequestMethod.POST)	
	public @ResponseBody boolean isPresent(@RequestBody ManuscriptAuthorDto manuscriptAuthorDto){	
		return ManuscriptAuthorInMemoryDB.INSTANCE.isPresent(manuscriptAuthorDto);
	}
}
