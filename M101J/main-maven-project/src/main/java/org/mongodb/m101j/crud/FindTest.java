package org.mongodb.m101j.crud;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

import static org.mongodb.m101j.utils.Helpers.printJson;

/**
 * Created by bozhin on 6/6/16.
 */
public class FindTest {
    public static void main(String[] args) {
        MongoClient client = new MongoClient();
        MongoDatabase db = client.getDatabase("course");
        MongoCollection<Document> collection = db.getCollection("insertTest");

        collection.drop();

        for (int i = 0; i < 10; ++i) {
            collection.insertOne(new Document("x", i));
        }

        System.out.println("Find one");
        Document first = collection.find(new Document("x", 1)).first();
        System.out.println("Find one");
        printJson(first);

        System.out.println("Find all with into");
        List<Document> all = collection.find().into(new ArrayList<Document>());
        for (Document doc : all) {
            printJson(doc);
        }

        System.out.println("Find all with iteration");
        MongoCursor<Document> cursor = collection.find().iterator();
        try {
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                printJson(doc);
            }
        } finally {
            cursor.close();
        }

        System.out.println("Count");
        long count = collection.count();
        System.out.println(count);
    }
}