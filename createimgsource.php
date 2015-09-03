<?php
  include("classes/dbconnect.php");
  $id=$_GET['image_id'];
  $sql="SELECT * from imagetbl where image_id='$id'";
 
  $query=mysql_query($sql) or die(mysql_error());
 
  while($result=mysql_fetch_array($query)){		
    header("Content-type:".$result['type']);
    header('Content-Disposition: inline; filename="'.$result['name'].'"');
    echo $result['image'];			
  }
?>