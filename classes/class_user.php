<?php 

include('classes/class_db.php');

class User extends Database{

	var $id;

	function selectUser($data){
		$conn = $this->connect();

		$sql = "SELECT * FROM usertbl WHERE username='".$data['username']."' AND
												password='".$data['password']."'";
		$result = $conn->query($sql);
		$conn->close();
		if ($result->num_rows > 0) {
			return $result;
		}

		return FALSE;
	}

		function insert($data){
		$conn = $this->connect();

		$sql = "INSERT INTO usertbl (name, address, gender, course, username, password)
					VALUES ('".$data['name']."', '".$data['address']."', '".$data['gender']."', '".$data['course']."' , '".$data['username']."', '".$data['password']."' )";

		if($conn->query($sql)){
			echo "New record created successfully.";
		}else{
			echo "Error: ".$sql." ".$conn->error;
		}

		$conn->close();
	}

		function get(){
				$conn = $this->connect();

				$sql = "SELECT * FROM usertbl WHERE id = ".$this->id;

				$result = $conn->query($sql);
				$conn->close();

				if($result->num_rows > 0){
					while($rows=$result->fetch_assoc()){
						return $rows;
					}
				}
			}

			function select(){
				$conn = $this->connect();

				$sql = "SELECT * FROM usertbl";
				$result = $conn->query($sql);
				$conn->close();

				if($result->num_rows > 0){
					return $result;
				}
				
				return FALSE;
			}


			function update($data){
				$conn = $this->connect();
				$sql = "UPDATE usertbl SET name = '" .$data['name']."',
											address = '".$data['address']."',
											gender = '".$data['gender']."',
											course = '".$data['course']."',
											username = '".$data['username']."',
											password = '".$data['password']."' 
											WHERE id = '".$data['id']."'";
											
				if($conn->query($sql)){
						echo "Record successfully updated!.";
					}else{
						echo "Error: ".$sql." ".$conn->error;
					}

				$conn->close();
			}
		}
?>