package com.skymall.rest.service;

import java.util.List;

import com.skymall.pojo.TbContent;

public interface ContentService {

	List<TbContent> getContentList(long contentCategoryId);
	
}
