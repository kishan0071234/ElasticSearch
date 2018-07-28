package org.elasticSearch.service;

import org.elasticsearch.action.search.SearchResponse;

public interface SearchService {

	//public SearchRequest getSearchRequest(String index,String types);
	public SearchResponse getPromotionCounts();
	public SearchResponse getPromotionsByDates(String from,String to,String keyword);
}
