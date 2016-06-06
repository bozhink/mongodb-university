package org.m101j.crud;

import org.bson.BsonDocument;
import org.bson.BsonString;
import org.bson.Document;
import org.bson.types.ObjectId;

import org.m101j.utils.Helpers;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

/**
 * Created by bozhin on 6/6/16.
 */
public class DocumentTest {
    public static void main(String[] args) {
        Document document = new Document()
                .append("str", "Hello, MongoDB");
        document.append("int", 42)
                .append("l", 1L)
                .append("double", 1.0)
                .append("bool", true)
                .append("date", new Date())
                .append("objectId", new ObjectId())
                .append("null", null)
                .append("embeddedDoc", new Document("x", 0))
                .append("list", Arrays.asList(1, 2, 3));

        String str = (String)document.get("str");
        System.out.println(str);
        System.out.println(document.get("int"));
        System.out.println(document.get("date"));
        System.out.println(document.get("objectId"));
        System.out.println(document.get("list"));

        Helpers.printJson(document);

        BsonDocument bsonDocument = new BsonDocument("str", new BsonString("MongoDD, Hello"));
    }
}
