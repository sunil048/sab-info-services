/**
 * 
 */
package com.sabtok.persistance.mongo;

import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.HashMap;
import java.util.Map;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

/**
 * @author Sunil
 *
 * MyMongoConnectionPool.java Aug 6, 2020 11:55:04 AM
 */
public class MyMongoClientPool {

	private static Map<String,MyMongoClient> connections = new HashMap<String,MyMongoClient>();
	private static Map<String,NewMongoClient> mongoConnections = new HashMap<String,NewMongoClient>();
	
	public static MongoClient getConnection(String connection) {
		System.out.println("Connection Details: "+connection);
		//Decoder decoder = Base64.getDecoder();
		//byte[] byteStr = decoder.decode(connection);
		//String connectionStr = new String(byteStr);
		
		MongoClientURI mongoURI = new MongoClientURI(connection);
		synchronized (mongoConnections) {
			NewMongoClient client = mongoConnections.get(connection);
			if (client == null) {
				try {
					client = new NewMongoClient(mongoURI);
				}catch (Exception e) {
					System.out.println("Not able to connect to mongoDB "+e.getMessage());
				}
				mongoConnections.put(connection, client);
			}
			return client;
		}
	}
	static void closeConnections() {
		synchronized (connections) {
		
			for (MyMongoClient m : connections.values()) {
				System.out.println("Closed "+m);
				m._close();
			}
		}
	}
	
}
