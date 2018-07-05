package com.skymall.common.pojo;

import java.util.List;
/**
 * 分页数据 easyUi 格式
 * @author admin
 *
 */
public class DataResult {

	/**分页总条数*/
	private int total;
	
	/**分页数据*/
	private List<?> rows;

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<?> getRows() {
		return rows;
	}

	public void setRows(List<?> rows) {
		this.rows = rows;
	}
	
	
	
}
