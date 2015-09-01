<?php $title = "PROECT|Register";?>
<?php include('includes/header1.php');?>
<br><br><br><br><br><br><br><br>
	<form action = "signup.php" method = "POST">
		<fieldset class = "account-info">
			<label>Username:
				<input type = "text" name = "username" id = "username" placeholder = "Username" required>
			</label>
			<label>Password:
				<input type = "password" name = "password" id = "password" placeholder = "Password" required>
			</label>
			<label>Password:
				<input type = "password" name = "cpassword" id = "cpassword" placeholder = "Confirm Password" required>
			</label>
			<label>Fullname:
				<input type = "text" name = "name" id = "name" placeholder = "Fullname" required>
			</label>
			<label>Address:
				<input type = "text" name = "address" id = "address" placeholder = "Address" required>
			</label>
			<label>Gender:
				<select name = "gender">
					<option value = "" disabled = "disabled" selected = "selective">Please Select a gender</option>
					<option value = "Female">Female</option>
					<option value = "Male">Male</option>
				</select>
			</label>
			<label>Course:
				<input type = "text" name = "course" id = "course" placeholder = "Course" required>
			</label>
		</fieldset>
	<fieldset class = "account-action">
		<input class = "btn" type = "submit" value = "Register">
	</fieldset>
	</form>
	<?php include('includes/footer.php');?>
