package com.skymall.portal.service.imp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.skymall.common.pojo.SkymallResult;
import com.skymall.common.utils.HttpClientUtil;
import com.skymall.common.utils.JsonUtils;
import com.skymall.pojo.TbContent;
import com.skymall.portal.service.ContentService;
@Service
public class ContentServiceImp implements ContentService{

	@Value("${REST_BASE_URL}")
	private String REST_BASE_URL;
	
	@Value("${REST_AD_URL}")
	private String REST_AD_URL;
	
	/**
	 * 获取首页大广告位列表
	* @Title: getContent 
	* @param @param categoryId
	* @param @return    入参
	* @return String    返回类型
	* @author （作者） 
	* @throws
	* @date 2018年3月15日 下午2:08:15 
	* @version V1.0
	 */
	@Override
	public String getContentList() {
		try {
			
			String result=HttpClientUtil.doGet(REST_BASE_URL+REST_AD_URL);
			
			if(StringUtils.isNotBlank(result)){
				
				//TODO  标记下
				//json数据格式化为SkymallResult类型
				SkymallResult skymallResult=SkymallResult.formatToList(result,TbContent.class);
				if(skymallResult.getStatus()==200){
					
					List<TbContent> list=(List<TbContent>)skymallResult.getData();
					
					
					List<Map<String, String>> resultList=new ArrayList<>();
					for (TbContent content : list) {
						
						Map<String,String> map=new HashMap<String,String>();
						map.put("src", content.getPic());
						map.put("heigth","240");
						map.put("width","670");
						map.put("srcB", content.getPic2());
						map.put("heigthB", "240");
						map.put("widthB","550");
						resultList.add(map);
					}
					return JsonUtils.objectToJson(resultList);
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		
		}
		
		return null;
	}

}
