<?php

function getDbConnection()
{
    $db_host = "localhost";
    $db_uid = "root"; 
    $db_pass = ""; 
    $db_name = "iot_db";
    $db_con = mysqli_connect($db_host, $db_uid, $db_pass, $db_name);
    mysqli_set_charset($db_con, 'utf8');

    return $db_con;
}

function getServerKey()
{

    $db_con = getDbConnection();
    $sql = "SELECT * FROM config WHERE `key` = 'server_key'";
    $result = mysqli_query($db_con, $sql);

    if ($row = mysqli_fetch_assoc($result)) {
        mysqli_close($db_con);

        return $row['value'];
    }
}

function checkKey()
{
    $server_key = getServerKey();
    if(!isset($server_key) || empty($server_key)){
        echo '{"error" : "Server key not defined yet !!!"}';
        http_response_code(404);
        return;
    }

    if(!isset($_GET['key']) || empty($_GET['key'])){
        echo '{"error": "Key is required !!!"}';
        http_response_code(403);
        return;
    }

    $key = $_GET['key'];

    if($key != $server_key){
        echo '{"error" : "Wrong key !!!"}';
        http_response_code(403);
        return;
    }

}

function createDuel($key, $from_id, $to_id)
{

    if ($key != getServerKey()) {
        return false;
    }

    $db_con = getDbConnection();
    $sql = "INSERT INTO `classroom_db`.`duel` (`from_id`, `to_id`) VALUES ($from_id, $to_id);";

    if (mysqli_query($db_con, $sql) == true) {
        return true;
    } else {
        return false;
    }
    mysqli_close($db_con);
}


function getAttendingByStudentId($id)
{
    $db_con = getDbConnection();
    $sql = "SELECT * FROM presence a WHERE $id = a.student_id";
    $result = mysqli_query($db_con, $sql);

    return ($row = mysqli_fetch_assoc($result));
    mysqli_close($db_con);
}

function getFriendById2($id)
{
    $db_con = getDbConnection();
    $sql = "SELECT * FROM friend where id=$id";
    $result = mysqli_query($db_con, $sql);

    if ($row = mysqli_fetch_assoc($result)) {
        return $row;
    }
    mysqli_close($db_con);
}

?>