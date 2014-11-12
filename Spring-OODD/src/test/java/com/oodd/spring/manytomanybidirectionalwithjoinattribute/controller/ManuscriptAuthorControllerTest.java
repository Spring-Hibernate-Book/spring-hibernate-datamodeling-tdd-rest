package com.oodd.spring.manytomanybidirectionalwithjoinattribute.controller;

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
import com.oodd.spring.manytomanybidirectionalwithjoinattribute.dto.AuthorDto;
import com.oodd.spring.manytomanybidirectionalwithjoinattribute.dto.ManuscriptAuthorDto;
import com.oodd.spring.manytomanybidirectionalwithjoinattribute.dto.ManuscriptDto;
import com.oodd.spring.manytomanybidirectionalwithjoinattribute.service.AuthorService;
import com.oodd.spring.manytomanybidirectionalwithjoinattribute.service.ManuscriptService;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration( locations = { "classpath:context.xml" } )
@TransactionConfiguration(defaultRollback = true)
@Transactional
public class ManuscriptAuthorControllerTest {
	private Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd hh:mm:ss").create();

	@Autowired
	private ManuscriptService manuscriptService;
	
	@Autowired
	private AuthorService authorService;
	
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
		ManuscriptAuthorDto manuscriptAuthorDto = new ManuscriptAuthorDto();
		
		ManuscriptDto manuscriptDto = new ManuscriptDto();
		manuscriptDto.setName("The Immortals of Meluha");
		manuscriptService.create(manuscriptDto);
		
		AuthorDto authorDto = new AuthorDto();
		authorDto.setName("Amish Tripathi");
		authorService.create(authorDto);
		
		List<ManuscriptDto> manuscriptDtos = manuscriptService.findAll();
		ManuscriptDto manuscriptDto1 = manuscriptDtos.get(0);
		
		List<AuthorDto> authorDtos = authorService.findAll();
		AuthorDto authorDto1 = authorDtos.get(0);
		
		manuscriptAuthorDto.setManuscriptDto(manuscriptDto1);
		manuscriptAuthorDto.setAuthorDto(authorDto1);
		manuscriptAuthorDto.setPublisher("Createspace");
				
		String json = gson.toJson(manuscriptAuthorDto);
		
		MockHttpServletRequestBuilder requestBuilderOne = MockMvcRequestBuilders.post("/manytomanybidirectionalwithjoinattribute/manuscriptauthor/create");
		requestBuilderOne.contentType(MediaType.APPLICATION_JSON);
		requestBuilderOne.content(json.getBytes());
		this.mockMvc.perform(requestBuilderOne).andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	public void testPresent() throws Exception {
		ManuscriptAuthorDto manuscriptAuthorDto = new ManuscriptAuthorDto();
		
		ManuscriptDto manuscriptDto = new ManuscriptDto();
		manuscriptDto.setName("The Immortals of Meluha");
		manuscriptService.create(manuscriptDto);
		
		AuthorDto authorDto = new AuthorDto();
		authorDto.setName("Amish Tripathi");
		authorService.create(authorDto);
		
		List<ManuscriptDto> manuscriptDtos = manuscriptService.findAll();
		ManuscriptDto manuscriptDto1 = manuscriptDtos.get(0);
		
		List<AuthorDto> authorDtos = authorService.findAll();
		AuthorDto authorDto1 = authorDtos.get(0);
		
		manuscriptAuthorDto.setManuscriptDto(manuscriptDto1);
		manuscriptAuthorDto.setAuthorDto(authorDto1);
		manuscriptAuthorDto.setPublisher("Createspace");
		
		String json = gson.toJson(manuscriptAuthorDto);
		
		MockHttpServletRequestBuilder requestBuilderOne = MockMvcRequestBuilders.post("/manytomanybidirectionalwithjoinattribute/manuscriptauthor/isPresent");
		requestBuilderOne.contentType(MediaType.APPLICATION_JSON);
		requestBuilderOne.content(json.getBytes());
		this.mockMvc.perform(requestBuilderOne).andExpect(MockMvcResultMatchers.status().isOk());
	}	
	
	public void testDelete() throws Exception {			
		ManuscriptAuthorDto manuscriptAuthorDto = new ManuscriptAuthorDto();
		
		ManuscriptDto manuscriptDto = new ManuscriptDto();
		manuscriptDto.setName("The Immortals of Meluha");
		manuscriptService.create(manuscriptDto);
		
		AuthorDto authorDto = new AuthorDto();
		authorDto.setName("Amish Tripathi");
		authorService.create(authorDto);
		
		List<ManuscriptDto> manuscriptDtos = manuscriptService.findAll();
		ManuscriptDto manuscriptDto1 = manuscriptDtos.get(0);
		
		List<AuthorDto> authorDtos = authorService.findAll();
		AuthorDto authorDto1 = authorDtos.get(0);
		
		manuscriptAuthorDto.setManuscriptDto(manuscriptDto1);
		manuscriptAuthorDto.setAuthorDto(authorDto1);
		manuscriptAuthorDto.setPublisher("Createspace");
		
		String json = gson.toJson(manuscriptAuthorDto);
		
		MockHttpServletRequestBuilder requestBuilder2 = MockMvcRequestBuilders.post("/manytomanybidirectionalwithjoinattribute/manuscriptauthor/remove");
		requestBuilder2.contentType(MediaType.APPLICATION_JSON);
		requestBuilder2.content(json.getBytes());
		this.mockMvc.perform(requestBuilder2).andExpect(MockMvcResultMatchers.status().is(204));
	}
}