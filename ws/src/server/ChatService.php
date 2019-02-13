<?php
/**
 * Created by PhpStorm.
 * User: Ahmed
 * Date: 24/10/2018
 * Time: 12:07
 */


namespace server;


use Ratchet\MessageComponentInterface;
use Ratchet\ConnectionInterface;


include('lib.php');

class ChatService implements MessageComponentInterface
{
    protected $clients;

    public function __construct()
    {
        $this->clients = new \SplObjectStorage;
    }

    public function onOpen(ConnectionInterface $conn)
    {
// une nouvelle connexion, on l'enregistre !
        $this->clients->attach($conn);
        echo "Un nouvel utilisateur ! ({$conn->resourceId})\n";

    }

    public function onMessage(ConnectionInterface $from, $msg)
    {
//        $numRecv = count($this->clients) - 1;
//        echo sprintf(
//            'Connection %d a envoyé un message "%s" to %d aux autres connection%s'."\n"
//            ,
//            $from->resourceId,
//            $msg,
//            $numRecv,
//            $numRecv == 1 ? '' : 's'
//        );

//        echo $msg;
        $jsonMsg = json_decode($msg, true);
		echo $jsonMsg;
        if (!array_key_exists('source', $jsonMsg) || !array_key_exists('message', $jsonMsg)) {
            return;
        }

        if (!array_key_exists('token', $jsonMsg['source'])) {
            return;
        }
		
        $token = $jsonMsg['source']['token'];
		
        $checkedSource = getFriendByToken($token);

        $jsonMsg['source'] = $checkedSource;
        if (strlen($jsonMsg['message']) > 50) {
            $jsonMsg['message'] = substr($jsonMsg['message'], 0, 50).'...';
        }

        //        var_dump($checkedSource);
//        unset($jsonMsg['source']['token']);


        /*
        * On envoit le message à tous les autres utilisateur sauf nous même
        *
        */
        foreach ($this->clients as $client) {
//            if ($from !== $client) {
            $client->send(json_encode($jsonMsg));
//            }
        }
    }

    public function onClose(ConnectionInterface $conn)
    {
// connexion fermé, on se débarasse du client
        $this->clients->detach($conn);

        echo "L'utilisateur {$conn->resourceId} s'est déconnecté\n";
    }

    public function onError(ConnectionInterface $conn, \Exception $e)
    {
        echo "Une erreur s'est produite : {$e->getMessage()}\n";
        $conn->close();
    }
}