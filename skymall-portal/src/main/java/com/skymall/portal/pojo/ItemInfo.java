/**  
 * <p>Title: ItemInfo.java</p>  
 * <p>Description: </p>   
 * @author shianliang  
 * @date 2018年4月22日  
 * @version 1.0  
 */ 
package com.skymall.portal.pojo;

import java.awt.Image;

import org.apache.commons.lang3.StringUtils;

import com.skymall.pojo.TbItem;

/**  
 * <p>Title: ItemInfo</p>  
 * <p>Description: </p>  
 * @author shianliang 
 * @date 2018年4月22日  
 */
public class ItemInfo extends TbItem{

	public String[] getImages(){
		String superImage=super.getImage();
		if(StringUtils.isNotBlank(superImage)){
			String[] images=superImage.split(",");
			return images;
		}
		return null;
	}
	
}
