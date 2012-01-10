var app = require('express').createServer()
    , jade = require('jade')
    , io = require('socket.io').listen(app)
    , _ = require('underscore')._
    , Backbone = require('backbone')
    , models = require('./models/models')
    , Dirty = require('dirty').Dirty;
    

//configure express to use jade
app.set('view engine', 'jade');
app.set('view options', {layout: false});


//setup routes
app.get('/*.(js|css)', function(req, res){
    res.sendfile('./'+req.url);
});

app.get('/', function(req, res){
    res.render('index');
});

//create local state
var activeClients = 0;
var nodeChatModel = new models.NodeChatModel();

// create database
var nextChatEntryId = 0;
var chatentries = new Dirty('chatentries.dirty');

// load existing chat entries - has to be load after DB's event 'load' is emitted !
chatentries.on('load', function() {
    var id = 0;
    chatentries.forEach(function(key,val) {
        var chat = new models.ChatEntry();
        chat.mport(val);
        nodeChatModel.chats.add(chat);
        id = key;
    });
    
    console.log('* Revived ' + nodeChatModel.chats.length + ' chats');

    nextChatEntryId = id;
});

io.sockets.on('connection', function(client){
    activeClients += 1;
    console.log('Number of active clients', activeClients);
    client.on('disconnect', function(){clientDisconnect(client)});
    client.on('message', function(msg){chatMessage(client, msg)});

    // send to client the list of messages
    client.emit('initial', nodeChatModel.xport());
    // send to all notification about new client
    io.sockets.emit('update', activeClients);
});

function chatMessage(client, msg){
    var chat = new models.ChatEntry();
    chat.mport(msg);

    nextChatEntryId += 1;

    chat.set({id: nextChatEntryId});
    nodeChatModel.chats.add(chat);
    
    var expandedMsg = chat.get('id') + ' ' + chat.get('name') + ': ' + chat.get('text');
    console.log('(' + client.id + ') ' + expandedMsg);

    chatentries.set(nextChatEntryId, chat.xport() );
    
    io.sockets.emit('chat', chat.xport() );
}

function clientDisconnect(client) {
    activeClients -= 1;
    client.broadcast.emit(activeClients)
}

app.listen(8000);

