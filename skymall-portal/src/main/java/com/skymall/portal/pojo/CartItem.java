/**  
 * <p>Title: CartItem.java</p>  
 * <p>Description: </p>   
 * @author shianliang  
 * @date 2018年5月13日  
 * @version 1.0  
 */ 
package com.skymall.portal.pojo;

/**  
 * <p>Title: CartItem</p>  
 * <p>Description: </p>  
 * @author shianliang 
 * @date 2018年5月13日  
 */
public class CartItem {
	private long id;
	private String title;
	private long price;
	private Integer num;
	private String image;
	

	public long getId() {
		return id;
	}
	public void setId(long id) {
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
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}

	
	
}
