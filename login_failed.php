<?php $title = "Project|LOGIN"; ?>
<?php include('includes/header.php'); ?>
<br><br><br><br><br><br><br>
<p class="warning">Incorrect Username/Password!</p>
<br><br><br>
<form action="login.php" method="POST">
	<fieldset class="account-info">
		<label>
			Username:
			<input type="text" name="username" id="username" placeholder="Username" required>
		</label>
		<label>
			Password:
			<input type="password" name="password" id="password" placeholder="Password" required>
		</label>
	</fieldset>
	<fieldset class="account-action">
		<input class="btn" type="submit" value="Login">
	</fieldset>
</form>
<?php include('includes/footer.php'); ?>