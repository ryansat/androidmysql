<?php
$link = mysqli_connect('localhost', 'username', '') or die('Cannot connect to the DB');
mysqli_select_db($link, 'id5741633_db_makanan')  or die("Could not select examples"); 
$query = "SELECT * FROM tbl_makanan";
$result =  mysqli_query($link,$query) or die('Errorquery: '.$query);
$rows = array();
while ($r = mysqli_fetch_assoc($result)) {
$rows[] = $r;
}
$data = "{makanan:".json_encode($rows)."}";
echo $data;
?>