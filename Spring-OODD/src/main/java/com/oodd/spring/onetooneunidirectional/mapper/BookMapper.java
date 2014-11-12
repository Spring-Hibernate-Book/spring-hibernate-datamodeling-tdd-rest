package com.oodd.spring.onetooneunidirectional.mapper;

import org.springframework.stereotype.Component;

import com.oodd.spring.onetooneunidirectional.entity.Book;
import com.oodd.spring.onetooneunidirectional.entity.Shipping;
import com.oodd.spring.onetooneunidirectional.dto.BookDto;
@Component
public class BookMapper {
	public Book mapDtoToEntity(BookDto bookDto){
		Book book = new Book();
		Shipping shipping = new Shipping();
		if(null!=bookDto.getId())book.setId(bookDto.getId());
		if(null!=bookDto.getName())book.setName(bookDto.getName());
		if(null!=bookDto.getCity()){shipping.setCity(bookDto.getCity());book.setShipping(shipping);}
		return book;
	}
	public BookDto mapEntityToDto(Book book){
		BookDto bookDto = new BookDto();
		if(null!=book.getId())bookDto.setId(book.getId());
		if(null!=book.getName())bookDto.setName(book.getName());
		if(null!=book.getShipping()){
			if(null !=book.getShipping().getCity()){
				bookDto.setCity(book.getShipping().getCity());
			}
		}
		return bookDto;
	}
}
