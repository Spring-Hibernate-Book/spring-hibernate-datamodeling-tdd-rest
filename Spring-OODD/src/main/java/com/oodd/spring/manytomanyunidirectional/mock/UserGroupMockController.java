package com.oodd.spring.manytomanyunidirectional.mock;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.oodd.spring.manytomanyunidirectional.dto.UserGroupDto;

@Controller
@RequestMapping(value="/manytomanyunidirectional/usergroup/mock")
public class UserGroupMockController {
	
	@RequestMapping(value="/findAll", method=RequestMethod.GET)
	public @ResponseBody List<UserGroupDto> findAll(){
		return UserGroupInMemoryDB.INSTANCE.findAll();
	}
	@RequestMapping(value="/create", method=RequestMethod.POST)
	@ResponseBody
	public void create(@RequestBody UserGroupDto userGroupDto){
		UserGroupInMemoryDB.INSTANCE.add(userGroupDto);
	}
	@RequestMapping(value="/remove", method=RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void remove(@RequestBody UserGroupDto userGroupDto){
		UserGroupInMemoryDB.INSTANCE.remove(userGroupDto);
	}
	@RequestMapping(value="/isPresent", method=RequestMethod.POST)	
	public @ResponseBody boolean isPresent(@RequestBody UserGroupDto userGroupDto){	
		return UserGroupInMemoryDB.INSTANCE.isPresent(userGroupDto);
	}
}
