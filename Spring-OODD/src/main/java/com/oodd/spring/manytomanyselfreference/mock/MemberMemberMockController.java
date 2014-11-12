package com.oodd.spring.manytomanyselfreference.mock;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.oodd.spring.manytomanyselfreference.dto.MemberMemberDto;

@Controller
@RequestMapping(value="/manytomanyselfreference/membermember/mock")
public class MemberMemberMockController {
	
	@RequestMapping(value="/findAll", method=RequestMethod.GET)
	public @ResponseBody List<MemberMemberDto> findAll(){
		return MemberMemberInMemoryDB.INSTANCE.findAll();
	}
	@RequestMapping(value="/create", method=RequestMethod.POST)
	@ResponseBody
	public void create(@RequestBody MemberMemberDto memberMemberDto){
		MemberMemberInMemoryDB.INSTANCE.add(memberMemberDto);
	}
	@RequestMapping(value="/remove", method=RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void remove(@RequestBody MemberMemberDto memberMemberDto){
		MemberMemberInMemoryDB.INSTANCE.remove(memberMemberDto);
	}
	@RequestMapping(value="/isPresent", method=RequestMethod.POST)	
	public @ResponseBody boolean isPresent(@RequestBody MemberMemberDto memberMemberDto){	
		return MemberMemberInMemoryDB.INSTANCE.isPresent(memberMemberDto);
	}
}
