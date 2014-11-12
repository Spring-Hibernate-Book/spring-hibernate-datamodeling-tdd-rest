package com.oodd.spring.manytomanybidirectionalwithjoinattribute.mapper;

import org.springframework.stereotype.Component;

import com.oodd.spring.manytomanybidirectionalwithjoinattribute.dto.ManuscriptDto;
import com.oodd.spring.manytomanybidirectionalwithjoinattribute.entity.Manuscript;

@Component
public class ManuscriptMapper {

	public Manuscript mapDtoToEntity(ManuscriptDto manuscriptDto) {
		Manuscript manuscript = new Manuscript();
		if(null != manuscriptDto.getId()) manuscript.setId(manuscriptDto.getId());
		if(null != manuscriptDto.getName()) manuscript.setName(manuscriptDto.getName());
		return manuscript;
	}
	
	public ManuscriptDto mapEntityToDto(Manuscript manuscript) {
		ManuscriptDto manuscriptDto = new ManuscriptDto();
		if(null != manuscript.getId()) manuscriptDto.setId(manuscript.getId());
		if(null != manuscript.getName()) manuscriptDto.setName(manuscript.getName());
		return manuscriptDto;
	}
}