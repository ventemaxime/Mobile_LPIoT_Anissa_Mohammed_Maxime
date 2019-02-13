<?php
session_start();

header('Content-Type: application/json');
include('lib.php');

checkKey();

if (!isset($_GET['username']) || empty($_GET['username'])) {

    echo '{"error": "username is required !!!"}';
    http_response_code(403);

    return;
}

if (!isset($_GET['password']) || empty($_GET['password'])) {
    echo '{"error": "password is required !!!"}';
    http_response_code(403);

    return;
}

$username = $_GET['username'];
$password = $_GET['password'];

getStudentByLogin($username, $password);

function getStudentByLogin($username, $password){

    $db_con = getDbConnection();
    $sql = "SELECT id, token, first_name, last_name,last_score, profile_url, random_color FROM student WHERE username = '$username' and `password` = '$password'";
    $result = mysqli_query($db_con, $sql);
    $output = null;

    if ($row = mysqli_fetch_assoc($result)) {
        $row['is_present'] = getAttendingByStudentId($row['id']) ? 1 : 0;
        $output = $row;
    }

    if ($output == null) {
        echo '{"info" : "Incorrect login !!!"}';
        http_response_code(400);
    } else {
        print(json_encode($output));
        http_response_code(200);
    }
    mysqli_close($db_con);
}

?>