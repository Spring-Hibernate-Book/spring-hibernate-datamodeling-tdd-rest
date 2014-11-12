package com.oodd.spring.classtableinheritance.service;

import java.util.List;

import com.oodd.spring.classtableinheritance.dto.EmployeeDto;

public interface EmployeeService {
	public void create(EmployeeDto employeeDto) ;
	public List<EmployeeDto> findAll();
	public EmployeeDto findById(Integer id);
	public void remove(Integer employeeId);
	public void edit(EmployeeDto employeeDto);
}