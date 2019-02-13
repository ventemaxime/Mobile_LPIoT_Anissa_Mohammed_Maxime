<?php
session_start();

header('Content-Type: application/json');
include('lib.php');

checkKey();

if (isset($_GET['id']) && !empty($_GET['id'])) {
    getFriendById($_GET['id']);
} else {
    getAllFriends();
}

function getAllFriends()
{
//	    sleep(2);
    $db_con = getDbConnection();
    $sql = "SELECT id, first_name, last_name,last_score, profile_url, random_color FROM student";
    $result = mysqli_query($db_con, $sql);
    $output = null;
    while ($row = mysqli_fetch_assoc($result)) {
        // unset($row['barcode']);
        $row['is_present'] = getAttendingByStudentId($row['id']) ? 1 : 0;
        $output[] = $row;
    }

    if ($output == null) {
        echo '{"info": "Empty list of friends !!!"}';
        http_response_code(204);
    } else {
        print(json_encode($output));
        http_response_code(200);
    }
    mysqli_close($db_con);
}


function getFriendById($id)
{
    $db_con = getDbConnection();
    $sql = "SELECT id, first_name, last_name,last_score, profile_url, random_color FROM student WHERE id = $id";
    $result = mysqli_query($db_con, $sql);
    $output = null;

    if ($row = mysqli_fetch_assoc($result)) {
        $row['is_present'] = getAttendingByStudentId($row['id']) ? 1 : 0;
        $output = $row;
    }

    if ($output == null) {
        echo '{"info" : "Friend not found !!!"}';
        http_response_code(400);
    } else {
        print(json_encode($output));
        http_response_code(200);
    }
    mysqli_close($db_con);
}

?>