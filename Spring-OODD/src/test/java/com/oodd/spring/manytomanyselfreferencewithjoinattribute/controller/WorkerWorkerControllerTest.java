package com.oodd.spring.manytomanyselfreferencewithjoinattribute.controller;

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
import com.oodd.spring.manytomanyselfreferencewithjoinattribute.dto.WorkerDto;
import com.oodd.spring.manytomanyselfreferencewithjoinattribute.dto.WorkerWorkerDto;
import com.oodd.spring.manytomanyselfreferencewithjoinattribute.service.WorkerService;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration( locations = { "classpath:context.xml" } )
@TransactionConfiguration(defaultRollback = true)
@Transactional
public class WorkerWorkerControllerTest {
	private Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd hh:mm:ss").create();

	@Autowired
	private WorkerService workerService;
	
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
		WorkerWorkerDto workerWorkerDto = new WorkerWorkerDto();
		
		WorkerDto workerDto1 = new WorkerDto();
		workerDto1.setName("Amritendu De");
		workerService.create(workerDto1);
		
		WorkerDto workerDto2 = new WorkerDto();
		workerDto2.setName("Amish Tripathi");
		workerService.create(workerDto2);
		
		List<WorkerDto> workerDtos1 = workerService.findAll();
		WorkerDto workerDto3 = workerDtos1.get(0);
		
		List<WorkerDto> workerDtos2 = workerService.findAll();
		WorkerDto workerDto4 = workerDtos2.get(0);
		
		workerWorkerDto.setWorkerId1(workerDto3);
		workerWorkerDto.setWorkerId2(workerDto4);
		workerWorkerDto.setRelationshipType("Authors");
				
		String json = gson.toJson(workerWorkerDto);
		
		MockHttpServletRequestBuilder requestBuilderOne = MockMvcRequestBuilders.post("/manytomanyselfreferencewithjoinattribute/workerworker/create");
		requestBuilderOne.contentType(MediaType.APPLICATION_JSON);
		requestBuilderOne.content(json.getBytes());
		this.mockMvc.perform(requestBuilderOne).andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	public void testPresent() throws Exception {
		WorkerWorkerDto workerWorkerDto = new WorkerWorkerDto();
		
		List<WorkerDto> workerDtos1 = workerService.findAll();
		WorkerDto workerDto3 = workerDtos1.get(0);
		
		List<WorkerDto> workerDtos2 = workerService.findAll();
		WorkerDto workerDto4 = workerDtos2.get(0);
		
		workerWorkerDto.setWorkerId1(workerDto3);
		workerWorkerDto.setWorkerId2(workerDto4);
		workerWorkerDto.setRelationshipType("Authors");
				
		String json = gson.toJson(workerWorkerDto);
		
		MockHttpServletRequestBuilder requestBuilderOne = MockMvcRequestBuilders.post("/manytomanyselfreferencewithjoinattribute/workerworker/isPresent");
		requestBuilderOne.contentType(MediaType.APPLICATION_JSON);
		requestBuilderOne.content(json.getBytes());
		this.mockMvc.perform(requestBuilderOne).andExpect(MockMvcResultMatchers.status().isOk());
	}	
	
	public void testDelete() throws Exception {			
		WorkerWorkerDto workerWorkerDto = new WorkerWorkerDto();
		
		List<WorkerDto> workerDtos1 = workerService.findAll();
		WorkerDto workerDto3 = workerDtos1.get(0);
		
		List<WorkerDto> workerDtos2 = workerService.findAll();
		WorkerDto workerDto4 = workerDtos2.get(0);
		
		workerWorkerDto.setWorkerId1(workerDto3);
		workerWorkerDto.setWorkerId2(workerDto4);
		workerWorkerDto.setRelationshipType("Authors");
				
		String json = gson.toJson(workerWorkerDto);
		
		MockHttpServletRequestBuilder requestBuilder2 = MockMvcRequestBuilders.post("/manytomanyselfreferencewithjoinattribute/workerworker/remove");
		requestBuilder2.contentType(MediaType.APPLICATION_JSON);
		requestBuilder2.content(json.getBytes());
		this.mockMvc.perform(requestBuilder2).andExpect(MockMvcResultMatchers.status().is(204));
	}
}