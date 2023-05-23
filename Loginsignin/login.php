<?php
require "DataBase.php";
$db = new DataBase();
if (isset($_POST['username']) && isset($_POST['password'])) {
    if ($db->dbConnect()) {
        if ($db->logIn("users", $_POST['username'], $_POST['password'])) {
            $sql = "SELECT Email,Username FROM users where Username = '".$_POST['username']."' ;";
            $data = mysqli_query($db->dbConnect(), $sql);
            $row = mysqli_fetch_assoc($data);
            $result = array("status" => "Login Success" , "email" => $row['Email'], "username" => $row['Username']);
            echo json_encode($result, JSON_PRETTY_PRINT);
        } else echo "Username or Password wrong";
    } else echo "Error: Database connection";
} else echo "All fields are required";
?>
