package com.oodd.spring.manytomanyselfreferencewithjoinattribute.mapper;

import org.springframework.stereotype.Component;

import com.oodd.spring.manytomanyselfreferencewithjoinattribute.dto.WorkerDto;
import com.oodd.spring.manytomanyselfreferencewithjoinattribute.entity.Worker;

@Component
public class WorkerMapper {

	public Worker mapDtoToEntity(WorkerDto workerDto) {
		Worker worker = new Worker();
		if(null != workerDto.getId()) worker.setId(workerDto.getId());
		if(null != workerDto.getName()) worker.setName(workerDto.getName());
		return worker;
	}
	
	public WorkerDto mapEntityToDto(Worker worker) {
		WorkerDto workerDto = new WorkerDto();
		if(null != worker.getId()) workerDto.setId(worker.getId());
		if(null != worker.getName()) workerDto.setName(worker.getName());
		return workerDto;
	}
}