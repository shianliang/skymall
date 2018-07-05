package com.skymall.rest.service;

import com.skymall.common.pojo.SkymallResult;

public interface ItemService {

	
	SkymallResult findItemInfoById(long ItemId);
	
	SkymallResult findItemDescByItemId(long itemId);
	
	SkymallResult findItemParamByItmId(long itemId);
}
