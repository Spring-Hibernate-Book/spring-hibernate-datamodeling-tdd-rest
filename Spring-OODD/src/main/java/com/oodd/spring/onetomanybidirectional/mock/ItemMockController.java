package com.oodd.spring.onetomanybidirectional.mock;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.oodd.spring.onetomanybidirectional.dto.ItemDto;
@Controller
@RequestMapping(value="/onetomanybidirectional/mock")
public class ItemMockController {
	@RequestMapping(value="/findAll", method=RequestMethod.GET)
	public @ResponseBody List<ItemDto> findAll(){
		return ItemInMemoryDB.INSTANCE.findAll();
	}
	@RequestMapping(value="/findById/{itemid}", method=RequestMethod.GET)
	public @ResponseBody ItemDto findById(@PathVariable("itemid") Integer itemid){
		return ItemInMemoryDB.INSTANCE.findById(itemid);
	}
	@RequestMapping(value="/create", method=RequestMethod.POST)
	@ResponseBody
	public void creat(@RequestBody ItemDto itemDto){
		ItemInMemoryDB.INSTANCE.add(itemDto);
	}
	@RequestMapping(value="/remove/{itemid}", method=RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void remove(@PathVariable("itemid") Integer itemid){
		ItemInMemoryDB.INSTANCE.remove(itemid);
	}
	@RequestMapping(value="/edit", method=RequestMethod.POST)
	@ResponseBody
	public void edit(@RequestBody ItemDto itemDto){
		ItemInMemoryDB.INSTANCE.edit(itemDto);
	}

}
