package org.elasticSearch.services.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.elastic.pojo.Source;
import org.elasticSearch.service.PromotionService;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.search.aggregations.bucket.filter.ParsedFilter;
import org.elasticsearch.search.aggregations.bucket.nested.ParsedNested;
import org.elasticsearch.search.aggregations.bucket.terms.ParsedStringTerms;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class PromotionServiceImpl implements PromotionService {

	int i=0,j=0;
	static SearchServiceImpl searchService =null;
	
	static {
		 searchService=new SearchServiceImpl();
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.elasticSearch.service.PromotionService#getAllPromotionsCount()
	 * Fetch all the promotion count from gmail by 
	 * default it will give 10 results .
	 * For more set size =n(your required size).
	 */
	public void getAllPromotionsCount() {
		
	SearchResponse searchResponse=searchService.getPromotionCounts();
      if(Integer.valueOf(200).equals(searchResponse.status().getStatus())){
		ParsedNested parsedNested =	searchResponse.getAggregations().get("headers");
	ParsedFilter parsedFilter=parsedNested.getAggregations().get("key");
	ParsedStringTerms parsedStringTerms=parsedFilter.getAggregations().get("value");
	Map<String,Integer> map=new LinkedHashMap<>();
	
	if(null!=parsedStringTerms.getBuckets()) {
	parsedStringTerms.getBuckets().forEach(entry->{
		map.put(entry.getKeyAsString(),(int) entry.getDocCount());
		String key = entry.getKeyAsString();     
		String[] split=key.split("<");// bucket key
		System.out.println(j+".Promotion Name: \t"+split[0]+"\n \tcount:"+entry.getDocCount());
		j++;
	});
	}
}
}
	
/*
 * (non-Javadoc)
 * @see org.elasticSearch.service.PromotionService#getPromotionsForFilteredDates(java.lang.String, java.lang.String, java.lang.String)
 * Fetch promotions with keyword by filtering date.
 */
	public void getPromotionsForFilteredDates(String from,String to,String keyword) {
		
	SearchResponse searchResponse=searchService.getPromotionsByDates(from, to, keyword);
	 if(Integer.valueOf(200).equals(searchResponse.status().getStatus())){
	List<String> deals=new ArrayList<>();
if(null!=searchResponse.getHits()) {
	searchResponse.getHits().forEach(hits->{
	    ObjectMapper mapper = new ObjectMapper();
	    try {
			Source readValue = mapper.readValue(hits.getSourceAsString(), Source.class);
			if(null!=readValue.getPayload()&& null!=readValue.getPayload().getHeaders()) {
				String snippet=readValue.getSnippet();
				readValue.getPayload().getHeaders().forEach(header->{
					if(header.getName().equalsIgnoreCase("Subject")) {
						deals.add(i+".\t"+header.getValue()+"\n"+snippet+"\t------------");
						i++;
					}
				});
			}
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	});
	deals.forEach(ww->System.out.println(ww));
}
}}
}
