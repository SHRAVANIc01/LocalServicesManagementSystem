<?php
require "DataBase.php";
$db = new DataBaseConfig();
$result = array();
$table = "customer";
$connect = mysqli_connect($db->servername, $db->username, $db->password, $db->databasename);
$email = $_POST['Email'];
if($connect){
    $sql =
        "SELECT * FROM ".$table." where Email = '".$email."' ;";
    $data = mysqli_query($connect, $sql);
    $num = mysqli_num_rows($data);
    if($num != 0){
        $row = mysqli_fetch_assoc($data);
        $result = array("status" => "success","name" => $row['Name'], "phonenumber" => $row['Phonenumber'] , "email" => $row['Email'], "address" => 
                    $row['Address'], "balance" => $row['Balance']);

    } else $result = array("status" => "failure", "message" => "No such email found");
}else $result = array("status" => "failure", "message" => " failed connection to database");

echo json_encode($result , JSON_PRETTY_PRINT);
