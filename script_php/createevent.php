<?php
	$data_back = json_decode(file_get_contents('php://input'));
	
	
	//$nomvoyage =  $data_back->{"nomvoyage"};
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
	
	//$nomvoyage =  "x551";
	/*
	$nompmr = "PmrMan";
	$prenompmr = "PrenomPMR";
	$train = "T51";
	$gareDep = "olympiade";
	$heureDep ="15:00";
	$gareArr ="gare du nord";
	$heureArr ="15:30";
	$agent = "2";
	$x = "70";
	$y = "100";
	*/
	//$req = mysql_query("Select id from itineraire where numero_voyage = '".$nomvoyage."' ");
	$req = mysql_query("Select * from client cl, train tr, evenement ev where cl.id = ev.client and ev.train = tr.id and tr.nom = '".$train."' and cl.nom='".$nompmr."'
	and cl.prenom='".$prenompmr."' ");
	
	if( mysql_num_rows($req) == 0 ) {
		$req_gareDep =  mysql_query("Select id,nom from gare where nom = '".$gareDep."' ");
		$row = mysql_fetch_array($req_gareDep);
		$id_gareDep = $row["id"];

		$req_gareArr =  mysql_query("Select id,nom from gare where nom = '".$gareArr."' ");
		$row = mysql_fetch_array($req_gareArr);
		$id_gareArr = $row["id"];

		$req_train =  mysql_query("Select id,nom from train where nom = '".$train."' ");
		$row = mysql_fetch_array($req_train);
		$train = $row["id"];
		
		$req_ajout1 = mysql_query("INSERT INTO itineraire (gare_depart, gare_arrive , heure_depart , heure_arrive ) values('".$id_gareDep."','".$id_gareArr."' , '".$heureDep."' , '".$heureArr."' )");
		$req_ajout2 = mysql_query("INSERT INTO client(nom , prenom ) values('".$nompmr."' , '".$prenompmr."')");
		$req_ajout3 = mysql_query("INSERT INTO plan(x , y ) values(".$x." , ".$y.")");

		$req_Client =  mysql_query("Select MAX(id) as i from client" );
		$row = mysql_fetch_array($req_Client);
		$idClient = $row["i"];

		$req_Itineraire =  mysql_query("Select MAX(id) as j from itineraire" );
		$row = mysql_fetch_array($req_Itineraire);
		$idItineraire = $row["j"];

		$req_Plan =  mysql_query("Select MAX(id) as k from plan" );
		$row = mysql_fetch_array($req_Plan);
		$idPlan = $row["k"];

		$req_Agent =  mysql_query("select id from agent where pseudo = '".$loginAgent."'");
		$row = mysql_fetch_array($req_Agent);
		$agent = $row["id"];


		$req_ajout4 = mysql_query("INSERT INTO evenement(agent , client , itineraire , train, plan ) values(".$agent." , ".$idClient." , ".$idItineraire.",".$train.",".$idPlan.")");
		$result["result"] = true;
	} else {
		$result["result"] = false;
	}

	print(json_encode($result));
?>
	