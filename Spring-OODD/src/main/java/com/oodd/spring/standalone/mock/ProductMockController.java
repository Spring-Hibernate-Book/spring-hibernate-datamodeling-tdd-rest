package com.oodd.spring.standalone.mock;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.oodd.spring.standalone.dto.ProductDto;

@Controller
@RequestMapping(value="/standalone/mock")
public class ProductMockController {
	
	@RequestMapping(value="/findAll", method=RequestMethod.GET)
	public @ResponseBody List<ProductDto> findAll(){
		return ProductInMemoryDB.INSTANCE.findAll();
	}
	@RequestMapping(value="/findById/{productid}", method=RequestMethod.GET)
	public @ResponseBody ProductDto findById(@PathVariable("productid") Integer productid){
		return ProductInMemoryDB.INSTANCE.findById(productid);
	}
	@RequestMapping(value="/create", method=RequestMethod.POST)
	@ResponseBody
	public void create(@RequestBody ProductDto product){
		ProductInMemoryDB.INSTANCE.add(product);
	}
	@RequestMapping(value="/remove/{productid}", method=RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void remove(@PathVariable("productid") Integer productid){
		ProductInMemoryDB.INSTANCE.remove(productid);
	}
	@RequestMapping(value="/edit", method=RequestMethod.POST)
	@ResponseBody
	public void edit(@RequestBody ProductDto product){
		ProductInMemoryDB.INSTANCE.edit(product);
	}
}
