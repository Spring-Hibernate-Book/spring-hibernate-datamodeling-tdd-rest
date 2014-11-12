package com.oodd.spring.onetomanyunidirectional.mock;
import java.util.ArrayList;
import java.util.List;
import com.oodd.spring.onetomanyunidirectional.dto.PersonDto;
public enum PersonInMemoryDB {
	INSTANCE;
	private static Integer lastId = 0;
	private static List<PersonDto> list = new ArrayList<PersonDto>();
	public Integer getId() {
		return ++lastId;
	}
	public void add(PersonDto personDto){
		personDto.setId(getId());
		list.add(personDto);
	}
	public void edit(PersonDto personDto){
		for(PersonDto dto : list){
			if(dto.getId() == personDto.getId()){
				dto.setName(personDto.getName());
				dto.setNumbers(personDto.getNumbers());
			}
		}
	}
	public void remove(Integer id) {
		PersonDto dto = null;
		for(PersonDto personDto : list){
			if(personDto.getId() == id){dto = personDto;break;}
		}
		if(null !=dto){list.remove(dto);}
	}
	public List<PersonDto> findAll(){
		return list;
	}
	public PersonDto findById(Integer id) {
		for(PersonDto personDto : list){
			if(personDto.getId() == id) return personDto ;
		}
		return null;
	}
}
