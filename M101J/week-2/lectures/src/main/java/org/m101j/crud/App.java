package org.m101j.crud;

import static java.util.Arrays.asList;

import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.BSON;
import org.bson.BsonDocument;
import org.bson.Document;

/**
 * Created by bozhin on 6/6/16.
 */
public class App {
    public static void main(String[] args) {
        System.out.println("Hello from Maven");

        MongoClientOptions mongoClientOptions = MongoClientOptions.builder()
                .connectionsPerHost(100)
                .build();

        // MongoClient mongoClient = new MongoClient();
        // MongoClient mongoClient = new MongoClient("localhost", 27017);
        // MongoClient mongoClient = new MongoClient(new ServerAddress("localhost", 27017));
        // MongoClient mongoClient = new MongoClient(asList(new ServerAddress("localhost", 27017)));
        // MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
        MongoClient mongoClient = new MongoClient(new ServerAddress(), mongoClientOptions);

        // MongoDatabase object is immutable
        // MongoDatabase db = mongoClient.getDatabase("test");
        MongoDatabase db = mongoClient.getDatabase("test")
                .withWriteConcern(new WriteConcern(1, 1000))
                .withReadConcern(new ReadConcern(ReadConcernLevel.LOCAL));

        // MongoCollection collection = db.getCollection("test");
        // MongoCollection collection = db.getCollection("test")
        //        .withReadPreference(ReadPreference.nearest());
        // MongoCollection<Document> collection = db.getCollection("test");
        MongoCollection<BsonDocument> collection = db.getCollection("test", BsonDocument.class); // type safe



        System.out.println("Hello from Mongo");
    }
}
