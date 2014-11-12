package com.oodd.spring.classtableinheritance.controller;

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
import com.oodd.spring.classtableinheritance.dto.EmployeeDto;
import com.oodd.spring.classtableinheritance.dto.PermanentEmployeeDto;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration( locations = { "classpath:context.xml" } )
@TransactionConfiguration(defaultRollback = true)
@Transactional
public class EmployeeControllerTest {
	
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
		
		EmployeeDto employeeDto = new PermanentEmployeeDto();
		employeeDto.setName("ABC");		
		((PermanentEmployeeDto)employeeDto).setLeaves(20);
		((PermanentEmployeeDto)employeeDto).setSalary(1000000);
		String json = gson.toJson(employeeDto);
		json= json.replace("{", "");
		json= json.replace("}", "");
		json = "{\"type\":\"permanentEmployee\","+json+"}";
		MockHttpServletRequestBuilder requestBuilderOne = MockMvcRequestBuilders.post("/classtableinheritance/create");
		requestBuilderOne.contentType(MediaType.APPLICATION_JSON);
		requestBuilderOne.content(json.getBytes());
		this.mockMvc.perform(requestBuilderOne).andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	public void testUpdate() throws Exception {
		
		MockHttpServletRequestBuilder requestBuilder2 = MockMvcRequestBuilders.get("/classtableinheritance/findAll");
		MvcResult result = this.mockMvc.perform(requestBuilder2).andReturn();
		String response2 = result.getResponse().getContentAsString();
		
		Type listType = new TypeToken<EmployeeDto[]>() {}.getType();
		EmployeeDto[] employeeDtoList = gson.fromJson(response2, listType);
		EmployeeDto employeeDto2 = employeeDtoList[0];
		
		EmployeeDto newEmployeeDto = new PermanentEmployeeDto();
		newEmployeeDto.setId(employeeDto2.getId());
		newEmployeeDto.setName("DEF");		
		((PermanentEmployeeDto)newEmployeeDto).setLeaves(20);
		((PermanentEmployeeDto)newEmployeeDto).setSalary(1000000);
		
		String json2 = gson.toJson(newEmployeeDto);
		json2= json2.replace("{", "");
		json2= json2.replace("}", "");
		json2 = "{\"type\":\"permanentEmployee\","+json2+"}";
		MockHttpServletRequestBuilder requestBuilder3 = MockMvcRequestBuilders.post("/classtableinheritance/edit");
		requestBuilder3.contentType(MediaType.APPLICATION_JSON);
		requestBuilder3.content(json2.getBytes());
		this.mockMvc.perform(requestBuilder3).andExpect(MockMvcResultMatchers.status().isOk());
	}

	public void testDelete() throws Exception {
		
		MockHttpServletRequestBuilder requestBuilder2 = MockMvcRequestBuilders.get("/classtableinheritance/findAll");
		MvcResult result = this.mockMvc.perform(requestBuilder2).andReturn();
		String response2 = result.getResponse().getContentAsString();
		
		Type listType = new TypeToken<EmployeeDto[]>() {}.getType();
		EmployeeDto[] employeeDtoList = gson.fromJson(response2, listType);
		EmployeeDto employeeDto2 = employeeDtoList[0];		
		MockHttpServletRequestBuilder requestBuilder3 = MockMvcRequestBuilders.post("/classtableinheritance/remove/"+employeeDto2.getId());
		requestBuilder3.contentType(MediaType.APPLICATION_JSON);
		this.mockMvc.perform(requestBuilder3).andExpect(MockMvcResultMatchers.status().is(204));
	}
}