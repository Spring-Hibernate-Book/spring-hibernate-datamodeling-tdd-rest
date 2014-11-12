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

import com.oodd.spring.manytomanyunidirectional.dto.UserDto;


@Controller
@RequestMapping(value="/manytomanyunidirectional/user/mock")
public class UserMockController {
	
	@RequestMapping(value="/findAll", method=RequestMethod.GET)
	public @ResponseBody List<UserDto> findAll(){
		return UserInMemoryDB.INSTANCE.findAll();
	}
	@RequestMapping(value="/findById/{userid}", method=RequestMethod.GET)
	public @ResponseBody UserDto findById(@PathVariable("userid") Integer userid){
		return UserInMemoryDB.INSTANCE.findById(userid);
	}
	@RequestMapping(value="/create", method=RequestMethod.POST)
	@ResponseBody
	public void create(@RequestBody UserDto user){
		UserInMemoryDB.INSTANCE.add(user);
	}
	@RequestMapping(value="/remove/{userid}", method=RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void remove(@PathVariable("userid") Integer userid){
		UserInMemoryDB.INSTANCE.remove(userid);
	}
	@RequestMapping(value="/edit", method=RequestMethod.POST)
	@ResponseBody
	public void edit(@RequestBody UserDto user){
		UserInMemoryDB.INSTANCE.edit(user);
	}
}
