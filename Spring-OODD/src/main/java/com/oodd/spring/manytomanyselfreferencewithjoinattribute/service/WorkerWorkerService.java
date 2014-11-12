package com.oodd.spring.manytomanyselfreferencewithjoinattribute.service;

import java.util.List;

import com.oodd.spring.manytomanyselfreferencewithjoinattribute.dto.WorkerWorkerDto;

public interface WorkerWorkerService {
	public boolean isPresent(WorkerWorkerDto workerWorkerDto);
	public List<WorkerWorkerDto> findAll();
	public void create(WorkerWorkerDto workerWorkerDto);
	public void remove(WorkerWorkerDto workerWorkerDto);
}