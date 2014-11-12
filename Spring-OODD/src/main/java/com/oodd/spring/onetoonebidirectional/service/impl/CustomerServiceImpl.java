package com.oodd.spring.onetoonebidirectional.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oodd.spring.onetoonebidirectional.dao.CustomerDao;
import com.oodd.spring.onetoonebidirectional.dto.CustomerDto;
import com.oodd.spring.onetoonebidirectional.entity.Customer;
import com.oodd.spring.onetoonebidirectional.mapper.CustomerMapper;
import com.oodd.spring.onetoonebidirectional.service.CustomerService;
@Service
@Transactional
public class CustomerServiceImpl implements CustomerService{
	@Autowired
	private CustomerDao dao ;
	@Autowired
	private CustomerMapper mapper ;
	@Override
	public void create(CustomerDto customerDto) {
		dao.insert(mapper.mapDtoToEntity(customerDto));
	}

	@Override
	public List<CustomerDto> findAll() {
		List<CustomerDto> customerDtos = new ArrayList<CustomerDto>();
		List<Customer> customers = dao.getAll();
		for(Customer customer:customers){
			customerDtos.add(mapper.mapEntityToDto(customer));
		}
		return customerDtos;
	}

	@Override
	public CustomerDto findById(int id) {
		Customer customer = dao.getById(id);
		if(null !=customer){
			return mapper.mapEntityToDto(customer);
		}
		return null;
	}

	@Override
	public void remove(int id) {
		dao.delete(dao.getById(id));
	}

	@Override
	public void edit(CustomerDto customerDto) {
		dao.update(mapper.mapDtoToEntity(customerDto));
	}

}
