var http = require('http');
var fs = require('fs');
var io = require('socket.io');
require('./draw_events.js');

var LineEvent = function(fromX, fromY, toX, toY) {
	this.fromX = fromX;
	this.fromY = fromY;
	this.toX = toX;
	this.toY = toY;
}

var EraseEvent = function(x, y) {
	this.x = x;
	this.y = y;
}
function staticHandler(request, response) {
	if (request.url == '/') {
		fs.readFile(__dirname + '/client.html', function(err, data) {
			response.writeHead(200, {'Content-Type': 'text/html'});
			response.write(data);
			response.end();
		});
	} else if (request.url == '/draw_events.js') {
		fs.readFile(__dirname + '/draw_events.js', function(err, data) {
			response.writeHead(200);
			response.write(data);
			response.end();
		});		
	}
}

var app = http.createServer(staticHandler);

var allDrews = new Array();

var ioApp = io.listen(app);
ioApp.sockets.on('connection', function(socket) {
		console.log('connection made');

		allDrews.forEach(function(x) {
			socket.emit('drev', x);
		});

		socket.on('drev', function (drev) {
			drevReassignProto(drev);
			console.log('drev: ' + drev);
			socket.broadcast.emit('drev', drev);
			allDrews.push(drev);
		});
		
		socket.on('replay', function() {
			allDrews.forEach(function(x) {
				socket.emit('drev', x);
			});
		});

		socket.on('clear', function() {
			allDrews = new Array();
			socket.emit('refresh')
			socket.broadcast.emit('refresh')
		});
});

app.listen(8080);


