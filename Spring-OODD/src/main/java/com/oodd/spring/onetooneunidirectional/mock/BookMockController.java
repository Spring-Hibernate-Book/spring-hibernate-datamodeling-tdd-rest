package com.oodd.spring.onetooneunidirectional.mock;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.oodd.spring.onetooneunidirectional.dto.BookDto;
@Controller
@RequestMapping(value="/onetooneunidirectional/mock")

public class BookMockController {
	@RequestMapping(value="/findAll", method=RequestMethod.GET)
	public @ResponseBody List<BookDto> findAll(){
		return BookInMemoryDB.INSTANCE.findAll();
	}
	@RequestMapping(value="/findById/{bookid}", method=RequestMethod.GET)
	public @ResponseBody BookDto findById(@PathVariable("bookid") Integer bookid){
		return BookInMemoryDB.INSTANCE.findById(bookid);
	}
	@RequestMapping(value="/create", method=RequestMethod.POST)
	@ResponseBody
	public void create(@RequestBody BookDto bookDto){
		 BookInMemoryDB.INSTANCE.add(bookDto);
	}
	@RequestMapping(value="/remove/{bookid}", method=RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void remove(@PathVariable("bookid") Integer bookid){
		BookInMemoryDB.INSTANCE.remove(bookid);
	}
	@RequestMapping(value="/edit", method=RequestMethod.POST)
	@ResponseBody
	public void edit(@RequestBody BookDto bookDto){
		BookInMemoryDB.INSTANCE.edit(bookDto);
	}
}
