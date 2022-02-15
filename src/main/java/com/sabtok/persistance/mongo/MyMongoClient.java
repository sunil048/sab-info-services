/**
 * 
 */
package com.sabtok.persistance.mongo;

import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;

/**
 * @author Sunil
 *
 * MyMongoClient.java Aug 6, 2020 11:56:08 AM
 */
public class MyMongoClient extends MongoClient{

	MyMongoClient(ServerAddress server){
		super(server);
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
