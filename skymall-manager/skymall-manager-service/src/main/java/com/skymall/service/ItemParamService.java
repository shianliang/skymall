package com.skymall.service;

import java.util.List;

import com.skymall.common.pojo.DataResult;
import com.skymall.common.pojo.SkymallResult;
import com.skymall.pojo.TbItemParam;

public interface ItemParamService {

	/**
	 * 
	 * <p>Description:通过商品分类ID获取商品规格 </p> 
	 * @author shianliang  
	 * @date 2018年4月22日  
	 * @version 1.0
	 * @param catId
	 * @return
	 */
	SkymallResult getItemParamByCatId(long catId);
	
	/**
	 * 
	 * <p>Description: 保存商品规格 </p> 
	 * @author shianliang  
	 * @date 2018年4月22日  
	 * @version 1.0
	 * @param itemParam
	 * @return
	 */
	SkymallResult saveItemParam(TbItemParam itemParam);
	
	/**
	 * 
	 * <p>Description:商品规格列表 </p> 
	 * @author shianliang  
	 * @date 2018年4月22日  
	 * @version 1.0
	 * @param page
	 * @param pageSize
	 * @return
	 */
	DataResult itemParamList(int page,int pageSize);
}
