package com.oodd.spring.manytomanyselfreference.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.oodd.spring.manytomanyselfreference.dto.MemberMemberDto;
import com.oodd.spring.manytomanyselfreference.service.MemberMemberService;

@Controller
@RequestMapping(value="/manytomanyselfreference/membermember")
@Transactional
public class MemberMemberController {
	
	@Autowired	
	private MemberMemberService service;
	
	@RequestMapping(value="/findAll", method=RequestMethod.GET)
	public @ResponseBody List<MemberMemberDto> findAll(){
		return service.findAll();
	}
	
	@RequestMapping(value="/isPresent", method=RequestMethod.POST)	
	public @ResponseBody boolean isPresent(@RequestBody MemberMemberDto memberMemberDto){	
		return service.isPresent(memberMemberDto);
	}
	
	@RequestMapping(value="/create", method=RequestMethod.POST)
	@ResponseBody
	public void create(@RequestBody MemberMemberDto memberMemberDto){	
		service.create(memberMemberDto);
	}
	
	@RequestMapping(value="/remove", method=RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void remove(@RequestBody MemberMemberDto memberMemberDto){
		service.remove(memberMemberDto);
	}
}
