<?php
$nama = $_GET['nama'];
$harga = $_GET['harga'];
$link = mysqli_connect('localhost', 'username', '') or die('Cannot connect to the DB');
mysqli_select_db($link, 'id5741633_db_makanan') or die('Cannot select the DB');
/* grab the posts from the db */
$query = "insert into tbl_makanan (nama_makanan,harga) values('".$nama."',".$harga.")";
$result = mysqli_query($link,$query) or die('Error query: '.$query);
echo "SUCCESS";
?>