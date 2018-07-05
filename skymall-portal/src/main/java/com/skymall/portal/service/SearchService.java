package com.skymall.portal.service;

import com.skymall.portal.pojo.SearchResult;

public interface SearchService {

	SearchResult search(String query,int page);
	
}
