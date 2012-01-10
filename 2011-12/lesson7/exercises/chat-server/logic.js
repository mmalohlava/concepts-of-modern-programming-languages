var 
    DirtyDB = require('dirty').Dirty;
var 
    users = new DirtyDB('users.dirty');
var 
    messages = new DirtyDB('messages.dirty');

module.exports = {

    addUser: function (username, name, surname) {
        users.set(username, {name: name, surname: surname});
    },

    newMessage: function (username, message) {
        var timestamp = new Date;
        var id = username + '_' + timestamp.getTime();
        messages.set(id, {username: username, message: message, timestamp: timestamp});
    },

    getMessages: function (username) {
    },

    getAllMessages: function (cb) {
        messages.forEach(function(key, val) {
            cb(val["username"], val["message"], val["timestamp"]);
        });
    },

    getAllUsers: function (cb) {
        users.forEach(function(key, val) {
            cb(key, val["name"], val["surname"]);
        });
    },

    userExists: function (username) {
        if (users.get(username) === undefined) {
            return false;
        } else {
            return true;
        }
    }
}

