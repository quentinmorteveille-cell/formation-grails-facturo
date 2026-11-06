package facturo

import grails.transaction.Transactional

@Transactional
class FournisseurService {

    Fournisseur get(Serializable id) {
        Fournisseur.get(id)
    }

    List<Fournisseur> list(Map params = [:]) {
        Fournisseur.createCriteria().list(params) {
            eq('actif', true)
        }
    }

    Fournisseur save(Fournisseur fournisseur) {
        if (fournisseur == null) {
            return null
        }

        fournisseur.save(flush: true)
    }
}