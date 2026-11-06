<g:hasErrors bean="${fournisseur}">
    <ul class="alert alert-danger errors" role="alert">
        <g:eachError bean="${fournisseur}" var="err">
            <li><g:message error="${err}"/></li>
        </g:eachError>
    </ul>
</g:hasErrors>

<div class="form-group">
    <label for="code">Code</label>
    <g:textField
            class="form-control ${hasErrors(bean: fournisseur, field: 'code', 'is-invalid')}"
            name="code"
            value="${fournisseur?.code}"/>
</div>

<div class="form-group">
    <label for="raisonSociale">Raison sociale</label>
    <g:textField
            class="form-control ${hasErrors(bean: fournisseur, field: 'raisonSociale', 'is-invalid')}"
            name="raisonSociale"
            value="${fournisseur?.raisonSociale}"/>
</div>

<div class="form-group">
    <label for="siret">SIRET</label>
    <g:textField
            class="form-control ${hasErrors(bean: fournisseur, field: 'siret', 'is-invalid')}"
            name="siret"
            value="${fournisseur?.siret}"/>
</div>

<div class="form-group">
    <label for="email">E-mail</label>
    <g:field
            type="email"
            class="form-control ${hasErrors(bean: fournisseur, field: 'email', 'is-invalid')}"
            name="email"
            value="${fournisseur?.email}"/>
</div>

<div class="form-group">
    <label for="delaiLivraisonJours">Délai de livraison (jours)</label>
    <g:field
            type="number"
            min="1"
            max="90"
            class="form-control ${hasErrors(bean: fournisseur, field: 'delaiLivraisonJours', 'is-invalid')}"
            name="delaiLivraisonJours"
            value="${fournisseur?.delaiLivraisonJours}"/>
</div>