kup = require 'coffeekup'

module.exports.webpage = kup.render ->
    doctype 5
    html ->
        head ->
            meta charset: 'utf-8'
            title 'hello-backbonejs'
            style '''
                body {font-family: sans-serif}
            '''
            script src:'https://ajax.googleapis.com/ajax/libs/jquery/1.6.1/jquery.min.js'
            script src:'http://ajax.cdnjs.com/ajax/libs/json2/20110223/json2.js'
            script src:'http://ajax.cdnjs.com/ajax/libs/underscore.js/1.1.6/underscore-min.js'
            script src:'http://ajax.cdnjs.com/ajax/libs/backbone.js/0.3.3/backbone-min.js'

        body -> 
            script src:'./helloworld.coffee'
            h1 'Hello World node.js and backbone and Coffeescript!!!'

