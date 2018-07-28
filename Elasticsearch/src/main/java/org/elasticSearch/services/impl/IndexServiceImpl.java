package org.elasticSearch.services.impl;

import java.io.IOException;

import org.elastic.properties.ConfigProperties;
import org.elasticSearch.constants.IndexQuery;
import org.elasticSearch.service.IndexService;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentType;

public class IndexServiceImpl implements IndexService {

	RestHighLevelClient client=	RestClientService.getInstance().getRestClient();
	CreateIndexResponse createIndexResponse = null;
    ConfigProperties properties=ConfigProperties.getInstance();
	public CreateIndexResponse insertPromotionIndex() {
		
		CreateIndexRequest request = new CreateIndexRequest(properties.getValue("elasticsearch.promotion.index")); 
		request.settings(Settings.builder() 
			    .put("index.number_of_shards", 3)
			    .put("index.number_of_replicas", 2)
			);
		request.mapping(properties.getValue("elasticsearch.promotion.type"), IndexQuery.PROMOTION_INDEX, XContentType.JSON);
		try {
			createIndexResponse = client.indices().create(request);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return createIndexResponse;
	}
}
