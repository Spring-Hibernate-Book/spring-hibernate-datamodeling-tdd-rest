package com.oodd.spring.onetoonebidirectional.mapper;

import org.springframework.stereotype.Component;

import com.oodd.spring.onetoonebidirectional.dto.CustomerDto;
import com.oodd.spring.onetoonebidirectional.entity.Cart;
import com.oodd.spring.onetoonebidirectional.entity.Customer;
@Component
public class CustomerMapper {
	public Customer mapDtoToEntity(CustomerDto customerDto){
		Customer customer = new Customer();
		Cart cart = new Cart();
		if(null!=customerDto.getId())customer.setId(customerDto.getId());
		if(null!=customerDto.getName())customer.setName(customerDto.getName());
		if(customerDto.getAmount()>0){
			cart.setAmount(customerDto.getAmount());
			cart.setCustomer(customer);
			customer.setCart(cart);
		}	
		return customer;
	}
	public CustomerDto mapEntityToDto(Customer customer){
		CustomerDto customerDto = new CustomerDto() ;
		if(null!=customer.getId())customerDto.setId(customer.getId());
		if(null!=customer.getName())customerDto.setName(customer.getName());
		if(null!=customer.getCart()){
			if(0!=customer.getCart().getAmount())
				customerDto.setAmount(customer.getCart().getAmount());
		}
		return customerDto;
	}
}
