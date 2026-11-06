
package facturo

class Fournisseur {

    String code
    String raisonSociale
    String siret
    String email
    Integer delaiLivraisonJours = 7
    Boolean actif = true
    String motifDesactivation
    Date dateCreated

    static constraints = {
        code                blank: false, size: 3..20, matches: /[A-Z0-9-]+/, unique: true
        raisonSociale       blank: false, maxSize: 120
        siret               blank: false, size: 14..14, matches: /\d{14}/, unique: true
        email               nullable: true, email: true, maxSize: 120
        delaiLivraisonJours min: 1
        actif               nullable: false
        motifDesactivation  nullable: true, maxSize: 200
        dateCreated         nullable: true, bindable: false
    }

    static mapping = {
        table 'fournisseur'
        dateCreated column: 'date_creation'
        sort 'raisonSociale'
    }

    String toString() {
        "$code - $raisonSociale"
    }
}
