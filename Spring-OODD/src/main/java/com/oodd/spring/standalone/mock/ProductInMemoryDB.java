package com.oodd.spring.standalone.mock;

import java.util.ArrayList;
import java.util.List;

import com.oodd.spring.standalone.dto.ProductDto;

public enum ProductInMemoryDB {

	INSTANCE;
	
	private static List<ProductDto> list = new ArrayList<ProductDto>();
	private static Integer lastId = 0;
	
	public Integer getId() {
		return ++lastId;
	}
	
	public void add(ProductDto productDto) {
		productDto.setId(getId());
		list.add(productDto);
	}

	public void edit(ProductDto productDto) {
		for (ProductDto dto:list) {
			if (dto.getId()==productDto.getId())
				dto.setName(productDto.getName());
		}
	}
	
	public void remove(Integer id) {
		ProductDto toRemove = null;
		for (ProductDto dto:list) 
			if (dto.getId()==id) toRemove = dto;
		if (toRemove!=null) list.remove(toRemove);
	}
	
	public List<ProductDto> findAll() {
		return list;
	}
	
	public ProductDto findById(Integer id) {
		for (ProductDto dto:list) {
			if (dto.getId()==id)
				return dto;
		}
		return null;
	}
}
