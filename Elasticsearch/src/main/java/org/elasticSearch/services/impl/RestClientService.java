package org.elasticSearch.services.impl;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

/*
 * Singleton design for the rest client
 */
public class RestClientService {

	private static RestHighLevelClient client =null;
	private static RestClientService restClient=null;
	private RestClientService() {
		
		client = new RestHighLevelClient(
		        RestClient.builder(
		                new HttpHost("localhost", 9200, "http"),
		                new HttpHost("localhost", 9201, "http")));
	}
	
	public static RestClientService getInstance() {
		
		if(restClient==null) {
			
			restClient=new RestClientService();
		}
		return restClient;
	}
	
	public RestHighLevelClient getRestClient() {
		
		return client;
	}
	
	
}
