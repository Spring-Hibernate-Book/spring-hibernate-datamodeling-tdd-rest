package com.oodd.spring.onetomanybidirectional.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oodd.spring.onetomanybidirectional.dao.ItemDao;
import com.oodd.spring.onetomanybidirectional.dto.ItemDto;
import com.oodd.spring.onetomanybidirectional.entity.Item;
import com.oodd.spring.onetomanybidirectional.mapper.ItemMapper;
import com.oodd.spring.onetomanybidirectional.service.ItemService;
@Service
@Transactional
public class ItemServiceImpl implements ItemService{
	@Autowired
	private ItemDao dao;
	@Autowired
	private ItemMapper mapper;
	@Override
	public void create(ItemDto itemDto) {
		dao.insert(mapper.mapDtoToEntity(itemDto));
	}
	@Override
	public List<ItemDto> findAll() {
		List<Item> items = dao.getAll();
		List<ItemDto> itemDtos = new ArrayList<ItemDto>();
		if(null !=items){
			for (Item item : items) {
				itemDtos.add(mapper.mapEntityToDto(item));
			}
		}
		return itemDtos;
	}
	@Override
	public ItemDto findById(Integer id) {
		Item item = dao.getById(id);
		if(null !=item){
			return mapper.mapEntityToDto(item);
		}
		return null;
	}
	@Override
	public void remove(Integer id) {
		Item item = dao.getById(id);
		if(null != item){
			dao.delete(item);
		}
	}
	@Override
	public void edit(ItemDto itemDto) {
		dao.update(mapper.mapDtoToEntity(itemDto));
	}
}
