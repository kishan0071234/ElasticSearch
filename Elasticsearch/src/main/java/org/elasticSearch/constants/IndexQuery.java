package org.elasticSearch.constants;

public interface IndexQuery {

	public static final String PROMOTION_INDEX= "{\n" +
	        "  \"promotions\": {\n" +
	        "    \"properties\": {\n" +
	        "      \"labelsId\": {\n" +
	        "        \"type\": \"text\"\n" +
	        "      },\n" +
	        "      \"snippet\": {\n" +
	        "        \"type\": \"text\"\n" +
	        "      },\n" +
	        "      \"payload\": {\n" +
	        "        \"type\": \"nested\",\n" +
	       
			"    \"properties\": {\n" +
	       "      \"headers\": {\n" +
	       "        \"type\": \"nested\",\n" +
			"    \"properties\": {\n" +
	         "      \"name\": {\n" +
	        "        \"type\": \"text\",\n" +
			  "      \"fields\": {\n" +
			  "      \"keyword\": {\n" +
		        "        \"type\": \"keyword\"\n" +
		        "      }\n" +
				 "      }\n" +
	        "      },\n" +
			  "      \"value\": {\n" +
	        "        \"type\": \"text\",\n" +
			  "      \"fields\": {\n" +
			  "      \"keyword\": {\n" +
		        "        \"type\": \"keyword\"\n" +
		        "      }\n" +
				 "      }\n" +
	        "      }\n" +
			 "      }\n" +
	        "    }\n" +
		   
			 "      }\n" +
	        "    }\n" +
	        "    }\n" +
	        "  }\n" +
	        "}";
}
