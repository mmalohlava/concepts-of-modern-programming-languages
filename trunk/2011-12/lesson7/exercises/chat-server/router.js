var
  url       =  require('url');
var 
  querystring = require('querystring');

function route(handle, request, response) {
    var pathname = url.parse(request.url).pathname;

    console.log('Router handling: ' + pathname);

    if (typeof handle[pathname] == 'function') {
        var params   = querystring.parse(url.parse(request.url).query);
        handle[pathname](params, response);
    } else {
        console.log("No request handler found for " + pathname);
        response.writeHead(404, {"Content-Type": "text/plain"});
        response.write("404 Not found");
        response.end();
    }
}

exports.route = route
