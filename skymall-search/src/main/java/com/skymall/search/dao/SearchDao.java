package com.skymall.search.dao;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;

import com.skymall.search.pojo.SearchResult;

public interface SearchDao {
	public SearchResult search(SolrQuery query) throws SolrServerException;
}
