<?php
require "DataBase.php";
$db = new DataBaseConfig();
$result = array();
$table = "customer";
$connect = mysqli_connect($db->servername, $db->username, $db->password, $db->databasename);
$email = $_POST['Email'];
$balance = $_POST['Balance'];
if(isset($_POST['Email']) && isset($_POST['Balance'])){
    if($connect){
        $sql = "UPDATE ".$table." SET Balance = ".$balance." WHERE Email = '".$email."';";
        
        if($data = mysqli_query($connect, $sql)){
            echo "Balance Updated Successfully";
        } else {
            echo "Problem in updating balance";
        }
    } else echo "Database not connected";
} else echo "Something not provided"
?>
