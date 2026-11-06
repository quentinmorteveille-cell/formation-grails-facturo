package facturo

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification
import spock.lang.Unroll

class DesactivationCommandSpec extends Specification {

    @Unroll
    void "le motif '#motif' doit être valide : #attendu"() {
        expect:
        new DesactivationCommand(
                fournisseurId: fournisseurId,
                motif: motif
        ).validate() == attendu

        where:
        fournisseurId | motif                                      | attendu
        1L            | 'Fin du contrat commercial'                | true
        2L            | 'Fournisseur non conforme'                  | true
        3L            | '1234567890'                               | true
        4L            | 'Arrêt activité fournisseur'                | true
        5L            | 'Trop court'                               | true
        6L            | 'Court'                                    | false
        7L            | ''                                         | false
        8L            | null                                       | false
        9L            | 'A' * 201                                  | false
    }

    void "un identifiant fournisseur est obligatoire"() {
        expect:
        !new DesactivationCommand(
                fournisseurId: null,
                motif: 'Motif suffisamment long'
        ).validate()
    }
}