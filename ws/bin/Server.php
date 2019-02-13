<?php
/**
 * Created by PhpStorm.
 * User: Ahmed
 * Date: 24/10/2018
 * Time: 12:06
 */

use Ratchet\Server\IoServer;
use Ratchet\Http\HttpServer;
use Ratchet\WebSocket\WsServer;
use server\ChatService;
//use server\PrivateChatService;


require dirname(
        __DIR__
    ).'/vendor/autoload.php';
// url : ws://ip_address:8080
$chat_server = IoServer::factory(new HttpServer(new WsServer(new ChatService())), 8080);
$chat_server->run();

$quizz_server = IoServer::factory(new HttpServer(new WsServer(new PrivateChatService())), 8081);
$quizz_server->run();




//$chat_server = IoServer::factory(new HttpServer(new WsServer(new PrivateChatService())), 8085);
//$chat_server->run();