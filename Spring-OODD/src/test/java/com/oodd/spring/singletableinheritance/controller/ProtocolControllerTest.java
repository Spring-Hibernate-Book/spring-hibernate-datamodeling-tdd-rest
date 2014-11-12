package com.oodd.spring.singletableinheritance.controller;

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
import com.oodd.spring.singletableinheritance.dto.ProtocolDto;
import com.oodd.spring.singletableinheritance.dto.TCPDto;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration( locations = { "classpath:context.xml" } )
@TransactionConfiguration(defaultRollback = true)
@Transactional
public class ProtocolControllerTest {
	
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
		ProtocolDto protocolDto = new TCPDto();
		protocolDto.setName("ABC");
		
		String json = gson.toJson(protocolDto);
		json= json.replace("{", "");
		json= json.replace("}", "");
		json = "{\"type\":\"tcp\","+json+"}";
		
		MockHttpServletRequestBuilder requestBuilderOne = MockMvcRequestBuilders.post("/singletableinheritance/create");
		requestBuilderOne.contentType(MediaType.APPLICATION_JSON);
		requestBuilderOne.content(json.getBytes());
		this.mockMvc.perform(requestBuilderOne).andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	public void testUpdate() throws Exception {
		
		MockHttpServletRequestBuilder requestBuilder2 = MockMvcRequestBuilders.get("/singletableinheritance/findAll");
		MvcResult result = this.mockMvc.perform(requestBuilder2).andReturn();
		String response2 = result.getResponse().getContentAsString();
		Type listType = new TypeToken<ProtocolDto[]>() {}.getType();
		ProtocolDto[] protocolDtoList = gson.fromJson(response2, listType);
		ProtocolDto protocolDto2 = protocolDtoList[0];
		protocolDto2.setName("DEF");
		String json2 = gson.toJson(protocolDto2);
		json2= json2.replace("{", "");
		json2= json2.replace("}", "");
		json2 = "{\"type\":\"tcp\","+json2+"}";
		MockHttpServletRequestBuilder requestBuilder3 = MockMvcRequestBuilders.post("/singletableinheritance/edit");
		requestBuilder3.contentType(MediaType.APPLICATION_JSON);
		requestBuilder3.content(json2.getBytes());
		this.mockMvc.perform(requestBuilder3).andExpect(MockMvcResultMatchers.status().isOk());
	}

	public void testDelete() throws Exception {
		
		MockHttpServletRequestBuilder requestBuilder2 = MockMvcRequestBuilders.get("/singletableinheritance/findAll");
		MvcResult result = this.mockMvc.perform(requestBuilder2).andReturn();
		String response2 = result.getResponse().getContentAsString();
		Type listType = new TypeToken<ProtocolDto[]>() {}.getType();
		ProtocolDto[] protocolDtoList = gson.fromJson(response2, listType);
		ProtocolDto protocolDto2 = protocolDtoList[0];
		MockHttpServletRequestBuilder requestBuilder3 = MockMvcRequestBuilders.post("/singletableinheritance/remove/"+protocolDto2.getId());
		requestBuilder3.contentType(MediaType.APPLICATION_JSON);
		this.mockMvc.perform(requestBuilder3).andExpect(MockMvcResultMatchers.status().is(204));
	}
}
