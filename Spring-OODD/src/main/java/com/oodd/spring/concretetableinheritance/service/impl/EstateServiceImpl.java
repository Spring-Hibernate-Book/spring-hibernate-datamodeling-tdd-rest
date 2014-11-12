package com.oodd.spring.concretetableinheritance.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oodd.spring.concretetableinheritance.dao.EstateDao;
import com.oodd.spring.concretetableinheritance.dto.EstateDto;
import com.oodd.spring.concretetableinheritance.entity.Estate;
import com.oodd.spring.concretetableinheritance.mapper.EstateMapper;
import com.oodd.spring.concretetableinheritance.service.EstateService;

@Service
@Transactional
public class EstateServiceImpl implements EstateService {

	@Autowired
	private EstateDao estateDao;
	
	@Autowired
	private EstateMapper estateMapper;
	@Override
	public void create(EstateDto estateDto) {		
		estateDao.insert(estateMapper.mapDtoToEntity(estateDto));
	}
	@Override
	public List<EstateDto> findAll() {
		List<Estate> estates = estateDao.getAll();
		List<EstateDto> estateDtos = new ArrayList<EstateDto>();
		for(Estate estate : estates){
			estateDtos.add(estateMapper.mapEntityToDto(estate));
		}
		return estateDtos;
	}

	@Override
	public EstateDto findById(Integer id) {
		Estate estate = estateDao.getById(id);
		EstateDto estateDto = null;
		if(null !=estate){
			estateDto = estateMapper.mapEntityToDto(estate);
		}
		return estateDto;
	}

	@Override
	public void remove(Integer estateId) {
		Estate estate = estateDao.getById(estateId);
		estateDao.delete(estate);
	}

	@Override
	public void edit(EstateDto estateDto) {
		estateDao.update(estateMapper.mapDtoToEntity(estateDto));
	}
}