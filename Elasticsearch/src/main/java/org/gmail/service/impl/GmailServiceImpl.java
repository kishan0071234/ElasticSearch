package org.gmail.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.elastic.properties.ConfigProperties;
import org.elasticSearch.constants.GmailTabs;
import org.elasticSearch.services.impl.RestClientService;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.gmail.services.GmailService;

import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.model.ListMessagesResponse;
import com.google.api.services.gmail.model.Message;

public class GmailServiceImpl implements GmailService {

    String user = "me";
    List<String> promotionIds;
    ListMessagesResponse response =null;
    List<Message> messages =null;
	IndexResponse resp = null;

    //To fetch singleton gmail service
    Gmail gmailService=HttpGmailService.instance().getGmailService();
    //To get the elastic search client portal
	RestHighLevelClient client=	RestClientService.getInstance().getRestClient();
	//To ge the property file
    ConfigProperties properties=ConfigProperties.getInstance();


/*
 * (non-Javadoc)
 * @see org.gmail.services.GmailService#getPromotionIds()
 * To fetch all promotions id from gmail
 */
    @Override
      public ListMessagesResponse getPromotionIds() {
    	  promotionIds=Arrays.asList(GmailTabs.PROMOTIONS);
    	  try {
			 response = gmailService.users().messages().list(properties.getValue("elasticsearch.emailid"))
			          .setLabelIds(promotionIds).execute();
		      messages = new ArrayList<Message>();
			while(response.getMessages()!=null) {
			messages.addAll(response.getMessages());
			          if (response.getNextPageToken() != null) {
			            String pageToken = response.getNextPageToken();
			            response = gmailService.users().messages().list(properties.getValue("elasticsearch.emailid"))
			                    .setLabelIds(promotionIds).setPageToken(pageToken).execute();

			          } else {
			            break;
			          }
				}
			      
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	  return response;
      }
    
    @Override
    public IndexResponse insertPromotionIdsToElastic() {
    	
   getPromotionIds();// get all promotions ids
   
   List<String> promotionIds=messages.stream().map(id->id.getId()).collect(Collectors.toList());
   promotionIds.forEach(promoId->{
		Message message = null;
		try {
			message = gmailService.users().messages().get(properties.getValue("elasticsearch.emailid"),promoId).execute();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	
	IndexRequest indexRequest = new IndexRequest(properties.getValue("elasticsearch.promotion.index"),properties.getValue("elasticsearch.promotion.type"));
	indexRequest.source(message.toString(), XContentType.JSON);
	try {
		resp = client.index(indexRequest);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
   });
   
   return resp;
    }
}