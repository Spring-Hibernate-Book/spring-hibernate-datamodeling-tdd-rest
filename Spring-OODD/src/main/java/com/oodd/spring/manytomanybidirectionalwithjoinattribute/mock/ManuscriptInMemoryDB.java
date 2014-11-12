package com.oodd.spring.manytomanybidirectionalwithjoinattribute.mock;

import java.util.ArrayList;
import java.util.List;

import com.oodd.spring.manytomanybidirectionalwithjoinattribute.dto.ManuscriptAuthorDto;
import com.oodd.spring.manytomanybidirectionalwithjoinattribute.dto.ManuscriptDto;

public enum ManuscriptInMemoryDB {

	INSTANCE;
	
	private static List<ManuscriptDto> list = new ArrayList<ManuscriptDto>();
	private static Integer lastId = 0;
	
	public Integer getId() {
		return ++lastId;
	}
	
	public void add(ManuscriptDto manuscriptDto) {
		manuscriptDto.setId(getId());
		list.add(manuscriptDto);
	}

	public void edit(ManuscriptDto manuscriptDto) {
		for (ManuscriptDto dto:list) {
			if (dto.getId()==manuscriptDto.getId()) {
				dto.setName(manuscriptDto.getName());
				
				List<ManuscriptAuthorDto> manuscriptAuthorList = ManuscriptAuthorInMemoryDB.INSTANCE.findAll();
				List<ManuscriptAuthorDto> modifiedManuscriptAuthorList = new ArrayList<ManuscriptAuthorDto>();
				for(ManuscriptAuthorDto manuscriptAuthorDto : manuscriptAuthorList) {
					if(dto.getId() == manuscriptAuthorDto.getManuscriptDto().getId()) {
						manuscriptAuthorDto.getManuscriptDto().setName(manuscriptDto.getName());
					}
					modifiedManuscriptAuthorList.add(manuscriptAuthorDto);
				}
				ManuscriptAuthorInMemoryDB.INSTANCE.setList(modifiedManuscriptAuthorList);
			}
		}
	}
	
	public void remove(Integer id) {
		ManuscriptDto toRemove = null;
		for (ManuscriptDto dto : list) {
			if (dto.getId()==id) {
				toRemove = dto;
			}
		}			
		if (toRemove!=null) {
			list.remove(toRemove);
			List<ManuscriptAuthorDto> manuscriptAuthorList = ManuscriptAuthorInMemoryDB.INSTANCE.findAll();
			List<ManuscriptAuthorDto> modifiedManuscriptAuthorList = new ArrayList<ManuscriptAuthorDto>();
			for(ManuscriptAuthorDto manuscriptAuthorDto : manuscriptAuthorList) {
				if(toRemove.getId() != manuscriptAuthorDto.getManuscriptDto().getId()) {
					modifiedManuscriptAuthorList.add(manuscriptAuthorDto);
				}
			}
			ManuscriptAuthorInMemoryDB.INSTANCE.setList(modifiedManuscriptAuthorList);
		}
	}
	
	public List<ManuscriptDto> findAll() {
		return list;
	}
	
	public ManuscriptDto findById(Integer id) {
		for (ManuscriptDto dto:list) {
			if (dto.getId()==id)
				return dto;
		}
		return null;
	}	
}