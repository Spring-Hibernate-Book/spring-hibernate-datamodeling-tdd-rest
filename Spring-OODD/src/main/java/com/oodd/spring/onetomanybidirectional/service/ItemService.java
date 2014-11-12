package com.oodd.spring.onetomanybidirectional.service;

import java.util.List;

import com.oodd.spring.onetomanybidirectional.dto.ItemDto;

public interface ItemService {
	public void create(ItemDto itemDto);
	public List<ItemDto> findAll();
	public ItemDto findById(Integer id);
	public void remove(Integer id);
	public void edit(ItemDto itemDto);
}
