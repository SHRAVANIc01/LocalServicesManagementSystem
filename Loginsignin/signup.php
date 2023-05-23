<?php
require "DataBase.php";
$db = new DataBase();
if (isset($_POST['Username']) && isset($_POST['Name']) && isset($_POST['Phonenumber']) && isset($_POST['Address']) && isset($_POST['Email']) && isset($_POST['Password'])) {
    if ($db->dbConnect()) {
        $db->addProfile("customer", $_POST['Name'], $_POST['Phonenumber'], $_POST['Address'], $_POST['Email']);

        if ($db->signUp("users", $_POST['Username'], $_POST['Phonenumber'], $_POST['Address'], $_POST['Email'], $_POST['Password'])) {
            echo "Sign Up Success";
        } else echo "Sign up Failed";
    } else echo "Error: Database connection";
} else echo "All fields are required";
?>
