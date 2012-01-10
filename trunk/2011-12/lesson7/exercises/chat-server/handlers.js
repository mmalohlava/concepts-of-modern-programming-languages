var logic = require('./logic');
var view  = require('./view');
var fs = require('fs');

function writeStandardHeader(response) {
    writeHeader(response, 200, 'text/html');
}

function writeHeader(response, code, contentType) {
    response.writeHeader(code, {'Content-Type' : contentType} );
}

function handleRegisterUser(parameters, response) {
        var username = parameters['username'];
        var name = parameters['name'];
        var surname = parameters['surname'];
        
        logic.addUser(username, name, surname);

        writeStandardHeader(response);
        response.write('User ' + username + ' added.');
        response.end();
}

function handleNewMessage(parameters, response) {
    var username = parameters['username'];
    var message = parameters['message'];

    writeStandardHeader(response);
    if (logic.userExists(username)) {
        logic.newMessage(username, message);
        response.write('OK!');                
    } else {
        response.write("User " + username + " was not found!");
    }

    response.end();
}

function handleGetMessages(username) {
}

function handleGetAllMessages(params, response) {
    
    var messages = [];
    logic.getAllMessages(function(username, msg, ts) {
       // response.write('User ' + username + ' said "' + msg + '" on ' + ts);
       messages.push( { username: username, text: msg, ts: ts} );
    });

    writeStandardHeader(response);
    response.write(
        view.render(view.mainLayout, {
        title: "Chat server",
        message: undefined,
        body: view.render(view.messagesLayout, {
            messages: messages
            })
        })
    );
    response.end();
}

function handleGetAllUsers(params, response) {
    var users = [];
    logic.getAllUsers(function (username, name, surname) {
        users.push({ username: username, name: name, surname: surname});
    });
    
    writeHeader(response, 200, 'application/json');
    var jsonResponse = JSON.stringify(users);
    response.write(jsonResponse);
    console.log(jsonResponse);
    response.end();
}

function handleStaticCss(params, response) {
    writeHeader(response, 200, 'text/css');
    response.write(fs.readFileSync('./css/screen.css', 'utf-8'));
    response.end();
}

exports.handleRegisterUser = handleRegisterUser
exports.handleNewMessage = handleNewMessage
exports.handleGetMessages = handleGetMessages
exports.handleGetAllMessages = handleGetAllMessages
exports.handleGetAllUsers = handleGetAllUsers
exports.handleStaticCss = handleStaticCss 

