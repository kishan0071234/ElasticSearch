package org.elasticSearch.service;

public interface PromotionService {

	public void getAllPromotionsCount();
	public void getPromotionsForFilteredDates(String from,String to,String keyword);
}
