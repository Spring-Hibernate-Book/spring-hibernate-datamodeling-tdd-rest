package com.oodd.spring.classtableinheritance.dao;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.oodd.spring.classtableinheritance.entity.Employee;

@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration( locations = { "classpath:context.xml" } )
@TransactionConfiguration( defaultRollback = true )
@Transactional
public class EmployeeDaoImplTest {
	@Autowired
	private EmployeeDao dao ;

	@Test
	public void testInsert() {
		Employee temployee = new Employee();
		temployee.setName("Lalit Narayan Mishra");
		dao.insert(temployee);
		
		Employee sEmployee = new Employee();
		sEmployee.setName("Amritendu De");
		dao.insert(sEmployee);
	
		Assert.assertEquals(2L, dao.getAll().size());
	}

	@Test
	public void testGetAll() {
		Assert.assertEquals(0L, dao.getAll().size());
	}
	
	@Test
	public void testGetById() {
		Employee semployee = new Employee();
		semployee.setName("Lalit Narayan Mishra");
		dao.insert(semployee);
	
		List<Employee> pList = dao.getAll();
		Employee employee = pList.get(0);
		
		Employee employee2 = dao.getById(employee.getId());
		Assert.assertEquals("Lalit Narayan Mishra", employee2.getName());
	}
	
	@Test
	public void testDelete() {
		Employee semployee = new Employee();
		semployee.setName("Lalit Narayan Mishra");
		dao.insert(semployee);
		
		Employee tEmployee = new Employee();
		tEmployee.setName("Amritendu De");
		dao.insert(tEmployee);
	
		Assert.assertEquals(2L, dao.getAll().size());
		
		dao.delete(tEmployee);
		
		Assert.assertEquals(1L, dao.getAll().size());
	}
	
	@Test
	public void testUpdate() {
		Employee semployee = new Employee();
		semployee.setName("Lalit Narayan Mishra");
		dao.insert(semployee);
		
		Assert.assertEquals(1L, dao.getAll().size());

		List<Employee> pList = dao.getAll();
		Employee employee = pList.get(0);
		employee.setName("Amritendu De");
		
		dao.update(employee);
		
		List<Employee> pList2 = dao.getAll();
		Employee employee2 = pList2.get(0);
		Assert.assertEquals("Amritendu De", employee2.getName());
	}
}