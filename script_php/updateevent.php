<?php

/*
format json

id: c’est l’id de l’événement
nom: c’est le nom du pmr
prenom : le prénom du pmr
train : nom du train
gareDep: le nom de la gare de depart
heureDep: l’heure de départ
gareArr: le nom de la gare d’arrivé
heureArr: l’heure d’arrivée
x
y
*/

	$data_back = json_decode(file_get_contents('php://input'));
	

	
	$idevent = $data_back->{"idEvent"};
	
	$nompmr = $data_back->{"nom"};
	$prenompmr = $data_back->{"prenom"};
	$train = $data_back->{"train"};
	$gareDep = $data_back->{"gareDep"};
	$heureDep = $data_back->{"heureDep"};
	$gareArr = $data_back->{"gareArr"};
	$heureArr = $data_back->{"heureArr"};
	
	$x   = $data_back->{"x"};
	$y   = $data_back->{"y"};
	$loginAgent   = $data_back->{"agent"};
	//$x   = "100";
	//$y   =  "50";
	
	
	
	/*
	$idevent ="1";
	$nompmr = "ceciest";
	$prenompmr = "untest";
	$train = "T60";
	$gareDep = "garetestx";
	$heureDep = "15h00";
	$gareArr = "gareloltest";
	$heureArr = "16:00";
	$x   = "100";
	$y   =  "50";
	$loginAgent   = "duarte";
	*/
	
	
	
	
	$req = mysql_query("Select id from evenement where id = ".$idevent." ");
	$existe = mysql_num_rows($req);
	
	if( $existe >= 1 ) {
		$req_pmr = mysql_query("Select id,nom,prenom from client where nom = '".$nompmr."'  and prenom = '".$prenompmr."' ");
		$existe_pmr = mysql_num_rows($req_pmr);
	
		if( $existe_pmr == 0 ) {
			$req_ajout_pmr = mysql_query("INSERT INTO client (nom , prenom ) values('".$nompmr."' , '".$prenompmr."' ) ");	
			echo 'client1';
			$req_idc = mysql_query("select MAX(id) as n from client");
			$req_idc =  mysql_fetch_array($req_idc);
			$idc = $req_idc["n"];

		} else {
			echo 'client2';
			$req_idc =  mysql_fetch_array($req_pmr);
			$idc =  $req_idc["id"];
		}
		$req_train = mysql_query("Select id,nom from train where nom = '".$train."' ");
		$existe_train = mysql_num_rows($req_train);
		
		if( $existe_train == 0 ) {
			$req_ajout_pmr = mysql_query("INSERT INTO train (nom , date_entre_service ) values('".$train."' , '".date("Y/M/D")."' ) ");	
			$req_idt = mysql_query("select MAX(id) as n from train");
			$req_idt =  mysql_fetch_array($req_idt);
			$idt = $req_idt["n"];

		} else {
			$req_idt =  mysql_fetch_array($req_train);
			$idt =  $req_idt["id"];
			
		}
		$req_gare = mysql_query("Select id,nom from gare where nom = '".$gareDep."' ");
		$existe_gareD = mysql_num_rows($req_gare);
		
		if( $existe_gareD == 0 ) {
			mysql_query("INSERT INTO gare (nom ) values('".$gareDep."' ) ");	
			$req_idd = mysql_query("select MAX(id) as n from gare");
			$req_idd =  mysql_fetch_array($req_idd);
			$idd = $req_idd["n"];

		} else {
			$req_idd =  mysql_fetch_array($req_gare);	
			$idd =  $req_idd["id"];	
		}		
			
		$req_gare2 = mysql_query("Select id,nom from gare where nom = '".$gareArr."' ");
		$existe_gareA = mysql_num_rows($req_gare2);
		
		if( $existe_gareA == 0 ) {
			mysql_query("INSERT INTO gare (nom ) values('".$gareArr."' ) ");	
			$req_ida = mysql_query("select MAX(id) as n from gare");
			$req_ida =  mysql_fetch_array($req_ida);
			$ida = $req_ida["n"];

		} else {
			$req_ida =  mysql_fetch_array($req_gare2);
			$ida =  $req_ida["id"];
		}	
			
			
		$req_itineraire = mysql_query(" select * from itineraire where gare_depart = ".$idd." and gare_arrive = ".$ida." and heure_depart = '".$heureDep."' and heure_arrive = '".$heureArr."' ");
		$existe_itineraire = mysql_num_rows($req_itineraire);
		
		if( $existe_itineraire == 0 ) {	
			$req_ajout_itineraire= mysql_query("INSERT INTO itineraire (gare_depart  , gare_arrive , heure_arrive , heure_depart ) values (".$idd." ,".$ida." , '".$heureArr."' ,'".$heureDep."' ) ");	
			$req_idi = mysql_query("select MAX(id) as n from itineraire");
			$req_idi =  mysql_fetch_array($req_idi);
			$idi = $req_idi["n"];
		} else {
			$req_idi =  mysql_fetch_array($req_itineraire);
			$idi =  $req_idi["id"];
		}

		echo 'idt:'.$idt.' ,idc:'.$idc.' ,idi:'.$idi.' ,idevent:'.$idevent;
		$req_update = mysql_query(" UPDATE evenement set train = ".$idt." , client = ".$idc.", itineraire = ".$idi."  where  id = ".$idevent."  ");
		
		$result["result"] = true;	
		
	} else {
		$result["result"] = false;
	}
	
	print(json_encode($result));
	
?>