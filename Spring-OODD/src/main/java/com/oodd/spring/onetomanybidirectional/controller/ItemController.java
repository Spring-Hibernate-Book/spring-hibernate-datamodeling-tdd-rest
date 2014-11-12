package com.oodd.spring.onetomanybidirectional.controller;

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

import com.oodd.spring.onetomanybidirectional.dto.ItemDto;
import com.oodd.spring.onetomanybidirectional.service.ItemService;
@Controller
@RequestMapping(value="/onetomanybidirectional")
public class ItemController {
	@Autowired
	private ItemService service;
	@RequestMapping(value="/findAll", method=RequestMethod.GET)
	public @ResponseBody List<ItemDto> findAll(){
		return service.findAll();
	}
	@RequestMapping(value="/findById/{itemid}", method=RequestMethod.GET)
	public @ResponseBody ItemDto findById(@PathVariable("itemid") Integer itemid){
		return service.findById(itemid);
	}
	@RequestMapping(value="/create", method=RequestMethod.POST)
	@ResponseBody
	public void create(@RequestBody ItemDto itemDto){
		service.create(itemDto);
	}
	@RequestMapping(value="/remove/{itemid}", method=RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void remove(@PathVariable("itemid") Integer itemid){
		service.remove(itemid);
	}
	@RequestMapping(value="/edit", method=RequestMethod.POST)
	@ResponseBody
	public void edit(@RequestBody ItemDto itemDto){
		service.edit(itemDto);
	}
}
