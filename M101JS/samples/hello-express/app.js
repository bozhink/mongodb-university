var express = require('express'),
	app = express(),
	engines = require('consolidate');

app.engine('html', engines.nunjucks);
app.set('view engine', 'html');
app.set('vews', __dirname + '/views');

app.get('/', function (request, response) {
	//// response.send('Hello World!');
	response.render('hello', { name: 'Hello world!'});
});

app.use(function (request, response) {
	response.sendStatus(404);
});

var server = app.listen(3000, function () {
	var port = server.address().port;
	console.log('Express server listening on port %s', port);
});