package com.oodd.spring.onetomanybidirectional.dao;
import java.util.List;
import com.oodd.spring.onetomanybidirectional.entity.Item;
public interface ItemDao {
	public void insert(Item item);
	public List<Item> getAll();
	public Item getById(Integer id);
	public void delete(Item item);
	public void update(Item item);
}
