package com.skymall.search.service;

import org.apache.solr.client.solrj.SolrQuery;

import com.skymall.search.pojo.SearchResult;

public interface SearchService {

	SearchResult search(String queryString ,int page,int rows) throws Exception;
	
}
