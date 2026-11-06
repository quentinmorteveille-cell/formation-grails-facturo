```gsp
<html>
<head>
    <meta name="layout" content="main"/>
    <title><g:message code="fournisseur.liste.titre"/></title>
</head>
<body>
<div class="d-flex justify-content-between align-items-center mb-3">
    <h1><g:message code="fournisseur.liste.titre"/></h1>

    <g:link action="create" class="btn btn-primary">
        Nouveau fournisseur
    </g:link>
</div>

<g:if test="${fournisseurs}">
    <table class="table table-sm">
        <thead>
        <tr>
            <th>Code</th>
            <th>Raison sociale</th>
            <th>SIRET</th>
            <th>E-mail</th>
            <th>Délai de livraison</th>
        </tr>
        </thead>
        <tbody>
        <g:each in="${fournisseurs}" var="f">
            <tr>
                <td>
                    <g:link action="show" id="${f.id}">${f.code}</g:link>
                </td>
                <td>${f.raisonSociale}</td>
                <td>${f.siret}</td>
                <td>${f.email}</td>
                <td>${f.delaiLivraisonJours} jours</td>
            </tr>
        </g:each>
        </tbody>
    </table>
</g:if>
<g:else>
    <p><g:message code="fournisseur.liste.vide"/></p>
</g:else>
</body>
</html>
```