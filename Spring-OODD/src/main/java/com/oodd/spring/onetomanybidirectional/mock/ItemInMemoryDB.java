package com.oodd.spring.onetomanybidirectional.mock;

import java.util.ArrayList;
import java.util.List;

import com.oodd.spring.onetomanybidirectional.dto.ItemDto;

public enum ItemInMemoryDB {
	INSTANCE;
	private static List<ItemDto> list = new ArrayList<ItemDto>();
	private static Integer lastId = 0;
	public Integer getId() {
		return ++lastId;
	}
	public void add(ItemDto itemDto){
		itemDto.setId(getId());
		list.add(itemDto);
	}
	public void edit(ItemDto itemDto){
		for(ItemDto dto : list){
			if(dto.getId() == itemDto.getId()){
				dto.setName(itemDto.getName());
				dto.setFeatureList(itemDto.getFeatureList());
			}
		}
	}
	public void remove(Integer id) {
		ItemDto dto = null;
		for(ItemDto itemDto : list){
			if(itemDto.getId() == id){
				dto = itemDto;
				break;
			}
		}
		if(null != dto){list.remove(dto);}
	}
	public List<ItemDto> findAll(){
		return list;
	}
	public ItemDto findById(Integer id) {
		for(ItemDto itemDto : list){
			if(itemDto.getId() == id) 
				return itemDto ;
		}
		return null;
	}
}
