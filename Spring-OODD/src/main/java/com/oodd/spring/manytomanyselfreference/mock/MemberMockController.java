package com.oodd.spring.manytomanyselfreference.mock;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.oodd.spring.manytomanyselfreference.dto.MemberDto;


@Controller
@RequestMapping(value="/manytomanyselfreference/member/mock")
public class MemberMockController {
	
	@RequestMapping(value="/findAll", method=RequestMethod.GET)
	public @ResponseBody List<MemberDto> findAll(){
		return MemberInMemoryDB.INSTANCE.findAll();
	}
	@RequestMapping(value="/findById/{memberId}", method=RequestMethod.GET)
	public @ResponseBody MemberDto findById(@PathVariable("memberId") Integer memberId){
		return MemberInMemoryDB.INSTANCE.findById(memberId);
	}
	@RequestMapping(value="/create", method=RequestMethod.POST)
	@ResponseBody
	public void create(@RequestBody MemberDto memberDto){
		MemberInMemoryDB.INSTANCE.add(memberDto);
	}
	@RequestMapping(value="/remove/{memberId}", method=RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void remove(@PathVariable("memberId") Integer memberId){
		MemberInMemoryDB.INSTANCE.remove(memberId);
	}
	@RequestMapping(value="/edit", method=RequestMethod.POST)
	@ResponseBody
	public void edit(@RequestBody MemberDto memberDto){
		MemberInMemoryDB.INSTANCE.edit(memberDto);
	}
}
