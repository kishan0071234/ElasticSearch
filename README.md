# Indexing Gmail Promotions Tabs to elasticsearch.

## What's this all about?
I recently looked at my gmail promotions tab and noticed that i have more than 10k emails of promotions ,but there is no way to filter the promotions by date and getting the total promotion count of all promoitons.

## Goal
Goal of this tutorial is to load all gmail promotions to ElasticSearch using indexing and start querying  the cluster to get a better picture of what's going on.

## Prerequisites
Set up Elasticsearch and make sure it's running at http://localhost:9200

(Optional) Set up Kibana and make sure it's running at http://localhost:5601 , if you want to run query

I use Java , kibana  and Java High rest client ElasticSearch Library  to import and query the data. 

Integrated Gmail Api , which was developed by google for developers.

## Alright, where do we start?
To run this project first enable the gmail api, so that java application can communicate with the gmail .

Click on the below link to enable gmail and give a project name and product name.
https://developers.google.com/gmail/api/quickstart/java

Next, it will generate the client_secret.json file.Then,copy and paste that file in org.elastic.authorization folder.

After that replace the application name in config.properties file with your project name and  enter your Gmail id in properties file.

Run the ElaticSearch by navigating to ElasticSearch bin folder and click on ElasticSearch.bat

Run the Kibana by navigating to Kibana bin folder and click on Kibana.bat

## Project consists of following features:

1.  Get all promotions name and  count from the gmail promotion tab

2.  Fetch promotions by specific keyword and filter by date

### Tools 
     1.Java 8
     2.ElasticSearch
     3.Kibana
#### Library
      1.Java High Rest client to integrate ElasticSearch in java
      
      
 ### Feedback
 email me at kishan007agarwal@gmail.com
