package org.m101j.crud;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import static java.util.Arrays.asList;
import static org.m101j.utils.Helpers.printJson;

/**
 * Created by bozhin on 6/6/16.
 */
public class InsertTest {
    public static void main(String[] args) {
        MongoClient client = new MongoClient();
        MongoDatabase db = client.getDatabase("course");
        MongoCollection<Document> collection = db.getCollection("insertTest");

        collection.drop();

        Document smith = new Document("name", "Smith")
                .append("age", 30)
                .append("profession", "programmer");

        Document jones = new Document("name", "Jones")
                .append("age", 25)
                .append("profession", "hacker");

        printJson(smith);

        collection.insertOne(smith);
        printJson(smith);

        smith.remove("_id");

        collection.insertMany(asList(smith, jones));

        printJson(smith);
    }
}
