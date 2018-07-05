package com.skymall.rest.pojo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 分类节点
* @ClassName: CategoryNode 
* @Description: 用来描述前端需要的数据格式
* @author shianliang  
* @date 2018年2月21日 下午4:11:04 
* @version V1.0
 */
public class CategoryNode {
	
	@JsonProperty("u")
	private String url;
	@JsonProperty("n")
	private String name;
	@JsonProperty("i")
	private List<?> items;
	

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<?> getItems() {
		return items;
	}

	public void setItems(List<?> items) {
		this.items = items;
	}
	
}
