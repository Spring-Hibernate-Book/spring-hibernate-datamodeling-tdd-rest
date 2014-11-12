package com.oodd.spring.manytomanyselfreferencewithjoinattribute.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oodd.spring.manytomanyselfreferencewithjoinattribute.dao.WorkerDao;
import com.oodd.spring.manytomanyselfreferencewithjoinattribute.dao.WorkerWorkerDao;
import com.oodd.spring.manytomanyselfreferencewithjoinattribute.dto.WorkerWorkerDto;
import com.oodd.spring.manytomanyselfreferencewithjoinattribute.entity.Worker;
import com.oodd.spring.manytomanyselfreferencewithjoinattribute.entity.WorkerWorker;
import com.oodd.spring.manytomanyselfreferencewithjoinattribute.entity.WorkerWorkerId;
import com.oodd.spring.manytomanyselfreferencewithjoinattribute.mapper.WorkerMapper;
import com.oodd.spring.manytomanyselfreferencewithjoinattribute.service.WorkerWorkerService;

@Service
@Transactional
public class WorkerWorkerServiceImpl implements WorkerWorkerService {

	@Autowired
	private WorkerDao workerDao;
	
	@Autowired
	private WorkerMapper workerMapper;
	
	@Autowired
	private WorkerWorkerDao workerWorkerDao;
	
	
	@Override
	public boolean isPresent(WorkerWorkerDto workerWorkerDto) {
		boolean status = false;
		List<WorkerWorker> workerWorkerList = workerWorkerDao.isPresent(workerWorkerDto.getWorkerId1().getId(), workerWorkerDto.getWorkerId2().getId());		
		if(workerWorkerList.size() > 0) {
			status = true;
		} else {
			workerWorkerList = workerWorkerDao.isPresent(workerWorkerDto.getWorkerId2().getId(), workerWorkerDto.getWorkerId1().getId());
			if(workerWorkerList.size() > 0) {
				status = true;
			}
		}
		
		return status;
	}

	@Override
	public List<WorkerWorkerDto> findAll() {
		List<WorkerWorkerDto> workerWorkerDtos = new ArrayList<WorkerWorkerDto>();
		List<WorkerWorker> workerList = workerWorkerDao.getAll();
		for(WorkerWorker workerWorker : workerList) {	
			WorkerWorkerDto workerWorkerDto = new WorkerWorkerDto();	
			workerWorkerDto.setWorkerId1(workerMapper.mapEntityToDto(workerWorker.getWorker1()));
			workerWorkerDto.setWorkerId2(workerMapper.mapEntityToDto(workerWorker.getWorker2()));
			workerWorkerDto.setRelationshipType(workerWorker.getRelationshipType());
			workerWorkerDtos.add(workerWorkerDto);
		}
		return workerWorkerDtos;
	}

	@Override
	public void create(WorkerWorkerDto workerWorkerDto) {
		Integer workerId1 = workerWorkerDto.getWorkerId1().getId();
		Integer workerId2 = workerWorkerDto.getWorkerId2().getId();
		
		Worker worker1 = workerDao.getById(workerId1);
		Worker worker2 = workerDao.getById(workerId2);
		
		WorkerWorkerId workerWorkerId = new WorkerWorkerId();
		WorkerWorker workerWorker = new WorkerWorker();
		workerWorkerId.setWorker1(worker1);
		workerWorkerId.setWorker2(worker2);
		workerWorker.setWorkerWorkerId(workerWorkerId);
		workerWorker.setRelationshipType(workerWorkerDto.getRelationshipType());
		
		workerWorkerDao.insert(workerWorker);
	}

	@Override
	public void remove(WorkerWorkerDto workerWorkerDto) {
		Integer workerId1 = workerWorkerDto.getWorkerId1().getId();
		Integer workerId2 = workerWorkerDto.getWorkerId2().getId();
		
		Worker worker1 = workerDao.getById(workerId1);
		Worker worker2 = workerDao.getById(workerId2);
		
		List<WorkerWorker> workerWorkerList = workerWorkerDao.isPresent(workerId1, workerId2);
		for(WorkerWorker workerWorker : workerWorkerList) {
			worker1.getWorkers1().remove(workerWorker);
			worker2.getWorkers1().remove(workerWorker);
			workerWorkerDao.delete(workerWorker);
		}	
		workerDao.update(worker1);
		workerDao.update(worker2);
	}
}