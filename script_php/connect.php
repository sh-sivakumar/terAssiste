<?php
    $data_back = json_decode(file_get_contents('php://input'));

    $login = $data_back->{"login"};
    $pass = $data_back->{"password"};

    
    $sql=mysql_query("Select COUNT(*) as nb FROM agent WHERE pseudo = '".$login."' && password = '".$pass."'");
    $row = mysql_fetch_array($sql) or die(mysql_error());

    if($row['nb'] >= 1) {
      $jsonReturn["result"] = true;
      
    } else {
      $jsonReturn["result"] = false;
      $jsonReturn["error"] = "Erreur de login";
    }

    print(json_encode($jsonReturn));
    mysql_close();
?>