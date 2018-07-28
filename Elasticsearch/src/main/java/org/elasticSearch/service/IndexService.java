package org.elasticSearch.service;

import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;

public interface IndexService {

	public CreateIndexResponse insertPromotionIndex();
}
