package com.oodd.spring.manytomanybidirectionalwithjoinattribute.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oodd.spring.manytomanybidirectionalwithjoinattribute.dao.ManuscriptDao;
import com.oodd.spring.manytomanybidirectionalwithjoinattribute.dto.ManuscriptDto;
import com.oodd.spring.manytomanybidirectionalwithjoinattribute.entity.Manuscript;
import com.oodd.spring.manytomanybidirectionalwithjoinattribute.mapper.ManuscriptMapper;
import com.oodd.spring.manytomanybidirectionalwithjoinattribute.service.ManuscriptService;

@Service
@Transactional
public class ManuscriptServiceImpl implements ManuscriptService {

	@Autowired
	private ManuscriptDao manuscriptDao;
	
	@Autowired
	private ManuscriptMapper manuscriptMapper;
	
	@Override
	public void create(ManuscriptDto manuscriptDto) {
		manuscriptDao.insert(manuscriptMapper.mapDtoToEntity(manuscriptDto));
	}

	@Override
	public List<ManuscriptDto> findAll() {
		List<Manuscript> manuscripts = manuscriptDao.getAll();
		List<ManuscriptDto> manuscriptDtos = new ArrayList<ManuscriptDto>();
		for(Manuscript manuscript : manuscripts) {
			manuscriptDtos.add(manuscriptMapper.mapEntityToDto(manuscript));
		}
		return manuscriptDtos;
	}

	@Override
	public ManuscriptDto findById(Integer id) {
		Manuscript manuscript = manuscriptDao.getById(id);
		if(null != manuscript) {
			return manuscriptMapper.mapEntityToDto(manuscript);
		}
		return null;
	}

	@Override
	public void remove(Integer id) {
		Manuscript manuscript = manuscriptDao.getById(id);
		manuscriptDao.delete(manuscript);
	}

	@Override
	public void edit(ManuscriptDto manuscriptDto) {
		Manuscript manuscriptCurrent = manuscriptDao.getById(manuscriptDto.getId());
		Manuscript manuscriptToBeEdited = manuscriptMapper.mapDtoToEntity(manuscriptDto);
		manuscriptToBeEdited.setManuscriptAuthors(manuscriptCurrent.getManuscriptAuthors());
		manuscriptDao.update(manuscriptToBeEdited);
	}
}