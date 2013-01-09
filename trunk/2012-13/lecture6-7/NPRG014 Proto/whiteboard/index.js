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

var ioApp = io.listen(app);
ioApp.sockets.on('connection', function(socket) {
		console.log('connection made');

		socket.on('drev', function (drev) {
			drevReassignProto(drev);
			console.log('drev: ' + drev);
			socket.broadcast.emit('drev', drev);
		});
});

app.listen(8080);

/* ASSIGNMENT:
 * Modify index.js and client.html in the following way:
 * - remember drawing events on the server and replay them whenever a new client connects
 * - add a button refresh, which clears the canvas (using "wtbdCtx.clearRect(0, 0, wtbd.width, wtbd.height);") and asks the server to replay the events
 * - add a button clear, which tells the server to forget all drawing events and to notify all the clients to refresh their state
 */