<?php 
	include 'koneksi.php';
	$response=array();
	$sql=mysql_query("SELECT * FROM berita");
	$cek=mysql_num_rows($sql);
	if($cek >0){
		$response["berita"]=array();
		while ($row=mysql_fetch_array($sql)){
			$data=array();
			$data["id"]=$row["id_berita"];
			$data["judul"]=$row["judul"];
			$data["deskripsi"]=$row["isi_berita"];
			$data["gambar"]=$row["gambar"];
			
			array_push($response["berita"],$data);
		}
		$response["pensan"]="Semua Data Berita";
		$response["sukses"]=1;
		echo json_encode($response);
	}else{
		$response["pesan"]="Gagal Mengambil Data";
		$response["sukses"]=0;
		echo json_encode($response);
	}		
?>