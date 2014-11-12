package com.oodd.spring.onetoonebidirectional.service;

import java.util.List;

import com.oodd.spring.onetoonebidirectional.dto.CustomerDto;

public interface CustomerService {
	public void create(CustomerDto customerDto);
	public List<CustomerDto> findAll();
	public CustomerDto findById(int id) ;
	public void remove(int id);
	public void edit(CustomerDto customerDto);
}
