package com.oodd.spring.concretetableinheritance.mock;

import java.util.ArrayList;
import java.util.List;

import com.oodd.spring.concretetableinheritance.dto.BuildingDto;
import com.oodd.spring.concretetableinheritance.dto.EstateDto;
import com.oodd.spring.concretetableinheritance.dto.LandDto;

public enum EstateInMemoryDB {

	INSTANCE;
	
	private static List<EstateDto> list = new ArrayList<EstateDto>();
	private static Integer lastId = 0;
	
	public Integer getId() {
		return ++lastId;
	}
	
	public void add(EstateDto estateDto) {
		estateDto.setId(getId());
		list.add(estateDto);
	}

	public void edit(EstateDto estateDto) {
		for (EstateDto dto:list) {
			if (dto.getId()==estateDto.getId()) {
				dto.setName(estateDto.getName());
				if(estateDto instanceof BuildingDto) {
					((BuildingDto)dto).setFloors(((BuildingDto)estateDto).getFloors());
				} else if(estateDto instanceof LandDto) {
					((LandDto)dto).setArea(((LandDto)estateDto).getArea());
				}
			}
		}
	}
	
	public void remove(Integer id) {
		EstateDto toRemove = null;
		for (EstateDto dto:list) 
			if (dto.getId()==id) toRemove = dto;
		if (toRemove!=null) list.remove(toRemove);
	}
	
	public List<EstateDto> findAll() {
		return list;
	}
	
	public EstateDto findById(Integer id) {
		for (EstateDto dto:list) {
			if (dto.getId()==id)
				return dto;
		}
		return null;
	}
}
