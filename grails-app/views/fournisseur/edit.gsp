<html>
<head>
    <meta name="layout" content="main"/>
    <title>Modifier ${fournisseur.code}</title>
</head>
<body>
<h1>Modifier ${fournisseur.code}</h1>

<g:form action="update" id="${fournisseur.id}" method="PUT">
    <g:hiddenField name="version" value="${fournisseur.version}"/>

    <g:render template="form" model="[fournisseur: fournisseur]"/>

    <g:submitButton class="btn btn-primary" name="enregistrer" value="Enregistrer"/>
    <g:link class="btn btn-link" action="show" id="${fournisseur.id}">Annuler</g:link>
</g:form>
</body>
</html>