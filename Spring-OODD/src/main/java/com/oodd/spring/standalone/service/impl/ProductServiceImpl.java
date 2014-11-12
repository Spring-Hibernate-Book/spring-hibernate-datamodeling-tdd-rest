package com.oodd.spring.standalone.service.impl;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oodd.spring.standalone.dao.ProductDao;
import com.oodd.spring.standalone.dto.ProductDto;
import com.oodd.spring.standalone.entity.Product;
import com.oodd.spring.standalone.mapper.ProductMapper;
import com.oodd.spring.standalone.service.ProductService;

@Service
@Transactional
public class ProductServiceImpl implements ProductService{
	@Autowired
	private ProductDao productDao;
	@Autowired
	private ProductMapper productMapper;
	@Override
	public void create(ProductDto productDto) {
		productDao.insert(productMapper.mapDtoToEntity(productDto));
	}
	@Override
	public List<ProductDto> findAll() {
		List<Product> products = productDao.getAll();
		List<ProductDto> productDtos = new ArrayList<ProductDto>();
		for(Product product : products){
			productDtos.add(productMapper.mapEntityToDto(product));
		}
		return productDtos;
	}

	@Override
	public ProductDto findById(Integer id) {
		Product product = productDao.getById(id);
		ProductDto productDto = null;
		if(null !=product){
			productDto = productMapper.mapEntityToDto(product);
		}
		return productDto;
	}

	@Override
	public void remove(Integer productId) {
		Product product = productDao.getById(productId);
		productDao.delete(product);
	}

	@Override
	public void edit(ProductDto productDto) {
		productDao.update(productMapper.mapDtoToEntity(productDto));
	}

}
