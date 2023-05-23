<?php
require "DataBase.php";
$db = new DataBase();
if (isset($_POST['ServiceName']) && isset($_POST['ServiceAddress']) && isset($_POST['Phonenumber']) && isset($_POST['Price']) && isset($_POST['Password']) && isset($_POST['Email']) && isset($_POST['Name'])) {
    if ($db->dbConnect()) {
        $service = $_POST['ServiceName'];
        $Address = $_POST['ServiceAddress'];
        $Phonenumber = $_POST['Phonenumber'];
        $Price = $_POST['Price'];
        $Password = $_POST['Password'];
        $Email = $_POST['Email'];
        $Name = $_POST['Name'];

        $sql =
            "INSERT INTO service_providers(Name,Phonenumber,Email,Address,Service,Price) values('".$Name."','".$Phonenumber."','".$Email."','".$Address."','".$service."','".$Price."' );";

        if($data = mysqli_query($db->connect, $sql)){
            echo "Adding Successful";
        } else echo "Service not added";
    } else echo "Error: Database connection";
} else echo "All fields are required";
?>
