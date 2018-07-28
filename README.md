# ElasticSearch
### Indexing Gmail Promotions Tabs to elasticsearch.

Integrate Gmail Api , which was developed by google for developers.

To run this project first enable the gmail api, so that java application can communicate with the gmail .

Click on the below link to enable gmail and give a project name and product name.
https://developers.google.com/gmail/api/quickstart/java

Next, it will generate the client_secret.json file.Then,copy and paste that file in org.elastic.authorization folder.

After that replace the application name in config.properties file with your project name and  enter your Gmail id in properties file.

Run the ElaticSearch by navigating to ElasticSearch bin folder and click on ElasticSearch.bat

Run the Kibana by navigating to Kibana bin folder and click on Kibana.bat

Project consists of following features:

1.  Get all promotion count from the gmail promotion tab

2.  Fetch promotions by specific keyword and filter by date

### Tools 
     1.Java 8
     2.ElasticSearch
     3.Kibana
#### Library
      1.Java High Rest client to integrate ElasticSearch in java
