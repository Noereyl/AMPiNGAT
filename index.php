<?php
session_start();

if(isset($_SESSION['username'])){
	include('main.php');
}else{
	include('login_failed.php');
}
?>