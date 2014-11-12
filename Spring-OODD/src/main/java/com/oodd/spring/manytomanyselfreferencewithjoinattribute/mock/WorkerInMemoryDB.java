package com.oodd.spring.manytomanyselfreferencewithjoinattribute.mock;

import java.util.ArrayList;
import java.util.List;

import com.oodd.spring.manytomanyselfreferencewithjoinattribute.dto.WorkerDto;
import com.oodd.spring.manytomanyselfreferencewithjoinattribute.dto.WorkerWorkerDto;



public enum WorkerInMemoryDB {

	INSTANCE;
	
	private static List<WorkerDto> list = new ArrayList<WorkerDto>();
	private static Integer lastId = 0;
	
	public Integer getId() {
		return ++lastId;
	}
	
	public void add(WorkerDto workerDto) {
		workerDto.setId(getId());
		list.add(workerDto);
	}

	public void edit(WorkerDto workerDto) {
		for (WorkerDto dto:list) {
			if (dto.getId()==workerDto.getId()) {
				dto.setName(workerDto.getName());
				
				List<WorkerWorkerDto> workerWorkerList = WorkerWorkerInMemoryDB.INSTANCE.findAll();
				List<WorkerWorkerDto> modifiedWorkerWorkerList = new ArrayList<WorkerWorkerDto>();
				for(WorkerWorkerDto workerWorkerDto : workerWorkerList) {
					if(dto.getId() == workerWorkerDto.getWorkerId1().getId()) {
						workerWorkerDto.getWorkerId1().setName(workerDto.getName());
					} 
					if(dto.getId() == workerWorkerDto.getWorkerId2().getId()) {
						workerWorkerDto.getWorkerId2().setName(workerDto.getName());
					}
					modifiedWorkerWorkerList.add(workerWorkerDto);
				}
				WorkerWorkerInMemoryDB.INSTANCE.setList(modifiedWorkerWorkerList);
			}
		}
	}
	
	public void remove(Integer id) {
		WorkerDto toRemove = null;
		for (WorkerDto dto : list) {
			if (dto.getId()==id) {
				toRemove = dto;
			}
		}			
		if (toRemove!=null) {
			list.remove(toRemove);
			List<WorkerWorkerDto> workerWorkerList = WorkerWorkerInMemoryDB.INSTANCE.findAll();
			List<WorkerWorkerDto> modifiedWorkerWorkerList = new ArrayList<WorkerWorkerDto>();
			for(WorkerWorkerDto workerWorkerDto : workerWorkerList) {
				if(toRemove.getId() != workerWorkerDto.getWorkerId1().getId()
						&& toRemove.getId() != workerWorkerDto.getWorkerId2().getId()) {
					modifiedWorkerWorkerList.add(workerWorkerDto);
				}
			}
			WorkerWorkerInMemoryDB.INSTANCE.setList(modifiedWorkerWorkerList);
		}
	}
	
	public List<WorkerDto> findAll() {
		return list;
	}
	
	public WorkerDto findById(Integer id) {
		for (WorkerDto dto:list) {
			if (dto.getId()==id)
				return dto;
		}
		return null;
	}	
}