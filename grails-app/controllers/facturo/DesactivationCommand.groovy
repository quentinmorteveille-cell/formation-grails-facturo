package facturo

import grails.validation.Validateable

class DesactivationCommand implements Validateable {

    Long fournisseurId
    String motif

    static constraints = {
        fournisseurId nullable: false
        motif blank: false, minSize: 10, maxSize: 200
    }
}