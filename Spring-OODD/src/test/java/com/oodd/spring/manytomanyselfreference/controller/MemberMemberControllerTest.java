package com.oodd.spring.manytomanyselfreference.controller;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.setup.StandaloneMockMvcBuilder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.oodd.spring.manytomanyselfreference.dto.MemberDto;
import com.oodd.spring.manytomanyselfreference.dto.MemberMemberDto;
import com.oodd.spring.manytomanyselfreference.service.MemberService;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration( locations = { "classpath:context.xml" } )
@TransactionConfiguration(defaultRollback = true)
@Transactional
public class MemberMemberControllerTest {
private Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd hh:mm:ss").create();

	@Autowired
	private MemberService memberService;
	
	@Resource
	private WebApplicationContext webApplicationContext;

	private MockMvc mockMvc;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.<StandaloneMockMvcBuilder>webAppContextSetup(webApplicationContext).build();
	}
	
	@Test
	public void testAll() throws Exception {
		testCreate();
		testPresent();
		testDelete();
	}
	
	public void testCreate() throws Exception {
		MemberMemberDto memberMemberDto = new MemberMemberDto();
		
		MemberDto memberDto1 = new MemberDto();
		memberDto1.setName("Sara Tencradi");
		memberService.create(memberDto1);
		
		MemberDto memberDto2 = new MemberDto();
		memberDto2.setName("Mike Scofield");
		memberService.create(memberDto2);
		
		List<MemberDto> memberDtos1 = memberService.findAll();
		MemberDto memberDto3 = memberDtos1.get(0);
		
		List<MemberDto> memberDtos2 = memberService.findAll();
		MemberDto memberDto4 = memberDtos2.get(0);
		
		memberMemberDto.setMemberId1(memberDto3);
		memberMemberDto.setMemberId2(memberDto4);	
				
		String json = gson.toJson(memberMemberDto);
		
		MockHttpServletRequestBuilder requestBuilderOne = MockMvcRequestBuilders.post("/manytomanyselfreference/membermember/create");
		requestBuilderOne.contentType(MediaType.APPLICATION_JSON);
		requestBuilderOne.content(json.getBytes());
		this.mockMvc.perform(requestBuilderOne).andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	public void testPresent() throws Exception {
		MemberMemberDto memberMemberDto = new MemberMemberDto();
		
		List<MemberDto> memberDtos1 = memberService.findAll();
		MemberDto memberDto1 = memberDtos1.get(0);
		
		List<MemberDto> memberDtos2 = memberService.findAll();
		MemberDto memberDto2 = memberDtos2.get(0);
		
		memberMemberDto.setMemberId1(memberDto1);
		memberMemberDto.setMemberId2(memberDto2);
		
		String json = gson.toJson(memberMemberDto);
		
		MockHttpServletRequestBuilder requestBuilderOne = MockMvcRequestBuilders.post("/manytomanyselfreference/membermember/isPresent");
		requestBuilderOne.contentType(MediaType.APPLICATION_JSON);
		requestBuilderOne.content(json.getBytes());
		this.mockMvc.perform(requestBuilderOne).andExpect(MockMvcResultMatchers.status().isOk());
	}	
	
	public void testDelete() throws Exception {			
		MemberMemberDto memberMemberDto = new MemberMemberDto();
		
		List<MemberDto> memberDtos1 = memberService.findAll();
		MemberDto memberDto1 = memberDtos1.get(0);
		
		List<MemberDto> memberDtos2 = memberService.findAll();
		MemberDto memberDto2 = memberDtos2.get(0);
		
		memberMemberDto.setMemberId1(memberDto1);
		memberMemberDto.setMemberId2(memberDto2);
		
		String json = gson.toJson(memberMemberDto);
		
		MockHttpServletRequestBuilder requestBuilder2 = MockMvcRequestBuilders.post("/manytomanyselfreference/membermember/remove");
		requestBuilder2.contentType(MediaType.APPLICATION_JSON);
		requestBuilder2.content(json.getBytes());
		this.mockMvc.perform(requestBuilder2).andExpect(MockMvcResultMatchers.status().is(204));
	}
}