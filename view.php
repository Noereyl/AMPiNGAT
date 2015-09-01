<?php $title = "PROJECT|View";?>
<?php include('includes/header1.php');?>
<?php include ('classes/class_user.php');
$obj_user = new User;
$obj_user->id = $_GET['i_d'];
$data = $obj_user->get();
?>
<br><br><br><br><br><br>
<html>
	<body>
		<head><title>View Information</title>
	</head>
		<div class = "container">
		
					<h2>Viewing Information</h2>
					<br><br>
				<fieldset class = "account-info">
					<p>
						<strong>Fullname:</strong>	
						<?php echo $data['name'];?></p>
					<p>
						<strong>Address:</strong>
						<?php echo $data['address'];?></p>						
					<p>
						<strong>Gender:</strong>
						<?php echo $data['gender'];?></p>						
					<p>
						<strong>Course:</strong>
						<?php echo $data['course'];?></p>
					<p>
						<strong>Username:</strong>
						<?php echo $data['username'];?></p>
					<p>
						<strong>Password:</strong>
						<?php echo $data['password'];?></p>
					<br><br><br><br>
					<a href = 'main.php?i_d=".$row["id"]."'>BACK</a>

				</fieldset>	
		</div>
	</body>
</html>