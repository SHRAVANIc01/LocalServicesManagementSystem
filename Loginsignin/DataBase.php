<?php
require "DataBaseConfig.php";

class DataBase
{
    public $connect;
    public $data;
    private $sql;
    protected $servername;
    protected $username;
    protected $password;
    protected $databasename;
    public $num;

    public function __construct()
    {
        $this->connect = null;
        $this->data = null;
        $this->sql = null;
        $dbc = new DataBaseConfig();
        $this->servername = $dbc->servername;
        $this->username = $dbc->username;
        $this->password = $dbc->password;
        $this->databasename = $dbc->databasename;
    }

    function dbConnect()
    {
        $this->connect = mysqli_connect($this->servername, $this->username, $this->password, $this->databasename);
        return $this->connect;
    }

    function prepareData($data)
    {
        return mysqli_real_escape_string($this->connect, stripslashes(htmlspecialchars($data)));
    }

    function logIn($table, $username, $password)
    {
        $username = $this->prepareData($username);
        $password = $this->prepareData($password);
        $this->sql = "select * from " . $table . " where Username = '" . $username . "'";
        $result = mysqli_query($this->connect, $this->sql);
        $row = mysqli_fetch_assoc($result);
        if (mysqli_num_rows($result) != 0) {
            $dbusername = $row['Username'];
            $dbpassword = $row['Password'];
            if ($dbusername == $username && password_verify($password, $dbpassword)) {
                $login = true;
            } else $login = false;
        } else $login = false;

        return $login;
    }

    function signUp($table, $username, $phonenumber, $address, $email, $password)
    {
        $username = $this->prepareData($username);
        $phonenumber = $this->prepareData($phonenumber);
        $address = $this->prepareData($address);
        $email = $this->prepareData($email);
        $password = $this->prepareData($password);
        $password = password_hash($password, PASSWORD_DEFAULT);
        $this->sql =
            "INSERT INTO " . $table . " (Username, Phonenumber, Address, Email, Password) VALUES ('" . $username . "','" . $phonenumber . "','" . $address . "','" . $email  . "','" . $password . "' )";
        if (mysqli_query($this->connect, $this->sql)) {
            return true;
        } else return false;
    }

    function addProfile($table, $name, $phonenumber, $address, $email)
    {
        $name = $this->prepareData($name);
        $phonenumber = $this->prepareData($phonenumber);
        $address = $this->prepareData($address);
        $email = $this->prepareData($email);
        $this->sql =
            "INSERT INTO " . $table . " (Name, phonenumber, address, email) VALUES ('" . $name . "','" . $phonenumber . "','" . $address . "','" . $email  . "' )";
        if (mysqli_query($this->connect, $this->sql)) {
            return true;
        } else return false;
    }

}

?>
