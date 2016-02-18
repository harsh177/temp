package com.bitwise.tdm.connection.impl;
import java.io.InputStream;

import java.util.Properties;

import org.apache.felix.dm.annotation.api.Component;
import org.jongo.Jongo;

import com.bitwise.tdm.connection.api.MongoDBConnection;
import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;
@Component
public class MongoDBConnectionImpl implements MongoDBConnection{
	@Override
	public MongoDatabase getDatabaseConnection() {
		MongoDatabase mongoDatabase=null;
		try
		{
			Properties prop = new Properties();
			InputStream input =MongoDBConnectionImpl.class.getResourceAsStream(PROPERTY_FILE_NAME);
				// load a properties file
			if(input==null){
	            System.out.println("Sorry, unable to find " +PROPERTY_FILE_NAME);
		        return mongoDatabase;
			}
			prop.load(input);
			System.out.println(prop.getProperty("database.host"));
			System.out.println(prop.getProperty("database.port"));
			System.out.println(prop.getProperty("database.dbname"));
			System.out.println(prop.getProperty("database.username"));
			System.out.println(prop.getProperty("database.password"));
			
			String connectionURI="mongodb://"+prop.getProperty("database.host")+":"+prop.getProperty("database.port")+"/"+prop.getProperty("database.dbname")+"";
			MongoClientURI mongoClientURI=new MongoClientURI(connectionURI);
			MongoClient mongoClient=new MongoClient(mongoClientURI);
			mongoDatabase=mongoClient.getDatabase("test");
		}catch(Exception e)
		{
			System.out.println(e+"Unable to create a connection");
			return mongoDatabase;
		}
		System.out.println("Connection Created Successfully");
		return mongoDatabase;
	}
}
