package com.oodd.spring.manytomanyselfreferencewithjoinattribute.mock;

import java.util.ArrayList;
import java.util.List;

import com.oodd.spring.manytomanyselfreferencewithjoinattribute.dto.WorkerWorkerDto;

public enum WorkerWorkerInMemoryDB {

	INSTANCE;
	
	private static List<WorkerWorkerDto> list = new ArrayList<WorkerWorkerDto>();
	
	public void add(WorkerWorkerDto workerWorkerDto) {
		list.add(workerWorkerDto);
	}

	public boolean isPresent(WorkerWorkerDto workerWorkerDto) {
		for (WorkerWorkerDto dto:list) {
			if (dto.getWorkerId1().getId() == workerWorkerDto.getWorkerId1().getId()
					&& dto.getWorkerId2().getId() == workerWorkerDto.getWorkerId2().getId()) {
				return true;
			}  else if (dto.getWorkerId1().getId() == workerWorkerDto.getWorkerId2().getId()
					&& dto.getWorkerId2().getId() == workerWorkerDto.getWorkerId1().getId()) {
				return true;
			}
			
		}
		return false;
	}
	
	public void remove(WorkerWorkerDto workerWorkerDto) {
		WorkerWorkerDto toRemove = null;
		for (WorkerWorkerDto dto:list) {
			if (dto.getWorkerId1().getId()==workerWorkerDto.getWorkerId1().getId()
					&& dto.getWorkerId2().getId() == workerWorkerDto.getWorkerId2().getId()) {
				toRemove = dto;
			}
		}			
		if (toRemove!=null) list.remove(toRemove);
	}
	
	public List<WorkerWorkerDto> findAll() {
		return list;
	}
	public void setList(List<WorkerWorkerDto> list) {
		WorkerWorkerInMemoryDB.list = list;
	}
}