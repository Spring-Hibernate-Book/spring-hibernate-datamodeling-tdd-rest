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

import com.oodd.spring.manytomanybidirectionalwithjoinattribute.dto.AuthorDto;


@Controller
@RequestMapping(value="/manytomanybidirectionalwithjoinattribute/author/mock")
public class AuthorMockController {
	
	@RequestMapping(value="/findAll", method=RequestMethod.GET)
	public @ResponseBody List<AuthorDto> findAll(){
		return AuthorInMemoryDB.INSTANCE.findAll();
	}
	@RequestMapping(value="/findById/{authorid}", method=RequestMethod.GET)
	public @ResponseBody AuthorDto findById(@PathVariable("authorid") Integer authorid){
		return AuthorInMemoryDB.INSTANCE.findById(authorid);
	}
	@RequestMapping(value="/create", method=RequestMethod.POST)
	@ResponseBody
	public void create(@RequestBody AuthorDto author){
		AuthorInMemoryDB.INSTANCE.add(author);
	}
	@RequestMapping(value="/remove/{authorid}", method=RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void remove(@PathVariable("authorid") Integer authorid){
		AuthorInMemoryDB.INSTANCE.remove(authorid);
	}
	@RequestMapping(value="/edit", method=RequestMethod.POST)
	@ResponseBody
	public void edit(@RequestBody AuthorDto author){
		AuthorInMemoryDB.INSTANCE.edit(author);
	}
}
