package com.oodd.spring.manytomanyselfreference.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.oodd.spring.manytomanyselfreference.dto.MemberDto;
import com.oodd.spring.manytomanyselfreference.service.MemberService;

@Controller
@RequestMapping(value="/manytomanyselfreference/member")
@Transactional
public class MemberController {

	@Autowired
	private MemberService service;
	
	@RequestMapping(value="/findAll", method=RequestMethod.GET)
	public @ResponseBody List<MemberDto> findAll(){
		return service.findAll();
	}
	
	@RequestMapping(value="/findById/{memberid}", method=RequestMethod.GET)
	public @ResponseBody MemberDto findById(@PathVariable("memberid") Integer memberid){
		return service.findById(memberid);
	}
	
	@RequestMapping(value="/create", method=RequestMethod.POST)
	@ResponseBody
	public void create(@RequestBody MemberDto memberDto){
		service.create(memberDto);
	}
	
	@RequestMapping(value="/remove/{memberid}", method=RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void remove(@PathVariable("memberid") Integer memberid){
		service.remove(memberid);
	}
	
	@RequestMapping(value="/edit", method=RequestMethod.POST)
	@ResponseBody
	public void edit(@RequestBody MemberDto memberDto){
		service.edit(memberDto);
	}
}