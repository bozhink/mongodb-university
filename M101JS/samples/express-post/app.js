var express = require('express'),
    app = express(),
    engines = require('consolidate'), // Templating library for Express
    bodyParser = require('body-parser');

app.engine('html', engines.swig);
app.set('view engine', 'html');
app.set('views', __dirname + '/views');
app.use(bodyParser.urlencoded({ extended: true }));

// Handler for internal server errors
function errorHandler(err, request, response, next) {
	console.error(err.message);
	console.error(err.stack);
	response.status(500)
		.render('error_template', { error: err });
}

app.use(errorHandler);

app.get('/', function (request, response, next) {
	response.render('fruit_picker', {
		fruits: [
			'apple',
			'orange',
			'banana',
			'peach'
		]
	});
});

app.post('/favorite_fruit', function (request, response, next) {
	var favorite = request.body.fruit;
	if (typeof favorite === 'undefined') {
		next('Error: Please choose a valid fruit!')
	} else {
		response.send('Your favorite fruit is ' + favorite);
	}
});

var server = app.listen(3000, function () {
	var port = server.address().port;
	console.log('Express server is listening on port %s.', port);
});
