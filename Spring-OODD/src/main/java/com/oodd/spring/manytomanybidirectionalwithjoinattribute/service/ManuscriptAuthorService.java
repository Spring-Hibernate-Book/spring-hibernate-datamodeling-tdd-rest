package com.oodd.spring.manytomanybidirectionalwithjoinattribute.service;

import java.util.List;

import com.oodd.spring.manytomanybidirectionalwithjoinattribute.dto.ManuscriptAuthorDto;

public interface ManuscriptAuthorService {
	public boolean isPresent(ManuscriptAuthorDto manuscriptAuthorDto);
	public List<ManuscriptAuthorDto> findAll();
	public void create(ManuscriptAuthorDto manuscriptAuthorDto);
	public void remove(ManuscriptAuthorDto manuscriptAuthorDto);
}
