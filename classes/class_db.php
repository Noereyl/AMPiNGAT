<?php
class Database{
	var $servername = "localhost";
	var $username = "root";
	var $password = "";
	var $db = "midterm_exam_db";

	function connect(){
		$conn = new mysqli($this->servername, $this->username, $this->password, $this->db);

		if ($conn->connect_error) {
			die("Connection Failed: ".$conn->connect_error);
		}
		return $conn;
	}
}
?>