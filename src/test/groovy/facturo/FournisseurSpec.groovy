package facturo

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification
import spock.lang.Unroll

class FournisseurSpec extends Specification implements DomainUnitTest<Fournisseur> {

    @Unroll
    void "le SIRET '#siret' doit être valide : #attendu"() {
        expect:
        new Fournisseur(
                code: 'TEST',
                raisonSociale: 'Test',
                siret: siret
        ).validate(['siret']) == attendu

        where:
        siret            | attendu
        '12345678900015' | true
        '73282932000074' | true
        '55210055400013' | true
        '12345678912341' | true
        '54756834567144' | false
        '12345678900016' | false
        '12345678900017' | false
        '12345678900018' | false
    }
}