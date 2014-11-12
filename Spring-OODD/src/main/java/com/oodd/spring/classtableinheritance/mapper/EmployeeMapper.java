package com.oodd.spring.classtableinheritance.mapper;

import org.springframework.stereotype.Component;

import com.oodd.spring.classtableinheritance.dto.ContractorEmployeeDto;
import com.oodd.spring.classtableinheritance.dto.EmployeeDto;
import com.oodd.spring.classtableinheritance.dto.PermanentEmployeeDto;
import com.oodd.spring.classtableinheritance.entity.ContractorEmployee;
import com.oodd.spring.classtableinheritance.entity.Employee;
import com.oodd.spring.classtableinheritance.entity.PermanentEmployee;

@Component
public class EmployeeMapper {

	public Employee mapDtoToEntity(EmployeeDto employeeDto){
		if(employeeDto instanceof PermanentEmployeeDto) {
			PermanentEmployee permanentEmployee = new PermanentEmployee();
			if(null!=employeeDto.getId()) permanentEmployee.setId(employeeDto.getId());
			if(null!=employeeDto.getName()) permanentEmployee.setName(employeeDto.getName());
			if(null!=((PermanentEmployeeDto)employeeDto).getLeaves()) permanentEmployee.setLeaves(((PermanentEmployeeDto)employeeDto).getLeaves());
			if(null!=((PermanentEmployeeDto)employeeDto).getSalary()) permanentEmployee.setSalary(((PermanentEmployeeDto)employeeDto).getSalary());
			return permanentEmployee;
		} else if(employeeDto instanceof ContractorEmployeeDto) {
			ContractorEmployee contractorEmployee = new ContractorEmployee();
			if(null!=employeeDto.getId()) contractorEmployee.setId(employeeDto.getId());
			if(null!=employeeDto.getName()) contractorEmployee.setName(employeeDto.getName());
			if(null!=((ContractorEmployeeDto)employeeDto).getHourlyRate()) contractorEmployee.setHourlyRate(((ContractorEmployeeDto)employeeDto).getHourlyRate());
			if(null!=((ContractorEmployeeDto)employeeDto).getOvertimeRate()) contractorEmployee.setOvertimeRate(((ContractorEmployeeDto)employeeDto).getOvertimeRate());
			return contractorEmployee;
		}
		return null;
	}
	public EmployeeDto mapEntityToDto(Employee employee){	
		if(employee instanceof PermanentEmployee) {
			PermanentEmployeeDto permanentEmployeeDto = new PermanentEmployeeDto();
			if(null!=employee.getId()) permanentEmployeeDto.setId(employee.getId());
			if(null!=employee.getName()) permanentEmployeeDto.setName(employee.getName());
			if(null!=((PermanentEmployee)employee).getLeaves()) permanentEmployeeDto.setLeaves(((PermanentEmployee)employee).getLeaves());
			if(null!=((PermanentEmployee)employee).getSalary()) permanentEmployeeDto.setSalary(((PermanentEmployee)employee).getSalary());
			return permanentEmployeeDto;
		} else if(employee instanceof ContractorEmployee) {
			ContractorEmployeeDto contractorEmployeeDto = new ContractorEmployeeDto();
			if(null!=employee.getId()) contractorEmployeeDto.setId(employee.getId());
			if(null!=employee.getName()) contractorEmployeeDto.setName(employee.getName());
			if(null!=((ContractorEmployee)employee).getHourlyRate()) contractorEmployeeDto.setHourlyRate(((ContractorEmployee)employee).getHourlyRate());
			if(null!=((ContractorEmployee)employee).getOvertimeRate()) contractorEmployeeDto.setOvertimeRate(((ContractorEmployee)employee).getOvertimeRate());
			return contractorEmployeeDto;
		}
		return null;
	}
}