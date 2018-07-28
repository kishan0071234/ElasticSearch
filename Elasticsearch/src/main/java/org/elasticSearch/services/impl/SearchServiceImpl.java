package org.elasticSearch.services.impl;

import java.io.IOException;

import org.apache.lucene.search.join.ScoreMode;
import org.elastic.properties.ConfigProperties;
import org.elasticSearch.service.SearchService;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;

public class SearchServiceImpl implements SearchService {
	
	//To fetch client service
	RestHighLevelClient client=	RestClientService.getInstance().getRestClient();
	SearchResponse response =null;
	ConfigProperties properties=ConfigProperties.getInstance();
	/*
	 * To get the search request 
	 */
	public static  SearchRequest getSearchRequest(String index, String types) {
		SearchRequest searchRequest = new SearchRequest(index); 
		searchRequest.types(types);
		return searchRequest;
	}
	
/*
 * (non-Javadoc)
 * @see org.elasticSearch.service.SearchService#getPromotionCounts()
 * To get all promotions count form the gmail promotions tab
 * By default it fetch top 10 promotions in descending order
 */
	@Override
	public SearchResponse getPromotionCounts() {
		SearchRequest search=getSearchRequest(properties.getValue("elasticsearch.promotion.index"), properties.getValue("elasticsearch.promotion.type"));
		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder(); 
		searchSourceBuilder.size(0).aggregation(AggregationBuilders.nested("headers", "payload.headers")
				.subAggregation(AggregationBuilders.filter("key", QueryBuilders.matchQuery("payload.headers.name", "From"))
				.subAggregation(AggregationBuilders.terms("value").field("payload.headers.value.keyword"))));
		search.source(searchSourceBuilder) ;
		
		try {
		 response=client.search(search);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response;
	}
	
	/*
	 * To fetch promotions by keyword
	 * and filter it by date
	 */
	@Override
	public SearchResponse getPromotionsByDates(String from,String to,String keyword) {
		
		SearchRequest searchRequest=getSearchRequest(properties.getValue("elasticsearch.promotion.index"), properties.getValue("elasticsearch.promotion.type"));
		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder(); 
		QueryBuilder rangeQ = QueryBuilders
                .rangeQuery("payload.headers.value")
                 .gte(from)
                 .lte(to)
                 .format("dd/MM/yyyy");		
		QueryBuilder qb = QueryBuilders
	            .boolQuery()
	            .should(QueryBuilders.matchQuery("payload.headers.name", "From"))
	            .should(QueryBuilders.matchQuery("payload.headers.value", keyword))
	            .must(QueryBuilders.matchQuery("payload.headers.name", "Date"))
	            .must(rangeQ);
		
		searchSourceBuilder.query(QueryBuilders.nestedQuery("payload.headers", qb, ScoreMode.None));
		searchRequest.source(searchSourceBuilder);

		try {
		 response=client.search(searchRequest);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response;
		
	}
}