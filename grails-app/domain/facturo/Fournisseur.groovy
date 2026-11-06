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
        code blank: false, size: 3..20, matches: /^[A-Z0-9-]+$/, unique: true
        raisonSociale blank: false, maxSize: 120

        siret blank: false, size: 14..14, matches: /^\d{14}$/, unique: true, validator: { value, object ->
            if (!value || !(value ==~ /^\d{14}$/)) {
                return true
            }

            int somme = 0
            value.reverse().eachWithIndex { caractere, index ->
                int chiffre = caractere.toString().toInteger()

                if (index % 2 == 1) {
                    chiffre *= 2
                    if (chiffre > 9) {
                        chiffre -= 9
                    }
                }

                somme += chiffre
            }

            if (somme % 10 != 0) {
                return 'invalid'
            }
        }

        email nullable: true, blank: true, email: true, maxSize: 120
        delaiLivraisonJours min: 1, max: 90
        actif nullable: false, bindable: false
        motifDesactivation nullable: true, maxSize: 200, bindable: false
        dateCreated nullable: true, bindable: false
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