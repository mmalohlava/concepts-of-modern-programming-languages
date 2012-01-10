var 
  http      =  require('http'),
  util      =  require('util');

function start(port, route, handle) {
 
    function onRequest(req,res) {
        console.log('REQ: ' + req.url);
        //util.log("REQ" + req.url);
        
        route(handle, req, res);
    }

    http.createServer(onRequest).listen(port);
    console.log('Server started on port ' + port);
}

module.exports.start = start;

