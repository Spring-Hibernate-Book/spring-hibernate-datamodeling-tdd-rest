package com.oodd.spring.standalone.mapper;

import org.springframework.stereotype.Component;

import com.oodd.spring.standalone.dto.ProductDto;
import com.oodd.spring.standalone.entity.Product;

@Component
public class ProductMapper {

	public Product mapDtoToEntity(ProductDto productDto){
		Product product = new Product();
		if(null!=productDto.getId()) product.setId(productDto.getId());
		if(null!=productDto.getName()) product.setName(productDto.getName());
		return product;
	}
	public ProductDto mapEntityToDto(Product product){
		ProductDto productDto = new ProductDto();
		if(null!=product.getId()) productDto.setId(product.getId());
		if(null!=product.getName()) productDto.setName(product.getName());
		return productDto;
	}
}
