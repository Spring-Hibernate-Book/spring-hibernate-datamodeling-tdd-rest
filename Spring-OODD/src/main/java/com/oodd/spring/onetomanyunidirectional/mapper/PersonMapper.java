package com.oodd.spring.onetomanyunidirectional.mapper;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.oodd.spring.onetomanyunidirectional.dto.PersonDto;
import com.oodd.spring.onetomanyunidirectional.entity.Person;
import com.oodd.spring.onetomanyunidirectional.entity.Phone;
@Component
public class PersonMapper {
	public Person mapDtoToEntity(PersonDto personDto){
		Person person = new Person();
		if(null !=personDto.getId()){person.setId(personDto.getId());}
		if(null !=personDto.getName()){person.setName(personDto.getName());}
		if(null !=personDto.getNumbers()&& personDto.getNumbers().size() > 0){
			List<Phone> phoneList = new ArrayList<Phone>();
			if(personDto.getNumbers()!=null && personDto.getNumbers().size()>0){
			for(String number : personDto.getNumbers()){
				Phone phone = new Phone();
				phone.setNumber(number);
				phoneList.add(phone);}
			}
			person.setPhones(phoneList);
		}
		return person;
	}
	public PersonDto mapEntityToDto(Person person){
		PersonDto personDto = new PersonDto();
		if(null !=person.getId()){personDto.setId(person.getId());}
		if(null !=person.getName()){personDto.setName(person.getName());}
		if(null !=person.getPhones() && person.getPhones().size() >0){
			List<String> phones = new ArrayList<String>();
			for(Phone phone : person.getPhones()){phones.add(phone.getNumber());}
			personDto.setNumbers(phones);
		}
		return personDto;
	}
}
