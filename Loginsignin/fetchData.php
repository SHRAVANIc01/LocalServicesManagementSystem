<?php
require "DataBase.php";
$db = new DataBaseConfig();
$result = array();
$send = array();
$table = "service_providers";
$connect = mysqli_connect($db->servername, $db->username, $db->password, $db->databasename);
if($connect){
    $sql =
        "SELECT * FROM ".$table.";";
    $data = mysqli_query($connect, $sql);
    $num = mysqli_num_rows($data);
    if($num != 0){
        while($row = mysqli_fetch_assoc($data)){
            $result = array("status" => "success","name" => $row['Name'], "phonenumber" => $row['Phonenumber'] , "email" => $row['Email'], "address" => 
                        $row['Address'], "service" => $row['Service']);
            $send[] = $result;
        }
    } else $result = array("status" => "failure", "message" => "failed to retrieve data");
}else $result = array("status" => "failure", "message" => " failed connection to database");

echo json_encode($send, JSON_PRETTY_PRINT);
?>