var mongoClient = require('mongodb').MongoClient,
	assert = require('assert');

mongoClient.connect('mongodb://localhost:27017/video', function (err, db) {
	assert.equal(null, err);
	console.log('Successfully connected to server');

	db.collection('movie').find({}).toArray(function (err, docs) {
		docs.forEach((doc) => console.log(doc.title));

		db.close();
	});

	console.log("Called find()");
});
