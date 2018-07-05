package com.skymall.search.mapper;

import java.util.List;

import com.skymall.search.pojo.Item;

public interface ItemDao {

   List<Item>  getItemList();
	
   Item getItemByItemId(long ItemId);
	
}
