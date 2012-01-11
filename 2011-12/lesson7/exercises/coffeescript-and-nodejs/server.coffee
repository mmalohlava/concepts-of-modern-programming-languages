http = require 'http'
fs = require 'fs'
cs = require('coffee-script')
view = require './view'

server = http.createServer (req, res) ->
    if req.method is "GET"
        if req.url is "/"
            res.writeHead 200, "Content-Type": "text/html"
            res.write view.webpage
            res.end()
            return
        else if req.url is "/helloworld.coffee"
            fs.readFile ".#{req.url}", 'utf-8', (err, data) ->
                if err then throw err
                compiledContent = cs.compile data
                res.writeHead 200, "Content-Type": "application/javascript"
                res.write compiledContent
                res.end()
            return

    res.writeHead 404, "Content-Type": "text/html"
    res.write "404 Not found"
    res.end()


server.listen 8080
console.log "Server running at", server.address()
