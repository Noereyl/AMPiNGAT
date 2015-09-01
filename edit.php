<?php $title = "PROECT|Update";?>
<?php include('includes/header1.php');?>

<?php include ('classes/class_user.php');
$obj_user = new User;
$obj_user->id = $_GET['i_d'];
$data = $obj_user->get();
?>

<br><br><br><br><br><br><br><br>
	<form action = "update.php" method = "POST">
		<fieldset class = "account-info">
			<label>Username:
				<input type = "text" name = "username" value = "<?php echo $data['username'];?>" id = "username">
			</label>
			<label>Password:
				<input type = "password" name = "password" id = "password" value = "<?php echo $data['password'];?>">
			</label>
			<label>Password:
				<input type = "password" name = "cpassword" id = "cpassword" value = "<?php echo $data['password'];?>">
			</label>
			<label>Fullname:
				<input type = "text" name = "name" id = "name" value = "<?php echo $data['name'];?>">
			</label>
			<label>Address:
				<input type = "text" name = "address" id = "address" value = "<?php echo $data['address'];?>">
			</label>
			<label>Gender:
				<select name = "gender" required>
					<option value = "" disabled = "disabled" selected = "selective">Please Select a gender</option>
					<option value = "Female">Female</option>
					<option value = "Male">Male</option>
				</select>
			</label>
			<label>Course:
				<input type = "text" name = "course" id = "course" value = "<?php echo $data['course'];?>">
			</label>
		</fieldset>
		<input type = "hidden" name = "id" value = "<?php echo $data['id'];?>" id = "id"> 
	<fieldset class = "account-action">
		<input class = "btn" type = "submit" value = "Update">
	</fieldset>
	</form>
	<?php include('includes/footer.php');?>	
