var express = require('express'),
    app = express(),
    engines = require('consolidate'); // Templating library for Express

app.engine('html', engines.swig);
app.set('view engine', 'html');
app.set('views', __dirname + '/views');

// Handler for internal server errors
function errorHandler(err, request, response, next) {
	console.error(err.message);
	console.error(err.stack);
	response.status(500)
		.render('error_template', { error: err });
}

app.use(errorHandler);

app.get('/:name', function (request, response, next) {
	var name = request.params.name,
		getvar1 = request.query.getvar1,
		getvar2 = request.query.getvar2;

	response.render('hello', {
		name: name,
		getvar1: getvar1,
		getvar2: getvar2
	});
});

var server = app.listen(3000, function () {
	var port = server.address().port;
	console.log('Express server is listening on port %s.', port);
});
