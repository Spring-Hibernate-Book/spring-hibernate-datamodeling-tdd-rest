package com.oodd.spring.standalone.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.oodd.spring.standalone.dto.ProductDto;
import com.oodd.spring.standalone.service.ProductService;

@Controller
@RequestMapping(value="/standalone")
@Transactional
public class ProductController {
	
	@Autowired
	private ProductService service ;
	
	@RequestMapping(value="/findAll", method=RequestMethod.GET)
	public @ResponseBody List<ProductDto> findAll(){
		return service.findAll();
	}
	@RequestMapping(value="/findById/{productid}", method=RequestMethod.GET)
	public @ResponseBody ProductDto findById(@PathVariable("productid") Integer productid){
		return service.findById(productid);
	}
	@RequestMapping(value="/create", method=RequestMethod.POST)
	@ResponseBody
	public void create(@RequestBody ProductDto product){
		service.create(product);
	}
	@RequestMapping(value="/remove/{productid}", method=RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void remove(@PathVariable("productid") Integer productid){
		service.remove(productid);
	}
	@RequestMapping(value="/edit", method=RequestMethod.POST)
	@ResponseBody
	public void edit(@RequestBody ProductDto product){
		service.edit(product);
	}
}
