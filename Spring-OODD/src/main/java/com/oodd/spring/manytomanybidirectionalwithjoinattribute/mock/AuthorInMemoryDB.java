package com.oodd.spring.manytomanybidirectionalwithjoinattribute.mock;

import java.util.ArrayList;
import java.util.List;

import com.oodd.spring.manytomanybidirectionalwithjoinattribute.dto.AuthorDto;
import com.oodd.spring.manytomanybidirectionalwithjoinattribute.dto.ManuscriptAuthorDto;



public enum AuthorInMemoryDB {

	INSTANCE;
	
	private static List<AuthorDto> list = new ArrayList<AuthorDto>();
	private static Integer lastId = 0;
	
	public Integer getId() {
		return ++lastId;
	}
	
	public void add(AuthorDto authorDto) {
		authorDto.setId(getId());
		list.add(authorDto);
	}

	public void edit(AuthorDto authorDto) {
		for (AuthorDto dto:list) {
			if (dto.getId()==authorDto.getId()) {
				dto.setName(authorDto.getName());
				
				List<ManuscriptAuthorDto> manuscriptAuthorList = ManuscriptAuthorInMemoryDB.INSTANCE.findAll();
				List<ManuscriptAuthorDto> modifiedClientAuthorList = new ArrayList<ManuscriptAuthorDto>();
				for(ManuscriptAuthorDto manuscriptAuthorDto : manuscriptAuthorList) {
					if(dto.getId() == manuscriptAuthorDto.getAuthorDto().getId()) {
						manuscriptAuthorDto.getAuthorDto().setName(authorDto.getName());
					}
					modifiedClientAuthorList.add(manuscriptAuthorDto);
				}
				ManuscriptAuthorInMemoryDB.INSTANCE.setList(modifiedClientAuthorList);
			}
		}
	}
	
	public void remove(Integer id) {
		AuthorDto toRemove = null;
		for (AuthorDto dto:list) {
			if (dto.getId()==id) toRemove = dto;
		}
		if (toRemove!=null) {
			list.remove(toRemove);
		}
		if (toRemove!=null) {
			list.remove(toRemove);
			List<ManuscriptAuthorDto> manuscriptAuthorList = ManuscriptAuthorInMemoryDB.INSTANCE.findAll();
			List<ManuscriptAuthorDto> modifiedClientAuthorList = new ArrayList<ManuscriptAuthorDto>();
			for(ManuscriptAuthorDto manuscriptAuthorDto : manuscriptAuthorList) {
				if(toRemove.getId() != manuscriptAuthorDto.getAuthorDto().getId()) {
					modifiedClientAuthorList.add(manuscriptAuthorDto);
				}
			}
			ManuscriptAuthorInMemoryDB.INSTANCE.setList(modifiedClientAuthorList);
		}
	}
	
	public List<AuthorDto> findAll() {
		return list;
	}
	
	public AuthorDto findById(Integer id) {
		for (AuthorDto dto:list) {
			if (dto.getId()==id)
				return dto;
		}
		return null;
	}
}
