/**
 * 
 */
package com.sabtok.persistance.mongo;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

/**
 * @author Sunil
 *
 * NewMongoClient.java Aug 6, 2020 12:07:03 PM
 */
public class NewMongoClient extends MongoClient {

	public NewMongoClient(MongoClientURI uri) {
		super(uri);
	}
	
	/* (non-Javadoc)
	 * @see com.mongodb.Mongo#close()
	 */
	@Override
	public void close() {
		System.out.println(Thread.currentThread()+" released the connection. ");
	}
	
	void _close() {
		super.close();
	}
}
