package com.iedu.config;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;

@Configuration
public class SpringMongoConfig extends AbstractMongoConfiguration implements DisposableBean{
	private static MongoClient client = null;
	private static GridFsTemplate gridFsTemplate = null;
	
	@Bean
	public GridFsTemplate gridFsTemplate() throws Exception {
		if(gridFsTemplate == null)
			gridFsTemplate = new GridFsTemplate(mongoDbFactory(), mappingMongoConverter()); 
		return gridFsTemplate;
	}

	@Override
	public String getDatabaseName() {
		return "iedu_group_net";
	}

	@Override
	@Bean
	public Mongo mongo() throws Exception {
		if(client == null)
			client = new MongoClient("127.0.0.1"); 
		return client;
	}
	
	/**
     * close Mongo client to avoid memory leaks
     */
    @Override
    public void destroy() {
        System.out.println("Shutdown Mongo DB connection");
        client.close();
        System.out.println("Mongo DB connection shutdown completed");
    }
}