package com.oodd.spring.manytomanyselfreferencewithjoinattribute.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oodd.spring.manytomanyselfreferencewithjoinattribute.dao.WorkerDao;
import com.oodd.spring.manytomanyselfreferencewithjoinattribute.dto.WorkerDto;
import com.oodd.spring.manytomanyselfreferencewithjoinattribute.entity.Worker;
import com.oodd.spring.manytomanyselfreferencewithjoinattribute.mapper.WorkerMapper;
import com.oodd.spring.manytomanyselfreferencewithjoinattribute.service.WorkerService;

@Service
@Transactional
public class WorkerServiceImpl implements WorkerService {

	@Autowired
	private WorkerDao workerDao;
	
	@Autowired
	private WorkerMapper workerMapper;
	
	
	@Override
	public void create(WorkerDto workerDto) {
		Worker worker = workerMapper.mapDtoToEntity(workerDto);
		workerDao.insert(worker);
	}

	@Override
	public List<WorkerDto> findAll() {
		List<Worker> workers = workerDao.getAll();
		List<WorkerDto> workerDtos = new ArrayList<WorkerDto>();
		for(Worker worker : workers) {
			workerDtos.add(workerMapper.mapEntityToDto(worker));
		}
		return workerDtos;
	}

	@Override
	public WorkerDto findById(Integer id) {
		Worker worker = workerDao.getById(id);
		if(null != worker) {			
			return workerMapper.mapEntityToDto(worker);
		}
		return null;
	}

	@Override
	public void remove(Integer id) {
		Worker worker = workerDao.getById(id);
		workerDao.delete(worker);
	}

	@Override
	public void edit(WorkerDto workerDto) {
		Worker workerCurrent = workerDao.getById(workerDto.getId());
		Worker workerToBeEdited = workerMapper.mapDtoToEntity(workerDto);
		workerToBeEdited.setWorkers1(workerCurrent.getWorkers1());
		workerDao.update(workerToBeEdited);
	}
}