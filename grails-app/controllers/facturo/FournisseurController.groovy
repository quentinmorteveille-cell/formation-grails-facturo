package facturo

import org.springframework.dao.OptimisticLockingFailureException

class FournisseurController {

    FournisseurService fournisseurService

    static allowedMethods = [
            save: 'POST',
            update: 'PUT',
	    desactiver: 'POST'
    ]

    def index() {
        [fournisseurs: fournisseurService.list()]
    }

    def show(Long id) {
    Fournisseur fournisseur = fournisseurService.get(id)

    if (!fournisseur) {
        notFound()
        return
    }

    [
            fournisseur: fournisseur,
            produits: Produit.findAllByFournisseur(
                    fournisseur,
                    [sort: 'libelle', order: 'asc']
            ),
            nbProduitsEnRupture: Produit.countByFournisseurAndStock(fournisseur, 0),
            desactivationCommand: new DesactivationCommand(fournisseurId: fournisseur.id)
    ]
}

def desactiver(DesactivationCommand command) {
    Fournisseur fournisseur = fournisseurService.get(command.fournisseurId)

    if (!fournisseur) {
        notFound()
        return
    }

    if (!command.validate()) {
        render view: 'show', model: [
                fournisseur: fournisseur,
                produits: Produit.findAllByFournisseur(
                        fournisseur,
                        [sort: 'libelle', order: 'asc']
                ),
                nbProduitsEnRupture: Produit.countByFournisseurAndStock(fournisseur, 0),
                desactivationCommand: command
        ]
        return
    }

    if (!fournisseur.actif) {
        flash.message = 'Ce fournisseur est déjà désactivé.'
        redirect action: 'index'
        return
    }

    fournisseur.actif = false
    fournisseur.motifDesactivation = command.motif

    Fournisseur saved = fournisseurService.save(fournisseur)

    if (!saved) {
        render view: 'show', model: [
                fournisseur: fournisseur,
                produits: Produit.findAllByFournisseur(
                        fournisseur,
                        [sort: 'libelle', order: 'asc']
                ),
                nbProduitsEnRupture: Produit.countByFournisseurAndStock(fournisseur, 0),
                desactivationCommand: command
        ]
        return
    }

    flash.message = "Le fournisseur ${fournisseur.code} a été désactivé."

    redirect action: 'index'
}

    /**
     * Affiche le formulaire de création.
     */
    def create() {
        [fournisseur: new Fournisseur()]
    }

    /**
     * Enregistre un nouveau fournisseur.
     */
    def save() {
        Fournisseur fournisseur = new Fournisseur()

        bindData(fournisseur, params, [
                include: [
                        'code',
                        'raisonSociale',
                        'siret',
                        'email',
                        'delaiLivraisonJours'
                ]
        ])

        if (!fournisseur.validate()) {
            render view: 'create', model: [fournisseur: fournisseur]
            return
        }

        Fournisseur saved = fournisseurService.save(fournisseur)

        if (!saved) {
            render view: 'create', model: [fournisseur: fournisseur]
            return
        }

        flash.message = "Le fournisseur ${fournisseur.code} a été créé."

        redirect action: 'show', id: fournisseur.id
    }

    /**
     * Affiche le formulaire de modification.
     */
    def edit(Long id) {
        Fournisseur fournisseur = fournisseurService.get(id)

        if (!fournisseur) {
            notFound()
            return
        }

        [fournisseur: fournisseur]
    }

    /**
     * Modifie un fournisseur existant.
     */
    def update(Long id) {
        Fournisseur fournisseur = fournisseurService.get(id)

        if (!fournisseur) {
            notFound()
            return
        }

        // Vérification du verrouillage optimiste.
        String versionParam = params.version?.toString()
        Long submittedVersion = versionParam?.isLong() ? versionParam.toLong() : null

        if (submittedVersion == null || submittedVersion != fournisseur.version) {
            fournisseur.errors.rejectValue(
                    'version',
                    'default.optimistic.locking.failure'
            )

            render view: 'edit', model: [fournisseur: fournisseur]
            return
        }

        // Seuls les champs autorisés sont modifiables.
        bindData(fournisseur, params, [
                include: [
                        'code',
                        'raisonSociale',
                        'siret',
                        'email',
                        'delaiLivraisonJours'
                ]
        ])

        if (!fournisseur.validate()) {
            render view: 'edit', model: [fournisseur: fournisseur]
            return
        }

        try {
            Fournisseur saved = fournisseurService.save(fournisseur)

            if (!saved) {
                render view: 'edit', model: [fournisseur: fournisseur]
                return
            }

        } catch (OptimisticLockingFailureException e) {

            Fournisseur latest = fournisseurService.get(id)

            if (!latest) {
                notFound()
                return
            }

            latest.errors.rejectValue(
                    'version',
                    'default.optimistic.locking.failure'
            )

            render view: 'edit', model: [fournisseur: latest]
            return
        }

        flash.message = "Le fournisseur ${fournisseur.code} a été modifié."

        redirect action: 'show', id: fournisseur.id
    }

    protected void notFound() {
        render status: 404, view: '/notFound'
    }
}