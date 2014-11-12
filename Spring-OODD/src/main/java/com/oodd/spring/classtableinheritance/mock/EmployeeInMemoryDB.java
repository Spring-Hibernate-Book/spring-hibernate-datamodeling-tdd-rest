package com.oodd.spring.classtableinheritance.mock;

import java.util.ArrayList;
import java.util.List;

import com.oodd.spring.classtableinheritance.dto.ContractorEmployeeDto;
import com.oodd.spring.classtableinheritance.dto.EmployeeDto;
import com.oodd.spring.classtableinheritance.dto.PermanentEmployeeDto;

public enum EmployeeInMemoryDB {

	INSTANCE;
	
	private static List<EmployeeDto> list = new ArrayList<EmployeeDto>();
	private static Integer lastId = 0;
	
	public Integer getId() {
		return ++lastId;
	}
	
	public void add(EmployeeDto employeeDto) {
		employeeDto.setId(getId());
		list.add(employeeDto);
	}

	public void edit(EmployeeDto employeeDto) {
		for (EmployeeDto dto:list) {
			if (dto.getId()==employeeDto.getId()) {
				dto.setName(employeeDto.getName());
				if(employeeDto instanceof PermanentEmployeeDto) {
					((PermanentEmployeeDto)dto).setLeaves(((PermanentEmployeeDto)employeeDto).getLeaves());
					((PermanentEmployeeDto)dto).setSalary(((PermanentEmployeeDto)employeeDto).getSalary());
				} else if(employeeDto instanceof ContractorEmployeeDto) {
					((ContractorEmployeeDto)dto).setHourlyRate(((ContractorEmployeeDto)employeeDto).getHourlyRate());
					((ContractorEmployeeDto)dto).setOvertimeRate(((ContractorEmployeeDto)employeeDto).getOvertimeRate());
				}
			}
		}
	}
	
	public void remove(Integer id) {
		EmployeeDto toRemove = null;
		for (EmployeeDto dto:list) 
			if (dto.getId()==id) toRemove = dto;
		if (toRemove!=null) list.remove(toRemove);
	}
	
	public List<EmployeeDto> findAll() {
		return list;
	}
	
	public EmployeeDto findById(Integer id) {
		for (EmployeeDto dto:list) {
			if (dto.getId()==id)
				return dto;
		}
		return null;
	}
}
