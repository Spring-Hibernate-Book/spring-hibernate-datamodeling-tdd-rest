package com.oodd.spring.classtableinheritance.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oodd.spring.classtableinheritance.dao.EmployeeDao;
import com.oodd.spring.classtableinheritance.dto.EmployeeDto;
import com.oodd.spring.classtableinheritance.entity.Employee;
import com.oodd.spring.classtableinheritance.mapper.EmployeeMapper;
import com.oodd.spring.classtableinheritance.service.EmployeeService;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeDao employeeDao;
	
	@Autowired
	private EmployeeMapper employeeMapper;
	@Override
	public void create(EmployeeDto employeeDto) {		
		employeeDao.insert(employeeMapper.mapDtoToEntity(employeeDto));
	}
	@Override
	public List<EmployeeDto> findAll() {
		List<Employee> employees = employeeDao.getAll();
		List<EmployeeDto> employeeDtos = new ArrayList<EmployeeDto>();
		for(Employee employee : employees){
			employeeDtos.add(employeeMapper.mapEntityToDto(employee));
		}
		return employeeDtos;
	}

	@Override
	public EmployeeDto findById(Integer id) {
		Employee employee = employeeDao.getById(id);
		EmployeeDto employeeDto = null;
		if(null !=employee){
			employeeDto = employeeMapper.mapEntityToDto(employee);
		}
		return employeeDto;
	}

	@Override
	public void remove(Integer employeeId) {
		Employee employee = employeeDao.getById(employeeId);
		employeeDao.delete(employee);
	}

	@Override
	public void edit(EmployeeDto employeeDto) {
		employeeDao.update(employeeMapper.mapDtoToEntity(employeeDto));
	}
}