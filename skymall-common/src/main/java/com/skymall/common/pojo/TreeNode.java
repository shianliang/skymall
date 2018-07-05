package com.skymall.common.pojo;
/**
 * 商品分类 easyUi 格式
 * @author admin
 *
 */
public class TreeNode {

	/**id*/
	private long id;
	/***/
	private String text;
	/**如果是叶子节点是 open 否则是closed*/
	private String state;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	
}
