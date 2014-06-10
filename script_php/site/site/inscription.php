<?php
	if ((isset($_POST["pseudo"]) && !empty($_POST["pseudo"])) && (isset($_POST["mdp"]) && !empty($_POST["mdp"])) 
	&& (isset($_POST["nom"]) && !empty($_POST["nom"])) && (isset($_POST["prenom"]) && !empty($_POST["prenom"])) 
	&& (isset($_POST["contact"]) && !empty($_POST["contact"]))) {

			
		$pseudo = $_POST["pseudo"];
		$mdp = $_POST["mdp"];
		$nom = $_POST["nom"];
		$prenom = $_POST["prenom"];
		$contact = $_POST["contact"];

		$sql = "INSERT INTO agent (pseudo, password, nom, prenom, contact) VALUES ('".$pseudo."', '".$mdp."', '".$nom."', '".$prenom."', '".$contact."')"; 

    	mysql_query($sql) or die('Erreur SQL !'.$sql.'<br>'.mysql_error()); 

    	$msg = "* Le compte à été crée !"; 
    	$couleur = "green";   
	} else {  

	    $msg = "* Erreur lors de la création du compte"; 
	    $couleur = "red";  
	}
?>

<html lang="en">
<head>
    <meta charset="utf-8">
    <title>terAssiste</title>
    <link rel="stylesheet" type="text/css" href="./style.css">
    <link rel="icon" type="image/png" href="./img/favicon.ico" />
</head>
<body>
    <div class="container">
        <center><img src="./img/logo.png" alt="Logo" class="logo"></center>
        <div class="flat-form2">
            <ul class="tabs">
                <p>terAssiste, l'aide aux personnes à mobilité réduite à portée de main.</p>
            </ul>
            <div id="login" class="form-action show">
                <p>Le formulaire ci-dessous permet de créer des comptes utilisateurs pour les agents de la SNCF voulant utiliser l'application
                terAssiste (disponible sous Android).</p>
                <form action="inscription.php" method="post">

                	<?php echo "<p style=\"color:".$couleur."\">".$msg."</p>"; ?>

                    <ul>
                        <li>
                            <input type="text" placeholder="Pseudo" name="pseudo" />
                        </li>
                        <li>
                            <input type="password" placeholder="Mot de passe" name="mdp" />
                        </li>
                        <li>
                            <input type="text" placeholder="Nom" name="nom" />
                        </li>
                        <li>
                            <input type="text" placeholder="Prénom" name="prenom"/>
                        </li>
                        <li>
                            <input type="text" placeholder="Contact" name="contact"/>
                        </li>
                        <li>
                            <input type="submit" value="Inscription" class="button" />
                        </li>
                    </ul>
                </form>
            </div>
        </div>
    </div>
</body>
</html>
