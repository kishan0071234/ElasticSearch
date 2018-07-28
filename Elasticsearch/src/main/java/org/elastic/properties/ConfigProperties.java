package org.elastic.properties;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigProperties {

    private static ConfigProperties instance = null;
    private Properties configProperties = null;
    private static final String propertyFile="config.properties";


    protected ConfigProperties() {
    	System.out.println("init");
    	configProperties=new Properties();
	     InputStream inputStream=  this.getClass().getResourceAsStream("/org/elastic/properties/"+propertyFile);
	       try {
	    	   if(null!=inputStream) {
	    		   configProperties.load(inputStream);
	    	   }
	    	   else {
	    		   throw new FileNotFoundException("Property File:"+propertyFile+"not found in the classpath");
	    	   }
                } catch (Exception e) {
	                System.out.println("Exception: " + e);
                } finally { 
	           try {
		             inputStream.close();
	            } catch (IOException e) {
		// TODO Auto-generated catch block
		          e.printStackTrace();
	}
      }
	    }

    public static ConfigProperties getInstance() {
        if(instance == null) {
            instance = new ConfigProperties();
        }
        return instance;
    }

    public String getValue(String key) {
        return configProperties.getProperty(key);
    }

}