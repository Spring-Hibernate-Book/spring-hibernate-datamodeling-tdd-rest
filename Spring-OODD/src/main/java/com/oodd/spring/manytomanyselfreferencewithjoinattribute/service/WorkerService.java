package com.oodd.spring.manytomanyselfreferencewithjoinattribute.service;

import java.util.List;

import com.oodd.spring.manytomanyselfreferencewithjoinattribute.dto.WorkerDto;

public interface WorkerService {
	public void create(WorkerDto manuscriptDto) ;
	public List<WorkerDto> findAll();
	public WorkerDto findById(Integer id);
	public void remove(Integer id);
	public void edit(WorkerDto manuscriptDto);
}
