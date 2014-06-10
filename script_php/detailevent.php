<?php
	$data_back = json_decode(file_get_contents('php://input'));
	

	$idevent = $data_back->{"idEvent"};

	$sql=mysql_query("	SELECT cl.nom as nomCl, cl.prenom as prenomCl, ag.nom as nomAg, ag.prenom as prenomAg, tr.nom as trainNom, 
	 						(select ga.nom from gare ga, itineraire it, evenement ev where it.id = ev.itineraire and ga.id = it.gare_depart and ev.id = ".$idevent.") as gareDep, 
	 						(select ga.nom from gare ga, itineraire it, evenement ev where it.id = ev.itineraire and ga.id = it.gare_arrive and ev.id = ".$idevent.") gareArr, 
	 					it.heure_depart as heureDep, it.heure_arrive as heureArr, pl.x, pl.y
						FROM evenement ev, agent ag, client cl, itineraire it, train tr, plan pl
						WHERE ev.id = ".$idevent." AND ag.id = ev.agent AND cl.id = ev.client AND it.id = ev.itineraire AND tr.id = ev.train AND ev.plan = pl.id");

  	$row = mysql_fetch_array($sql);
  	$num_rows = mysql_num_rows($sql);
	
	if($num_rows >= 1) {
    	$jsonReturn["result"] = true;
    	$jsonReturn["nom"] = $row["nomCl"];
    	$jsonReturn["prenom"] = $row["prenomCl"];
    	$jsonReturn["agent"] = $row["nomAg"]." ".$row["prenomAg"];
    	$jsonReturn["train"] = $row["trainNom"];
    	$jsonReturn["gareDep"] = $row["gareDep"];
    	$jsonReturn["heureDep"] = $row["heureDep"];
    	$jsonReturn["gareArr"] = $row["gareArr"];
    	$jsonReturn["heureArr"] = $row["heureArr"];
      $jsonReturn["x"] = $row["x"];
      $jsonReturn["y"] = $row["y"];

    
  	} else {
    	$jsonReturn["result"] = false;
    	$jsonReturn["error"] = "Evenement non existant";
  	}
	
	print(json_encode($jsonReturn));

?>
	