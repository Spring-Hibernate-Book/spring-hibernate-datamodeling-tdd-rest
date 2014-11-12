package com.oodd.spring.concretetableinheritance.controller;

import java.lang.reflect.Type;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.setup.StandaloneMockMvcBuilder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.oodd.spring.concretetableinheritance.dto.BuildingDto;
import com.oodd.spring.concretetableinheritance.dto.EstateDto;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration( locations = { "classpath:context.xml" } )
@TransactionConfiguration(defaultRollback = true)
@Transactional
public class EstateControllerTest {
	
	private Gson gson = new GsonBuilder().create();
	
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
		testUpdate();
		testDelete();
	}

	public void testCreate() throws Exception {
		
		EstateDto estateDto = new BuildingDto();
		estateDto.setName("ABC");		
		((BuildingDto)estateDto).setFloors(20);
		String json = gson.toJson(estateDto);
		json= json.replace("{", "");
		json= json.replace("}", "");
		json = "{\"type\":\"building\","+json+"}";
		MockHttpServletRequestBuilder requestBuilderOne = MockMvcRequestBuilders.post("/concretetableinheritance/create");
		requestBuilderOne.contentType(MediaType.APPLICATION_JSON);
		requestBuilderOne.content(json.getBytes());
		this.mockMvc.perform(requestBuilderOne).andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	public void testUpdate() throws Exception {
		
		MockHttpServletRequestBuilder requestBuilder2 = MockMvcRequestBuilders.get("/concretetableinheritance/findAll");
		MvcResult result = this.mockMvc.perform(requestBuilder2).andReturn();
		String response2 = result.getResponse().getContentAsString();
		
		Type listType = new TypeToken<EstateDto[]>() {}.getType();
		EstateDto[] estateDtoList = gson.fromJson(response2, listType);
		EstateDto estateDto2 = estateDtoList[0];
		
		EstateDto newEstateDto = new BuildingDto();
		newEstateDto.setId(estateDto2.getId());
		newEstateDto.setName("DEF");		
		((BuildingDto)newEstateDto).setFloors(21);
		
		String json2 = gson.toJson(newEstateDto);
		json2= json2.replace("{", "");
		json2= json2.replace("}", "");
		json2 = "{\"type\":\"building\","+json2+"}";
		MockHttpServletRequestBuilder requestBuilder3 = MockMvcRequestBuilders.post("/concretetableinheritance/edit");
		requestBuilder3.contentType(MediaType.APPLICATION_JSON);
		requestBuilder3.content(json2.getBytes());
		this.mockMvc.perform(requestBuilder3).andExpect(MockMvcResultMatchers.status().isOk());
	}

	public void testDelete() throws Exception {
		
		MockHttpServletRequestBuilder requestBuilder2 = MockMvcRequestBuilders.get("/concretetableinheritance/findAll");
		MvcResult result = this.mockMvc.perform(requestBuilder2).andReturn();
		String response2 = result.getResponse().getContentAsString();
		
		Type listType = new TypeToken<EstateDto[]>() {}.getType();
		EstateDto[] estateDtoList = gson.fromJson(response2, listType);
		EstateDto estateDto2 = estateDtoList[0];		
		MockHttpServletRequestBuilder requestBuilder3 = MockMvcRequestBuilders.post("/concretetableinheritance/remove/"+estateDto2.getId());
		requestBuilder3.contentType(MediaType.APPLICATION_JSON);
		this.mockMvc.perform(requestBuilder3).andExpect(MockMvcResultMatchers.status().is(204));
	}
}
