package com.oodd.spring.onetooneunidirectional.controller;
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

import com.oodd.spring.onetooneunidirectional.dto.BookDto;
import com.oodd.spring.onetooneunidirectional.service.BookService;
@Controller
@RequestMapping(value="/onetooneunidirectional")
@Transactional
public class BookController {
	@Autowired
	private BookService service ;
	
	@RequestMapping(value="/findAll", method=RequestMethod.GET)
	public @ResponseBody List<BookDto> findAll(){
		return service.findAll();
	}
	@RequestMapping(value="/findById/{bookId}", method=RequestMethod.GET)
	public @ResponseBody BookDto findById(@PathVariable("bookId") Integer bookId){
		return service.findById(bookId);
	}
	@RequestMapping(value="/create", method=RequestMethod.POST)
	@ResponseBody
	public void create(@RequestBody BookDto book){
		service.create(book);
	}
	@RequestMapping(value="/remove/{bookId}", method=RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void remove(@PathVariable("bookId") Integer bookId){
		service.remove(bookId);
	}
	@RequestMapping(value="/edit", method=RequestMethod.POST)
	@ResponseBody
	public void edit(@RequestBody BookDto book){
		service.edit(book);
	}
}
