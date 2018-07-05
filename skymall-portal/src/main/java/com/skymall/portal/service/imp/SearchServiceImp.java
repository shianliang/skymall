package com.skymall.portal.service.imp;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.skymall.common.pojo.SkymallResult;
import com.skymall.common.utils.HttpClientUtil;
import com.skymall.common.utils.JsonUtils;
import com.skymall.portal.pojo.SearchResult;
import com.skymall.portal.service.SearchService;
@Service
public class SearchServiceImp implements SearchService {

	/**search服务基础url*/
	@Value("${SEARCH_BASE_URL}")
	private String SEARCH_BASE_URL;
	
	/**
	 * 全文检索
	 * 直接调用search服务
	 */
	@Override
	public SearchResult search(String query, int page) {
		
		Map<String, String> params=new HashMap<>();
		
		params.put("q", query);
		params.put("page", page+"");

		try {
			String jsonData = HttpClientUtil.doGet(SEARCH_BASE_URL, params);
			if(StringUtils.isNotBlank(jsonData)){
				SkymallResult skymallResult = SkymallResult.formatToPojo(jsonData, SearchResult.class);
				if(skymallResult.getStatus()==200){
					SearchResult result=(SearchResult)skymallResult.getData();
					return result;
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
