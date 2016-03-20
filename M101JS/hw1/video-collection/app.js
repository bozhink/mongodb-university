var express = require('express'),
	app = express(),
	engines = require('consolidate'),
	bodyParser = require('body-parser'),
	mongoClient = require('mongodb').MongoClient,
	assert = require('assert');

app.engine('html', engines.nunjucks);
app.set('view engine', 'html');
app.set('views', __dirname + '/views');
app.use(bodyParser.urlencoded({ extended: true }));
app.use('/bower_components',  express.static(__dirname + '/bower_components'));
app.use('/css',  express.static(__dirname + '/css'));

function errorHandler(err, request, response, next) {
	console.error(err.message);
	console.error(err.stack);
	response.status(500)
		.render('error-template', { error: err });
}

app.use(errorHandler);

mongoClient.connect('mongodb://localhost:27017/video', function (err, db) {
	assert.equal(null, err);
	console.log('Successfully connected to MongoDB.');

	app.get('/', function (request, response) {

		db.collection('movies')
			.find({})
			.toArray(function (err, docs) {
				response.render('movies', { movies: docs });
			});

	});

	app.get('/add_movie', function (request, response) {
		response.render('add-movie', {});
	});

	app.post('/add_movie', function (request, response, next) {
		var title = '' + request.body.title,
			year = request.body.year | 0,
			imdb = '' + request.body.imdb;

		if (title.length < 1) {
			
			next('Error: Please provide a valid title.');
		
		} else if (2100 < year || year < 1900) {
		
			next('Error: Please provide a valid year.');
		
		} else if (imdb.length < 1) {
		
			next('Error: Please provide a valid ImDB ID.');
		
		} else {

			db.collection('movies')
				.insertOne({
					title: title,
					year: year,
					imdb: imdb
				}, function (err, result) {
					response.redirect('/');
				});

		}
	});

	app.use(function (request, response) {
		response.sendStatus(404);
	});

	var server = app.listen(3000, function () {
		var port = server.address().port;
		console.log('Express server is listening on port %s.', port);
	})
});