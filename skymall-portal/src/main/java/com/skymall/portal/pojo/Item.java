package com.skymall.portal.pojo;

import org.apache.commons.lang3.StringUtils;

/**
 * 从数据库导入solr索引库需要的pojo
* @ClassName: Item 
* @Description: 
* @author
* @date 2018年3月26日 上午11:06:42 
* @version V1.0
 */
public class Item {

	private String id;
	private String title;
	private long price;
	private String sell_point;
	private String image;
	private String ceagory_name;
	private String item_des;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public long getPrice() {
		return price;
	}
	public void setPrice(long price) {
		this.price = price;
	}
	public String getSell_point() {
		return sell_point;
	}
	public void setSell_point(String sell_point) {
		this.sell_point = sell_point;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getCeagory_name() {
		return ceagory_name;
	}
	public void setCeagory_name(String ceagory_name) {
		this.ceagory_name = ceagory_name;
	}
	public String getItem_des() {
		return item_des;
	}
	public void setItem_des(String item_des) {
		this.item_des = item_des;
	}
	public String[] getImages(){
		if(StringUtils.isNotBlank(image)){
			return image.split(",");
		}
		return null;
	}
	
}
