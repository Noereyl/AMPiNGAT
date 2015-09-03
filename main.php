<?php
include('classes/class_user.php'); 
$user_obj = new User;
$users = $user_obj->select();

if(empty($_SESSION)) 
   session_start();

if(!isset($_SESSION['username'])) { 
   header("Location: new.php");
   exit;
}
?>

<?php $title = "PROJECT|Home";?>
<?php include('includes/header1.php');?>
<br><br><br><br><br><br>
	<html>
	<body>
		<head><title>List of Users</title>
	</head>
		<div class = "container">
			<div class = "row">
				<div class = "col-md-12">
					<h2>List of Users</h2>
					<br>
							<table class="table table-striped">
								<thead>
									<tr>
										<th>FULLNAME</th>
										<th>ADDRESS</th>
										<th>GENDER</th>
										<th>COURSE</th>
									</tr>
								</thead>
				<?php
				if($users) {
					while ($row=$users->fetch_assoc()) {
						echo "<tr>";
						echo "<td>".$row["name"]."</td>";
						echo "<td>".$row['address']."</td>";
						echo "<td>".$row['gender']."</td>"; 
						echo "<td>".$row['course']."</td>";
						echo "<td><a href = 'view.php?i_d=".$row["id"]."'>VIEW</td>";
						echo "<td><a href = 'edit.php?i_d=".$row["id"]."'>UPDATE</td>";
						echo "</tr>";
					}
				}
			?>
			</table>
			</div>
			</div>
		</div>
	</body>
	</html>
	