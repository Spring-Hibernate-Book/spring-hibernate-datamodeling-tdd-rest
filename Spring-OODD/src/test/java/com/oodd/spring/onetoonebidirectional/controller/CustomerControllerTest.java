package com.oodd.spring.onetoonebidirectional.controller;

import java.lang.reflect.Type;
import java.util.List;

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
import com.oodd.spring.onetoonebidirectional.dto.CustomerDto;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration( locations = { "classpath:context.xml" } )
@TransactionConfiguration(defaultRollback = true)
@Transactional
public class CustomerControllerTest {
	private final Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd hh:mm:ss").create();

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
		CustomerDto customerDto1 = new CustomerDto();
		customerDto1.setName("Alex");
		customerDto1.setAmount(200.0);
		String json = gson.toJson(customerDto1);
		MockHttpServletRequestBuilder requestBuilderOne = MockMvcRequestBuilders.post("/onetoonebidirectional/create");
		requestBuilderOne.contentType(MediaType.APPLICATION_JSON);
		requestBuilderOne.content(json.getBytes());
		this.mockMvc.perform(requestBuilderOne).andExpect(MockMvcResultMatchers.status().isOk());
	}
	public void testUpdate() throws Exception {
		MockHttpServletRequestBuilder requestBuilder2 = MockMvcRequestBuilders.get("/onetoonebidirectional/findAll");
		MvcResult result = this.mockMvc.perform(requestBuilder2).andReturn();
		String response2 = result.getResponse().getContentAsString();
		Type listType = new TypeToken<List<CustomerDto>>() {}.getType();
		List<CustomerDto> customerDtos = gson.fromJson(response2, listType);
		CustomerDto customerDto = customerDtos.get(0);
		customerDto.setAmount(400.0);
		String json2 = gson.toJson(customerDto);
		MockHttpServletRequestBuilder requestBuilder3 = MockMvcRequestBuilders.post("/onetoonebidirectional/edit");
		requestBuilder3.contentType(MediaType.APPLICATION_JSON);
		requestBuilder3.content(json2.getBytes());
		this.mockMvc.perform(requestBuilder3).andExpect(MockMvcResultMatchers.status().isOk());
	}
	public void testDelete() throws Exception {
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/onetoonebidirectional/findAll");
		MvcResult result = this.mockMvc.perform(requestBuilder).andReturn();
		String response2 = result.getResponse().getContentAsString();
		Type listType = new TypeToken<List<CustomerDto>>() {}.getType();
		List<CustomerDto> customerDtos = gson.fromJson(response2, listType);
		CustomerDto customerDto = customerDtos.get(0);
		MockHttpServletRequestBuilder requestBuilder2 = MockMvcRequestBuilders.post("/onetoonebidirectional/remove/"+customerDto.getId());
		requestBuilder2.contentType(MediaType.APPLICATION_JSON);
		this.mockMvc.perform(requestBuilder2).andExpect(MockMvcResultMatchers.status().is(204));
	}
}
