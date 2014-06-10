<?php


	$sql_event =mysql_query("	Select evenement.itineraire as iditineraire, train.nom as nomTrain, train.id as idTrain  
								FROM evenement JOIN train ON evenement.train = train.id
								GROUP BY nomTrain");
	$num_rows = mysql_num_rows($sql_event);

	if( $num_rows >=1 ) {	
		$jsonReturn["result"] = true;
		$json =  array();
		while( $donnees = mysql_fetch_array($sql_event)) {  
			$iditineraire = $donnees["iditineraire"];
			$nomTrain = $donnees["nomTrain"];
			$idTrain = $donnees["idTrain"];

			$sql_event3 = mysql_query("Select count(client) as nb FROM evenement WHERE train = ".$idTrain."");
			$res2 = mysql_fetch_array($sql_event3);
			$nbPMR = $res2["nb"];

			$sql_event2 = mysql_query("Select client,id FROM evenement WHERE train = ".$idTrain."");

			$json_pmr = array();	
			while($donnees2 = mysql_fetch_array($sql_event2)) {
				$sql_cp = mysql_query("Select nom,prenom,x,y FROM client, evenement, plan  WHERE client.id = ".$donnees2["client"]." 
					and client.id = evenement.client and evenement.plan = plan.id");

				$res =  mysql_fetch_array($sql_cp);
				$nom = $res["nom"];
				$prenom = $res["prenom"];
				$x = $res["x"];
				$y = $res["y"];
				$idevt = $donnees2["id"];

				$json_pmre["nom"] = $nom;
				$json_pmre["prenom"] = $prenom;
				$json_pmre["x"] = $x;
				$json_pmre["y"] = $y;

				$json_pmr[] = array( "id" => $idevt, "nom" => $json_pmre["nom"] , "prenom" => $json_pmre["prenom"], "x" => $json_pmre["x"], "y" => $json_pmre["y"] );
			}
			$json[] = array( "train" => $nomTrain , "nbPMR" => $nbPMR , "pmr" => $json_pmr );


		}
		$jsonReturn["event"] = $json;
	}
	else {
		$jsonReturn["result"] = false;
		$jsonReturn["error"] = "pas d'evenement";

	}

	print(json_encode($jsonReturn));

?>