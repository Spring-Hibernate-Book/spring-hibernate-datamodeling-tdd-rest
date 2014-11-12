/**
 * 
 */
package com.oodd.spring.classtableinheritance.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.oodd.spring.classtableinheritance.dto.ContractorEmployeeDto;
import com.oodd.spring.classtableinheritance.dto.EmployeeDto;
import com.oodd.spring.classtableinheritance.dto.PermanentEmployeeDto;

@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration( locations = { "classpath:context.xml" } )
@TransactionConfiguration( defaultRollback = true )
@Transactional
public class EmployeeServiceImplTest {
	@Autowired
	private EmployeeService service;
	
	@Test
	public void testCreate() {
		EmployeeDto bemployee = new PermanentEmployeeDto();
		bemployee.setName("Lalit Narayan Mishra");
		((PermanentEmployeeDto)bemployee).setLeaves(30);
		((PermanentEmployeeDto)bemployee).setSalary(500000);
		service.create(bemployee);
		
		EmployeeDto hEmployee = new ContractorEmployeeDto();
		hEmployee.setName("Amritendu De");
		((ContractorEmployeeDto)hEmployee).setHourlyRate(35);
		((ContractorEmployeeDto)hEmployee).setOvertimeRate(20);
		service.create(hEmployee);
	
		Assert.assertEquals(2L, service.findAll().size());
	}

	@Test
	public void testFindAll() {
		Assert.assertEquals(0L, service.findAll().size());
	}
	
	@Test
	public void testFindById() {
		EmployeeDto bemployee = new PermanentEmployeeDto();
		bemployee.setName("Lalit Narayan Mishra");
		((PermanentEmployeeDto)bemployee).setLeaves(30);
		((PermanentEmployeeDto)bemployee).setSalary(500000);
		service.create(bemployee);
	
		List<EmployeeDto> pList = service.findAll();
		EmployeeDto employee = pList.get(0);
		
		EmployeeDto employee2 = service.findById(employee.getId());
		Assert.assertEquals("Lalit Narayan Mishra", employee2.getName());
	}
	
	@Test
	public void testRemove() {
		EmployeeDto bemployee = new PermanentEmployeeDto();
		bemployee.setName("Lalit Narayan Mishra");
		((PermanentEmployeeDto)bemployee).setLeaves(30);
		((PermanentEmployeeDto)bemployee).setSalary(500000);
		service.create(bemployee);
		
		EmployeeDto hEmployee = new ContractorEmployeeDto();
		hEmployee.setName("Amritendu De");
		((ContractorEmployeeDto)hEmployee).setHourlyRate(35);
		((ContractorEmployeeDto)hEmployee).setOvertimeRate(20);
		service.create(hEmployee);
	
		Assert.assertEquals(2L, service.findAll().size());
		
		List<EmployeeDto> pList = service.findAll();
		EmployeeDto employee = pList.get(0);
		service.remove(employee.getId());
		
		Assert.assertEquals(1L, service.findAll().size());
	}
	
	@Test
	public void testEdit() {
		EmployeeDto bemployee = new PermanentEmployeeDto();
		bemployee.setName("Lalit Narayan Mishra");
		((PermanentEmployeeDto)bemployee).setLeaves(30);
		((PermanentEmployeeDto)bemployee).setSalary(500000);
		service.create(bemployee);
		
		Assert.assertEquals(1L, service.findAll().size());

		List<EmployeeDto> pList = service.findAll();
		EmployeeDto employee = pList.get(0);
		employee.setName("Amritendu De");
		
		service.edit(employee);
		
		List<EmployeeDto> pList2 = service.findAll();
		EmployeeDto employee2 = pList2.get(0);
		Assert.assertEquals("Amritendu De", employee2.getName());
	}	
}