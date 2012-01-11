var 
    server      = require('./chat-server'),
    handlers    = require('./handlers'),
    router      = require('./router');

var handleUrl = {};

handleUrl['/'] = handlers.handleGetAllMessages;
handleUrl['/register-user'] = handlers.handleRegisterUser;
handleUrl['/new-message'] = handlers.handleNewMessage;
handleUrl['/get-message'] = handlers.handleGetMessage;
handleUrl['/all-messages'] = handlers.handleGetAllMessages;
handleUrl['/all-users'] = handlers.handleGetAllUsers;
handleUrl['/css/screen.css'] = handlers.handleStaticCss;

server.start(8080, router.route, handleUrl);

