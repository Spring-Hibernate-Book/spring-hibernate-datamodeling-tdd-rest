package com.oodd.spring.manytomanybidirectionalwithjoinattribute.mock;

import java.util.ArrayList;
import java.util.List;

import com.oodd.spring.manytomanybidirectionalwithjoinattribute.dto.ManuscriptAuthorDto;

public enum ManuscriptAuthorInMemoryDB {

	INSTANCE;
	
	private static List<ManuscriptAuthorDto> list = new ArrayList<ManuscriptAuthorDto>();
	
	public void add(ManuscriptAuthorDto manuscriptAuthorDto) {
		list.add(manuscriptAuthorDto);
	}

	public boolean isPresent(ManuscriptAuthorDto manuscriptAuthorDto) {
		for (ManuscriptAuthorDto dto:list) {
			if (dto.getManuscriptDto().getId() == manuscriptAuthorDto.getManuscriptDto().getId() 
					&& dto.getAuthorDto().getId() == manuscriptAuthorDto.getAuthorDto().getId()) {
				return true;
			}				
		}
		return false;
	}
	
	public void remove(ManuscriptAuthorDto manuscriptAuthorDto) {
		ManuscriptAuthorDto toRemove = null;
		for (ManuscriptAuthorDto dto:list) {
			if (dto.getManuscriptDto().getId()==manuscriptAuthorDto.getManuscriptDto().getId()
					&& dto.getAuthorDto().getId()==manuscriptAuthorDto.getAuthorDto().getId()) {
				toRemove = dto;
			}
		}			
		if (toRemove!=null) list.remove(toRemove);
	}
	
	public List<ManuscriptAuthorDto> findAll() {
		return list;
	}
	public void setList(List<ManuscriptAuthorDto> list) {
		ManuscriptAuthorInMemoryDB.list = list;
	}
}