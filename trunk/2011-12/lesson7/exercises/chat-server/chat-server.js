var 
  http      =  require('http'),
  util      =  require('util');

function sleep(timeMilis) {
  var now = new Date().getTime();
  console.log("Waiting...");      
  while(new Date().getTime() < now + timeMilis) {
  }
  console.log("Waiting done!");
}

function start(port, route, handle) {
 
    function onRequest(req,res) {
        console.log('REQ: ' + req.url);

        //sleep(2000);
        
        route(handle, req, res);
    }

    http.createServer(onRequest).listen(port);
    console.log('Server started on port ' + port);
}

module.exports.start = start;

