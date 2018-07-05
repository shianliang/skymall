package com.skymall.search.service;

import com.skymall.common.pojo.SkymallResult;
import com.skymall.search.pojo.Item;

public interface ItemService {
	
	SkymallResult importAllItems();
	
	Item getItemByItemId(Long itemId);
}
