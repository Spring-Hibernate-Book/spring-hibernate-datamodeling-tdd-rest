package com.oodd.spring.manytomanyunidirectional.mock;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.oodd.spring.manytomanyunidirectional.dto.GroupDto;


@Controller
@RequestMapping(value="/manytomanyunidirectional/group/mock")
public class GroupMockController {
	
	@RequestMapping(value="/findAll", method=RequestMethod.GET)
	public @ResponseBody List<GroupDto> findAll(){
		return GroupInMemoryDB.INSTANCE.findAll();
	}
	@RequestMapping(value="/findById/{groupid}", method=RequestMethod.GET)
	public @ResponseBody GroupDto findById(@PathVariable("groupid") Integer groupid){
		return GroupInMemoryDB.INSTANCE.findById(groupid);
	}
	@RequestMapping(value="/create", method=RequestMethod.POST)
	@ResponseBody
	public void create(@RequestBody GroupDto group){
		GroupInMemoryDB.INSTANCE.add(group);
	}
	@RequestMapping(value="/remove/{groupid}", method=RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void remove(@PathVariable("groupid") Integer groupid){
		GroupInMemoryDB.INSTANCE.remove(groupid);
	}
	@RequestMapping(value="/edit", method=RequestMethod.POST)
	@ResponseBody
	public void edit(@RequestBody GroupDto group){
		GroupInMemoryDB.INSTANCE.edit(group);
	}
}
