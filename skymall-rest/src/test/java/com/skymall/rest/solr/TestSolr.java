package com.skymall.rest.solr;

import java.io.IOException;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

public class TestSolr {

	/**
	 * 测试添加索引库
	 * solr中没有修改的方法   添加中修改（solr是如果添加的id相同，会先删除后添加，来实现修改的效果）

	 * 
	 * 
	 */
//	@Test
//	public void addDocument() throws SolrServerException, IOException{
//		//创建一连接
//		SolrServer solrServer=new HttpSolrServer("http://192.168.203.11:8080/solr");
//		
//		//创建文档对象
//		SolrInputDocument document=new SolrInputDocument();
//		document.addField("id","test001" );
//		document.addField("item_title", "测试商品001");
//		document.addField("item_price", 99999);
//		document.addField("item_desc","顶级商品就在天空商城");
//		//把文档对象添加到索引库
//		solrServer.add(document);
//		//提交
//		solrServer.commit();
//	}
	/**
	 * 测试索引键的维护                                                                                                                                                                         
	 */
//	@Test
//	public void deletedocument() throws SolrServerException, IOException{
//		
//
//		SolrServer solrServer=new HttpSolrServer("http://192.168.203.11:8080/solr");
//		
//		solrServer.deleteById("test001");
//		//solrServer.deleteByQuery("*:*");
//		solrServer.commit();
//	}
//	@Test
//	public void queryDocument() throws SolrServerException{
//		//创建查询对象
//		SolrServer solrService=new HttpSolrServer("http://192.168.203.11:8080/solr");
//		
//		//创建查询条件
//		SolrQuery solrQuery=new SolrQuery();
//		//查询条件
//		solrQuery.setQuery( "*:*");
//		//分页开始
//		solrQuery.setStart(2);
//		//页大小
//		solrQuery.setRows(5);
//		
//		//执行查询并返回结果
//		QueryResponse  response=solrService.query(solrQuery);
//		
//		//取返回参数
//		SolrDocumentList results = response.getResults();
//		
//		//总条数
//		System.out.println("总条数="+results.getNumFound());
//		for (SolrDocument document : results) {
//			System.out.println("id="+document.get("id"));
//			System.out.println("price="+document.get("item_price"));
//			System.out.println("title="+document.get("item_title"));
//			System.out.println("image="+document.get("item_image"));
//		}
//		
//	}
	
}
