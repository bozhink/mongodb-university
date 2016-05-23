var MongoClient = require('mongodb').MongoClient,
    assert = require('assert');

MongoClient.connect('mongodb://localhost:27017/school', function(err, db) {
    assert.equal(err, null);
    console.log("Successfully connected to MongoDB.");

    var projection = { "_id":0, "student": 1 },
        cursor = db.collection("grades").find({});
    cursor.project(projection);
    cursor.sort({"grade": -1});
    cursor.skip(4);
    cursor.limit(2);

    cursor.forEach(
        function(doc) {
            console.log(doc);
        },
        function(err) {
            assert.equal(err, null);
            return db.close();
        }
    );

});