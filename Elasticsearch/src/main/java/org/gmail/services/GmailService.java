package org.gmail.services;

import org.elasticsearch.action.index.IndexResponse;

import com.google.api.services.gmail.model.ListMessagesResponse;

public interface GmailService {

	 public ListMessagesResponse getPromotionIds();
	 public IndexResponse insertPromotionIdsToElastic();
}
