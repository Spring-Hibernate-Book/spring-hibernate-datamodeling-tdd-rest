package com.oodd.spring.concretetableinheritance.mapper;

import org.springframework.stereotype.Component;

import com.oodd.spring.concretetableinheritance.dto.BuildingDto;
import com.oodd.spring.concretetableinheritance.dto.EstateDto;
import com.oodd.spring.concretetableinheritance.dto.LandDto;
import com.oodd.spring.concretetableinheritance.entity.Building;
import com.oodd.spring.concretetableinheritance.entity.Estate;
import com.oodd.spring.concretetableinheritance.entity.Land;

@Component
public class EstateMapper {

	public Estate mapDtoToEntity(EstateDto estateDto){
		if(estateDto instanceof BuildingDto) {
			Building building = new Building();
			if(null!=estateDto.getId()) building.setId(estateDto.getId());
			if(null!=estateDto.getName()) building.setName(estateDto.getName());
			if(null!=((BuildingDto)estateDto).getFloors()) building.setFloors(((BuildingDto)estateDto).getFloors());
			return building;
		} else if(estateDto instanceof LandDto) {
			Land land = new Land();
			if(null!=estateDto.getId()) land.setId(estateDto.getId());
			if(null!=estateDto.getName()) land.setName(estateDto.getName());
			if(null!=((LandDto)estateDto).getArea()) land.setArea(((LandDto)estateDto).getArea());
			return land;
		}
		return null;
	}
	public EstateDto mapEntityToDto(Estate estate){	
		if(estate instanceof Building) {
			BuildingDto buildingDto = new BuildingDto();
			if(null!=estate.getId()) buildingDto.setId(estate.getId());
			if(null!=estate.getName()) buildingDto.setName(estate.getName());
			if(null!=((Building)estate).getFloors()) buildingDto.setFloors(((Building)estate).getFloors());
			return buildingDto;
		} else if(estate instanceof Land) {
			LandDto landDto = new LandDto();
			if(null!=estate.getId()) landDto.setId(estate.getId());
			if(null!=estate.getName()) landDto.setName(estate.getName());
			if(null!=((Land)estate).getArea()) landDto.setArea(((Land)estate).getArea());
			return landDto;
		}
		return null;
	}
}