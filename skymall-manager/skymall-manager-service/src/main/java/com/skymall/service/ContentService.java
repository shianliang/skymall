package com.skymall.service;

import com.skymall.common.pojo.DataResult;
import com.skymall.common.pojo.SkymallResult;
import com.skymall.pojo.TbContent;

public interface ContentService {

	
	DataResult getContentList(long categoryId,int page,int rows);
	
	SkymallResult saveContent(TbContent content);
	
	SkymallResult editContent(TbContent content);
}
