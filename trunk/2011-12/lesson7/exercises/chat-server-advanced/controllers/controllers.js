//
//Controllers
//
var NodeChatController = {
    init: function() {
        // create Socket.IO
        this.socket = io.connect();
        var mysocket = this.socket;

        // create client model
        this.model = new models.NodeChatModel();
        // bind the view
        this.view = new NodeChatView({model: this.model, socket: this.socket, el: $('#content')});
        var view = this.view;

        // register events from the server and call view handles
        this.socket.on('initial', function(messages) {view.handleInitialMsg(messages)});
        this.socket.on('chat', function(message) {view.handleChatMsg(message)});
        this.socket.on('update', function(clients) {view.handleUpdate(clients)});

        this.view.render();

        return this;
    }
};

//
// Bootstrap the app - register after document is loaded!
//
$(document).ready(function () {
    window.app = NodeChatController.init({});
});

