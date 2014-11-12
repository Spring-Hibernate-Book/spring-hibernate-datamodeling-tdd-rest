package com.oodd.spring.onetomanyselfreference.mock;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.oodd.spring.onetomanyselfreference.dto.CategoryDto;
@Controller
@RequestMapping(value="/onetomanyselfreference/mock")
public class CategoryMockController {
	@RequestMapping(value="/findAll", method=RequestMethod.GET)
	public @ResponseBody List<CategoryDto> findAll(){
		return CategoryInMemoryDB.INSTANCE.findAll();
	}
	@RequestMapping(value="/findById/{Id}", method=RequestMethod.GET)
	public @ResponseBody CategoryDto findById(@PathVariable("Id") Integer Id){
		return CategoryInMemoryDB.INSTANCE.findById(Id);
	}
	@RequestMapping(value="/create", method=RequestMethod.POST)
	@ResponseBody
	public void create(@RequestBody CategoryDto categoryDto){
		CategoryInMemoryDB.INSTANCE.add(categoryDto);
	}
	@RequestMapping(value="/remove/{Id}", method=RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void remove(@PathVariable("Id") Integer Id){
		CategoryInMemoryDB.INSTANCE.remove(Id);
	}
	@RequestMapping(value="/edit", method=RequestMethod.POST)
	@ResponseBody
	public void edit(@RequestBody CategoryDto categoryDto){
		CategoryInMemoryDB.INSTANCE.edit(categoryDto);
	}
}
