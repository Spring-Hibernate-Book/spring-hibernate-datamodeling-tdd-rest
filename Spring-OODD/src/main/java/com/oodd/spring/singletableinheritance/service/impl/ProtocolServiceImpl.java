package com.oodd.spring.singletableinheritance.service.impl;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oodd.spring.singletableinheritance.dao.ProtocolDao;
import com.oodd.spring.singletableinheritance.dto.ProtocolDto;
import com.oodd.spring.singletableinheritance.entity.Protocol;
import com.oodd.spring.singletableinheritance.mapper.ProtocolMapper;
import com.oodd.spring.singletableinheritance.service.ProtocolService;

@Service
@Transactional
public class ProtocolServiceImpl implements ProtocolService {
	@Autowired
	private ProtocolDao protocolDao;
	@Autowired
	private ProtocolMapper protocolMapper;
	@Override
	public void create(ProtocolDto protocolDto) {		
		protocolDao.insert(protocolMapper.mapDtoToEntity(protocolDto));
	}
	@Override
	public List<ProtocolDto> findAll() {
		List<Protocol> protocols = protocolDao.getAll();
		List<ProtocolDto> protocolDtos = new ArrayList<ProtocolDto>();
		for(Protocol protocol : protocols){
			protocolDtos.add(protocolMapper.mapEntityToDto(protocol));
		}
		return protocolDtos;
	}

	@Override
	public ProtocolDto findById(Integer id) {
		Protocol protocol = protocolDao.getById(id);
		ProtocolDto protocolDto = null;
		if(null !=protocol){
			protocolDto = protocolMapper.mapEntityToDto(protocol);
		}
		return protocolDto;
	}

	@Override
	public void remove(Integer protocolId) {
		Protocol protocol = protocolDao.getById(protocolId);
		protocolDao.delete(protocol);
	}

	@Override
	public void edit(ProtocolDto protocolDto) {
		protocolDao.update(protocolMapper.mapDtoToEntity(protocolDto));
	}
}