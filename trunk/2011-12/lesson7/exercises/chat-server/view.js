var jade = require('jade');
var fs = require('fs');

var mainLayoutFile = './templates/main.jade';
var mainLayout = jade.compile(fs.readFileSync(mainLayoutFile, 'utf-8'), {filename: mainLayoutFile} );

var messagesLayoutFile = './templates/messages.jade';
var messagesLayout = jade.compile(fs.readFileSync(messagesLayoutFile, 'utf-8'), {filename: messagesLayoutFile});

function render(template, locals) {
    return template(locals);
}

module.exports = {
        render: render,
        mainLayout: mainLayout,
        messagesLayout: messagesLayout
};
