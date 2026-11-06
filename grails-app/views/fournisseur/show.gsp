
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Fournisseur ${fournisseur.code}</title>
</head>
<body>

<div class="d-flex justify-content-between align-items-center mb-3">
    <h1>Fournisseur ${fournisseur.code}</h1>

    <g:link class="btn btn-primary" action="edit" id="${fournisseur.id}">
        Modifier
    </g:link>
</div>

<g:if test="${flash.message}">
    <div class="alert alert-info" role="alert">
        ${flash.message}
    </div>
</g:if>

<h3>Informations générales</h3>

<table class="table table-bordered">
    <tbody>
    <tr>
        <th>Code</th>
        <td>${fournisseur.code}</td>
    </tr>
    <tr>
        <th>Raison sociale</th>
        <td>${fournisseur.raisonSociale}</td>
    </tr>
    <tr>
        <th>SIRET</th>
        <td>${fournisseur.siret}</td>
    </tr>
    <tr>
        <th>E-mail</th>
        <td>${fournisseur.email ?: '-'}</td>
    </tr>
    <tr>
        <th>Délai de livraison</th>
        <td>${fournisseur.delaiLivraisonJours} jours</td>
    </tr>
    <tr>
        <th>Statut</th>
        <td>
            <g:if test="${fournisseur.actif}">
                <span class="badge badge-success">Actif</span>
            </g:if>
            <g:else>
                <span class="badge badge-secondary">Désactivé</span>
            </g:else>
        </td>
    </tr>
    <g:if test="${!fournisseur.actif}">
        <tr>
            <th>Motif de désactivation</th>
            <td>${fournisseur.motifDesactivation}</td>
        </tr>
    </g:if>
    </tbody>
</table>

<h3>Produits associés</h3>

<g:if test="${produits}">
    <table class="table table-sm">
        <thead>
        <tr>
            <th>Référence</th>
            <th>Libellé</th>
            <th>Stock</th>
        </tr>
        </thead>
        <tbody>
        <g:each in="${produits}" var="produit">
            <tr>
                <td>${produit.reference}</td>
                <td>${produit.libelle}</td>
                <td>${produit.stock}</td>
            </tr>
        </g:each>
        </tbody>
    </table>

    <p>Produits en rupture : ${nbProduitsEnRupture}</p>
</g:if>
<g:else>
    <p>Aucun produit associé à ce fournisseur.</p>
</g:else>

<g:if test="${fournisseur.actif}">
    <hr/>

    <h3>Désactiver le fournisseur</h3>

    <p>La désactivation retirera ce fournisseur de la liste des fournisseurs actifs.</p>

    <g:hasErrors bean="${desactivationCommand}">
        <div class="alert alert-danger">
            <ul>
                <g:eachError bean="${desactivationCommand}" var="err">
                    <li><g:message error="${err}"/></li>
                </g:eachError>
            </ul>
        </div>
    </g:hasErrors>

    <g:form action="desactiver" method="POST">
        <g:hiddenField name="fournisseurId" value="${fournisseur.id}"/>

        <div class="form-group">
            <label for="motif">Motif de désactivation (10 à 200 caractères)</label>
            <g:textArea
                    name="motif"
                    class="form-control"
                    rows="3"
                    maxlength="200"
                    value="${desactivationCommand?.motif}"/>
        </div>

        <g:submitButton
                class="btn btn-danger"
                name="desactiver"
                value="Désactiver le fournisseur"/>
    </g:form>
</g:if>

<div class="mt-3">
    <g:link action="index" class="btn btn-secondary">
        Retour à la liste
    </g:link>
</div>

</body>
</html>
