package com.oodd.spring.manytomanybidirectional.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oodd.spring.manytomanybidirectional.dao.AccountDao;
import com.oodd.spring.manytomanybidirectional.dto.AccountDto;
import com.oodd.spring.manytomanybidirectional.entity.Account;
import com.oodd.spring.manytomanybidirectional.mapper.AccountMapper;
import com.oodd.spring.manytomanybidirectional.service.AccountService;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {
	
	@Autowired
	private AccountDao accountDao;
	
	@Autowired
	private AccountMapper accountMapper;
	
	@Override
	public void create(AccountDto accountDto) {
		accountDao.insert(accountMapper.mapDtoToEntity(accountDto));
	}

	@Override
	public List<AccountDto> findAll() {
		List<Account> accounts = accountDao.getAll();
		List<AccountDto> accountDtos = new ArrayList<AccountDto>();
		for(Account account : accounts) {
			accountDtos.add(accountMapper.mapEntityToDto(account));
		}
		return accountDtos;
	}

	@Override
	public AccountDto findById(Integer id) {
		Account account = accountDao.getById(id);
		if(null != account) {
			return accountMapper.mapEntityToDto(account);
		}
		return null;
	}

	@Override
	public void remove(Integer id) {
		Account account = accountDao.getById(id);
		accountDao.delete(account);
	}

	@Override
	public void edit(AccountDto accountDto) {
		Account accountCurrent = accountDao.getById(accountDto.getId());
		Account accountToBeEdited = accountMapper.mapDtoToEntity(accountDto);
		accountToBeEdited.setClients(accountCurrent.getClients());
		accountDao.update(accountToBeEdited);
	}
}