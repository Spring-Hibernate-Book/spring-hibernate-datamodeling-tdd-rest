package com.oodd.spring.standalone.service;

import java.util.List;
import com.oodd.spring.standalone.dto.ProductDto;

public interface ProductService {
	public void create(ProductDto productDto ) ;
	public List<ProductDto> findAll();
	public ProductDto findById(Integer id);
	public void remove(Integer productId);
	public void edit(ProductDto productDto);
}
