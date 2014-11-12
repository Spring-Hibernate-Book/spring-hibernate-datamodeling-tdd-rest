package com.oodd.spring.onetomanybidirectional.mapper;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.oodd.spring.onetomanybidirectional.dto.ItemDto;
import com.oodd.spring.onetomanybidirectional.entity.Feature;
import com.oodd.spring.onetomanybidirectional.entity.Item;
@Component
public class ItemMapper {
	public Item mapDtoToEntity(ItemDto itemDto){
		Item item = new Item();
		if(null != itemDto.getId()){item.setId(itemDto.getId());}
		if(null != itemDto.getName()){item.setName(itemDto.getName());}
		if(null != itemDto.getFeatureList() && itemDto.getFeatureList().size()>0){
			List<Feature> features = new ArrayList<Feature>();
			for(String feature : itemDto.getFeatureList()){
				Feature featureObject = new Feature();
				featureObject.setName(feature);
				featureObject.setItem(item);
				features.add(featureObject);
			}
			item.setFeatures(features);
		}
		return item ;
	}
	public ItemDto mapEntityToDto(Item item){
		ItemDto itemDto = new ItemDto();
		if(null != item.getId()){itemDto.setId(item.getId());}
		if(null != item.getName()){itemDto.setName(item.getName());}
		if(null != item.getFeatures() && item.getFeatures().size() > 0){
			List<String> featuers = new ArrayList<String>();
			for(Feature feature : item.getFeatures()){
				featuers.add(feature.getName());
			}
			itemDto.setFeatureList(featuers);
		}
		return itemDto;
	}
}
