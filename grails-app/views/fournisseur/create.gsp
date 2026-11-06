<html>
<head>
    <meta name="layout" content="main"/>
    <title>Nouveau fournisseur</title>
</head>
<body>
<h1>Nouveau fournisseur</h1>

<g:form action="save" method="POST">
    <g:render template="form" model="[fournisseur: fournisseur]"/>

    <g:submitButton class="btn btn-primary" name="creer" value="Créer"/>
    <g:link class="btn btn-link" action="index">Annuler</g:link>
</g:form>
</body>
</html>