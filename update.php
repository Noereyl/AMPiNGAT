<?php
include('classes/class_user.php');
session_start();
$user_obj = new User;
$user_obj->update(array('name'=>$_POST['name'],
						'address'=>$_POST['address'],
						'gender'=>$_POST['gender'],
						'course'=>$_POST['course'],
						'username'=>$_POST['username'],
						'password'=>$_POST['password'],
						'id' => $_POST['id']));

header('location: main.php');
?>