package com.oodd.spring.onetomanyselfreference.mock;

import java.util.ArrayList;
import java.util.List;

import com.oodd.spring.onetomanyselfreference.dto.CategoryDto;
public enum CategoryInMemoryDB {
	INSTANCE;
	
	private static List<CategoryDto> list = new ArrayList<CategoryDto>();
	
	private static Integer lastId = 0;
	
	public Integer getId() {
		return ++lastId;
	}
	public void add(CategoryDto categoryDto){
		categoryDto.setId(getId());
		list.add(categoryDto);
	}
	public void edit(CategoryDto categoryDto){
		for(CategoryDto dto : list){
			if(categoryDto.getId() == dto.getId()){
				dto.setName(categoryDto.getName());
				dto.setParentId(categoryDto.getParentId());
				break;
			}
		}
	}
	public void remove(Integer id) {
		CategoryDto dto = null;
		for(CategoryDto categoryDto : list){
			if(categoryDto.getId() ==  id) {
				boolean flag = false;
				for(CategoryDto categoryDto2 : list){
					if (id==categoryDto2.getParentId())
						flag = true;
				}
				if (!flag) {
					dto = categoryDto; 
					break;
				}
			}
		}
		if(null != dto) list.remove(dto);
	}
	public List<CategoryDto> findAll(){ return list; }
	public CategoryDto findById(Integer id) {
		for(CategoryDto categoryDto : list){ 
			if(categoryDto.getId() == id) 
				return categoryDto;
		}
		return null ;
	}
}
