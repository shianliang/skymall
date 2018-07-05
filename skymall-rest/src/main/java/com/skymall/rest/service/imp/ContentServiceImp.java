package com.skymall.rest.service.imp;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.skymall.common.utils.JsonUtils;
import com.skymall.dao.TbContentDao;
import com.skymall.pojo.TbContent;
import com.skymall.pojo.TbContentQuery;
import com.skymall.pojo.TbContentQuery.Criteria;
import com.skymall.rest.dao.JedisClient;
import com.skymall.rest.service.ContentService;
@Service
public class ContentServiceImp implements ContentService {

	@Autowired
	private TbContentDao contentDao;
	
	/**redis客户端*/
	@Autowired
	private JedisClient jedisClient;
	
	/**resource.properties中定义的首页内容 key*/
	@Value("{INDEX_CONTENT_REDIS_KEY}")
	private String INDEX_CONTENT_REDIS_KEY;
	
	@Override
	public List<TbContent> getContentList(long contentCategoryId) {
			
		//从redis中取缓存(不能影响正常的业务逻辑，即使抛异常要捕获打印日志,程序继续执行)
		try {
			String cache=jedisClient.hget(INDEX_CONTENT_REDIS_KEY, contentCategoryId+"");
			if(StringUtils.isNotBlank(cache)){
				List<TbContent> result=JsonUtils.jsonToList(cache, TbContent.class);
				return result;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		TbContentQuery query=new TbContentQuery();
		Criteria criteria = query.createCriteria();
		criteria.andCategoryIdEqualTo(contentCategoryId);
		List<TbContent> list=contentDao.selectByExample(query);
		
		//将数据存入redis
		try {
			String contentCache=JsonUtils.objectToJson(list);
			jedisClient.hset(INDEX_CONTENT_REDIS_KEY, contentCategoryId+"", contentCache);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}

}
