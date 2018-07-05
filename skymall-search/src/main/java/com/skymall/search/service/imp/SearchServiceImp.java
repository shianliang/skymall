package com.skymall.search.service.imp;

import org.apache.solr.client.solrj.SolrQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skymall.search.dao.SearchDao;
import com.skymall.search.pojo.SearchResult;
import com.skymall.search.service.SearchService;
@Service
public class SearchServiceImp implements SearchService {

	@Autowired
	private SearchDao searchDao;
	

	@Override
	public SearchResult search(String queryString, int page, int rows) throws Exception {
		
		//创建查询条件
		SolrQuery solrQuery=new SolrQuery();
		
		//设置查询条件
		solrQuery.setQuery(queryString);
		
		//设置分页
		solrQuery.setStart((page-1)*rows);
		solrQuery.setRows(rows);
		
		//默认搜索域
		solrQuery.set("df","item_keywords");
		
		//设置高亮
		solrQuery.setHighlight(true);
		solrQuery.addHighlightField("item_title");
		solrQuery.setHighlightSimplePre("<em style=\"color:red\">");
		solrQuery.setHighlightSimplePost("</em>");
		
		//调用dao的查询方法
		SearchResult result=searchDao.search(solrQuery);
		
		//计算总页数
		long recordCount=result.getRecordCount();
		long pageCount=recordCount/rows;
		if(recordCount%rows>0){
			pageCount++;
		}
		result.setPageCount(pageCount);
		result.setCurPage(page);
		
		
		return result;
	}

	


}
