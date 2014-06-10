<?php
	$data_back = json_decode(file_get_contents('php://input'));
	

    $idevent = $data_back->{"idEvent"};

	$req = mysql_query("select id from evenement where id = ".$idevent);
	$num_rows = mysql_num_rows($req);
	
	if( $num_rows >= 1 ) {	
		$sql_event = mysql_query("DELETE from evenement WHERE id='".$idevent."'");
		$jsonReturn["result"] = true;
	}
	else {
		$jsonReturn["result"] = false;
		$jsonReturn["error"] = "Erreur, evenement inexistant";
	}

	print(json_encode($jsonReturn));
	
?>