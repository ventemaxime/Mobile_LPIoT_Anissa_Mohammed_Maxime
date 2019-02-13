jQuery(document).ready(function ($) {

    // https://www.npmjs.com/package/jquery-simple-websocket


    var connection = $.simpleWebSocket(
        {
            url: 'ws://localhost:8080',
            // url: 'ws://192.168.1.29:8080',
            // protocols: 'your_protocol', // optional
            // timeout: 20000, // optional, default timeout between connection attempts
            // attempts: 60, // optional, default attempts until closing connection
            // dataType: 'json' // optional (xml, json, text), default json
        }
    );

    connection.connect();

    // reconnected listening
    connection.listen(function (message) {
        console.log(message);
        addMessage(message.source.first_name, message.message, false);

    });


    $("#chat-send").click(function () {
        let token = $('#chat-name').val();
        let src = '{ "token": "' + token + '" }';
        let message = $('#chat-message').val();

        // var jsonMessage = {'name': name, 'message': message};
        var jsonMessage = {'source': src, 'message': message};
        console.log(jsonMessage);
        connection.send(jsonMessage).done(function () {
            // message send
            addMessage(JSON.parse(jsonMessage.source).first_name + ' ' + jsonMessage.last_name, message, true);
        }).fail(function (e) {
            // error sending
            alert("Error sending");
        });


    });
    // connection.send({'text': 'hello'}).done(function () {
    //     // message send
    // }).fail(function (e) {
    //     // error sending
    // });

    // if (connection.isConnected()) ... ;
    // connection.close();

    function addMessage(name, message, me) {

        let owner = (me) ? 'me' : 'him';
        let html = '<div class="' + owner + '">' +
            '<div class="name">' + name + '</div>' +
            '<br>' +
            '<div class="box">' + message + '</div>' +
            '</div>';

        var chatArea = $('#chat-area');
        chatArea.append(html);
        chatArea.scrollTop(chatArea.get(0).scrollHeight);
    }
});
