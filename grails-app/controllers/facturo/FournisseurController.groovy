package facturo

class FournisseurController {

    def index() {
        [fournisseurs: Fournisseur.findAllByActif(true, [sort: 'raisonSociale', order: 'asc'])]
    }

    def show(Long id) {
        Fournisseur fournisseur = Fournisseur.get(id)

        if (!fournisseur) {
            notFound()
            return
        }

        [fournisseur: fournisseur,
         produits: Produit.findAllByFournisseur(fournisseur, [sort: 'libelle', order: 'asc']),
         nbProduitsEnRupture: Produit.countByFournisseurAndStock(fournisseur, 0)]
    }

    protected void notFound() {
        render status: 404, view: '/notFound'
    }
}