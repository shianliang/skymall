package com.skymall.search.dao.imp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.skymall.search.dao.SearchDao;
import com.skymall.search.pojo.Item;
import com.skymall.search.pojo.SearchResult;

@Repository
public class SearchDaoImp implements SearchDao{

	@Autowired
	private SolrServer solrServer;
	
	@Override
	public SearchResult search(SolrQuery query) throws SolrServerException {
		//执行查询
		QueryResponse response = solrServer.query(query);
		//取返回结果
		SolrDocumentList result=response.getResults();
		
		//取高亮显示
		Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();
		
		String title="";
		List<Item> list=new ArrayList<>();
		for (SolrDocument solrDocument : result) {
			Item item=new Item();
			item.setId((String)solrDocument.get("id"));
			
			//取高亮（如果title设定是高亮就取高亮对象中的title）
			List<String> titles=highlighting.get((String)solrDocument.get("id")).get("item_title");
			if(titles!=null&&title.length()>0){
				title=titles.get(0);
			}else{
				title=(String)solrDocument.get("item_title");
			}
			
			item.setTitle(title);
			item.setPrice((long)solrDocument.get("item_price"));
			item.setCeagory_name((String) solrDocument.get("item_ceagory_name"));
			item.setImage((String) solrDocument.get("item_image"));
			item.setSell_point((String) solrDocument.get("item_sell_point"));
			list.add(item);
		}
		//返回对象
		SearchResult searchResult=new SearchResult();		
		searchResult.setItemList(list);
		//获得总条数
		searchResult.setRecordCount(result.getNumFound());

		return searchResult;
	}

}
