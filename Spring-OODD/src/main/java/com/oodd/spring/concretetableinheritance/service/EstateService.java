package com.oodd.spring.concretetableinheritance.service;

import java.util.List;

import com.oodd.spring.concretetableinheritance.dto.EstateDto;

public interface EstateService {
	public void create(EstateDto estateDto ) ;
	public List<EstateDto> findAll();
	public EstateDto findById(Integer id);
	public void remove(Integer estateId);
	public void edit(EstateDto estateDto);
}