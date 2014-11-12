package com.oodd.spring.manytomanybidirectionalwithjoinattribute.service;

import java.util.List;

import com.oodd.spring.manytomanybidirectionalwithjoinattribute.dto.ManuscriptDto;

public interface ManuscriptService {
	public void create(ManuscriptDto manuscriptDto) ;
	public List<ManuscriptDto> findAll();
	public ManuscriptDto findById(Integer id);
	public void remove(Integer id);
	public void edit(ManuscriptDto manuscriptDto);
}
