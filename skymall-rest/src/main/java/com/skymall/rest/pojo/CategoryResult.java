package com.skymall.rest.pojo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *  分类数据
* @ClassName: CategoryResult 
* @Description:  用来描述前端需要的数据格式
* @author shianliang  
* @date 2018年2月21日 下午4:10:13 
* @version V1.0
 */
public class CategoryResult {
	

	private List<?> data;

	public List<?> getData() {
		return data;
	}

	public void setData(List<?> data) {
		this.data = data;
	}
	
	
	
}
