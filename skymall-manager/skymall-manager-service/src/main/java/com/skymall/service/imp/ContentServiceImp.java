package com.skymall.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skymall.common.pojo.DataResult;
import com.skymall.common.pojo.SkymallResult;
import com.skymall.common.utils.HttpClientUtil;
import com.skymall.dao.TbContentDao;
import com.skymall.pojo.TbContent;
import com.skymall.pojo.TbContentQuery;
import com.skymall.pojo.TbContentQuery.Criteria;
import com.skymall.service.ContentService;
@Service
public class ContentServiceImp implements ContentService {

	@Autowired
	private TbContentDao contentDao;
	
	/**缓存同步rest请求url*/
	@Value("${REST_BASE_URL}")
	private String REST_BASE_URL;
	@Value("${REST_CONTENT_SYNC_URL}")
	private String REST_CONTENT_SYNC_URL;
	/**
	 * 获取内容列表
	 */
	@Override
	public DataResult getContentList(long categoryId,int page,int rows) {
		
		TbContentQuery query=new TbContentQuery();
		Criteria criteria = query.createCriteria();
		criteria.andCategoryIdEqualTo(categoryId);
		PageHelper.startPage(page, rows);
		
		List<TbContent> list = contentDao.selectByExample(query);
		//获取总条数
	    PageInfo<TbContent> info= new PageInfo<>(list);
		long total=info.getTotal();
		
		DataResult result=new DataResult();
		result.setRows(list);
		result.setTotal((int)total);
		
		
		return result;
	}
	/**
	 * 保存商品内容
	 */
	@Override
	public SkymallResult saveContent(TbContent content) {
		 
		contentDao.insert(content);
		
		//缓存同步
		try {
			//调用rest服务（缓存同步）
			HttpClientUtil.doGet(REST_BASE_URL+REST_CONTENT_SYNC_URL+content.getCategoryId());
			
		} catch (Exception e) {
			e.printStackTrace();
			//TODO 缓存同步失败向管理员发送短信或邮件
		}
		
		return SkymallResult.ok();
	}
	/**
	 * 编辑商品内容
	 */
	@Override
	public SkymallResult editContent(TbContent content) {

		contentDao.updateByPrimaryKey(content);
		//缓存同步
		try {
			//调用rest服务（缓存同步）
			HttpClientUtil.doGet(REST_BASE_URL+REST_CONTENT_SYNC_URL+content.getCategoryId());
			
		} catch (Exception e) {
			e.printStackTrace();
			//TODO 缓存同步失败向管理员发送短信或邮件
		}
		
		return SkymallResult.ok();
	}

}
