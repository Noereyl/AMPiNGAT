<?php
include('classes/class_user.php');
session_start();
$user_obj = new User;
$user_obj->selectUser(array('username'=>$_POST['username'],
							'password'=>$_POST['password']));

if ($user_obj->selectUser(array('username'=>$_POST['username'],
							'password'=>$_POST['password'])) != FALSE) {
	
	$users = $user_obj->selectUser(array('username'=>$_POST['username'],
							'password'=>$_POST['password']));
	if ($users) {
		while ($rows = $users->fetch_assoc()) {
			$_SESSION['username'] = $rows['username'];
			$location = "index.php?username=".$rows['username'];
		}
	}
} else {
	$location = "login_failed.php";
}
header("Location: $location");
?>