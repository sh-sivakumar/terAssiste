<html lang="en">
<head>
    <meta charset="utf-8">
    <title>terAssiste</title>
    <link rel="stylesheet" type="text/css" href="./site/style.css">
    <link rel="icon" type="image/png" href="./site/img/favicon.ico" />
</head>
<body>
    <div class="container">
        <center><img src="./site/img/logo.png" alt="Logo" class="logo"></center>
        <div class="flat-form">
            <ul class="tabs">
                <p>terAssiste, l'aide aux personnes à mobilité réduite à portée de main.</p>
            </ul>
            <div id="login" class="form-action show">
                <p>Le formulaire ci-dessous permet de créer des comptes utilisateurs pour les agents de la SNCF voulant utiliser l'application
                terAssiste (disponible sous Android).</p>
                <form action="site/inscription.php" method="post">
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
