package org.gmail.service.impl;

import java.io.IOException;
import java.security.GeneralSecurityException;

import org.elastic.authoriztion.Authorization;
import org.elastic.properties.ConfigProperties;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.gmail.Gmail;


/*
 * SIngleton design class
 */
public class HttpGmailService {

	private static NetHttpTransport HTTP_TRANSPORT =null;
    private static Gmail gmail = null;
    private static HttpGmailService httpGmailService=null;
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    private static final String APPLICATION_NAME =ConfigProperties.getInstance().getValue("elasticsearch.applicationame") ;

	
    /*
     * constructor to authorize the gmail service
     */
	private HttpGmailService() {
		if(HTTP_TRANSPORT==null && gmail == null ) {
			
			try {
				HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
				Authorization authorization=new Authorization();
				gmail = new Gmail.Builder(HTTP_TRANSPORT, JSON_FACTORY,authorization.getCredentials(HTTP_TRANSPORT))
				         .setApplicationName(APPLICATION_NAME)
				         .build();
			} catch (GeneralSecurityException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static HttpGmailService instance() {
		if(httpGmailService==null) {
			httpGmailService=new HttpGmailService();
		}
		return httpGmailService;
	}
	
	public Gmail getGmailService() {
		return gmail;
	}
}