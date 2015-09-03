	<html>
	<head>
	<title>Uploading image to MySQL database and displaying it</title>
	<script src="mootools1_2.js" type="text/javascript" language="javascript"></script>
	<script src="ajax.js" type="text/javascript" language="javascript"></script>					
	<link href="style.css" rel="stylesheet" type="text/css">
	</head>
		<body>
			<a class="navbar-brand" href="main.php">Back</a>
		<fieldset>
			<legend>Upload Form</legend>
			<form enctype='multipart/form-data' name='frmupload' action='' method='POST'>
			<input type="hidden" name="MAX_FILE_SIZE" value="524288" />
			<input name='filename' type='file'>	
			<input type='submit' value='Submit' name='submit'>
		</form>
		</fieldset>
<?php
include("classes/dbconnect.php");

if(isset($_POST['submit'])){

if(is_uploaded_file($_FILES['filename']['tmp_name'])){
	
	$maxsize=$_POST['MAX_FILE_SIZE'];		
	$size=$_FILES['filename']['size'];
			
	// getting the image info..
	$imgdetails = getimagesize($_FILES['filename']['tmp_name']);
	$mime_type = $imgdetails['mime']; 
	//print_r($imgdetails);
				
	// checking for valid image type
	if(($mime_type=='image/jpeg')||($mime_type=='image/gif')){
	  // checking for size yet again
	  if($size<$maxsize){
	    $filename=$_FILES['filename']['name'];	
	    $imgData =addslashes (file_get_contents($_FILES['filename']['tmp_name']));
	    $sql="INSERT into imagetbl(name,image,type,size) values ('$filename','$imgData','".$mime_type."','".addslashes($imgdetails[3])."')";					
	    mysql_query($sql,$link) or die(mysql_error());
	  }else{
	    echo "<font class='error'>Image to be uploaded is too large..Error uploading the image!!</font>";
	  }
	}else{
	  echo "<font class='error'>Not a valid image file! Please upload jpeg or gif image.</font>";
	}
	
}else{			
  switch($_FILES['filename']['error']){
	case 0: //no error; possible file attack!
	  echo "<font class='error'>There was a problem with your upload.</font>";
	  break;
	case 1: //uploaded file exceeds the upload_max_filesize directive in php.ini
	  echo "<font class='error'>The file you are trying to upload is too big.</font>";
	  break;
	case 2: //uploaded file exceeds the MAX_FILE_SIZE directive that was specified in the html form
	  echo "<font class='error'>The file you are trying to upload is too big.</font>";
	  break;
	case 3: //uploaded file was only partially uploaded
	  echo "<font class='error'>The file you are trying upload was only partially uploaded.</font>";
	  break;
	case 4: //no file was uploaded
	  echo "<font class='error'>You must select an image for upload.</font>";
	  break;
	default: //a default error, just in case! 
	  echo "<font class='error'>There was a problem with your upload.</font>";
	  break;
  }		
}	

}

//List Images Part
$sql='SELECT * from imagetbl';
$query=mysql_query($sql) or die(mysql_error);
$listimage = "<fieldset>
		<legend>List Images</legend>	
	 ";
$listimage.= "
<table cellpadding='1' cellspacing='1' width='300' class='tablecss'>
  <tr class='tablecss'>
	<td nowrap class='label'>Image Name</td>
	<td class='label'>Type</td>
	<td class='label'>Size</td>
  </tr>";
$imageexist=false;
while($result=mysql_fetch_array($query)){
  $imageexist=true;		  			
  $listimage.= "
  <tr class='whiterow'>
	<td><a href='javascript:void(0)' onclick='ajaxrequest(\"".$result['image_id']."\")'>".$result['name']."</a></td>
	<td>".str_replace('image/','',$result['type'])."</td>
	<td nowrap>".$result['size']."</td>
  </tr>";										
}

$listimage.= "
</table>
</fieldset>";

if($imageexist){
	echo $listimage;
}

echo "<div id='imageframe' style='border:1px solid red;'></div>";					
?>
</body>
</html>