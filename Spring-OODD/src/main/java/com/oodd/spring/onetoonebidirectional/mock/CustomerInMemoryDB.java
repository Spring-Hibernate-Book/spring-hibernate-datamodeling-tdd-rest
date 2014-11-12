package com.oodd.spring.onetoonebidirectional.mock;

import java.util.ArrayList;
import java.util.List;

import com.oodd.spring.onetoonebidirectional.dto.CustomerDto;

public enum CustomerInMemoryDB {
	INSTANCE;
	private static Integer lastId = 0;
	private static List<CustomerDto> list = new ArrayList<CustomerDto>();
	public Integer getId() {
		return ++lastId;
	}
	public void add(CustomerDto customerDto){
		customerDto.setId(getId());
		list.add(customerDto);
	}
	public void edit(CustomerDto customerDto){
		for(CustomerDto dto : list){
			if(dto.getId() == customerDto.getId()){
				dto.setName(customerDto.getName());
				dto.setAmount(customerDto.getAmount());
			}
		}
	}
	public void remove(Integer id) {
		CustomerDto dto = null;
		for(CustomerDto customerDto : list){
			if(customerDto.getId() == id){dto = customerDto;break;}
		}
		if(null !=dto){list.remove(dto);}
	}
	public List<CustomerDto> findAll(){
		return list;
	}
	public CustomerDto findById(Integer id) {
		for(CustomerDto customerDto : list){
			if(customerDto.getId() == id) return customerDto ;
		}
		return null;
	}
}
