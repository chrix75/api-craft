package fr.arolla.api.craft.etablissement.domain.service;

import fr.arolla.api.craft.etablissement.domain.DenominationUsuelle;
import fr.arolla.api.craft.etablissement.domain.Designation;
import fr.arolla.api.craft.etablissement.domain.Enseigne;
import fr.arolla.api.craft.etablissement.domain.Siret;

/**
 * Fake class for learning case.
 */
public class EtablissementService {
    private Designation currentDesignation = new Designation(
            new DenominationUsuelle("Arolla SAS"),
            new Enseigne("Arolla")
    );

    public Designation updateDesignation(Siret siret, Designation update) {
        if (update.getDenominationUsuelle() != null) {
            currentDesignation.setDenominationUsuelle(update.getDenominationUsuelle());
        }

        if (update.getEnseigne() != null) {
            currentDesignation.setEnseigne(update.getEnseigne());
        }

        return new Designation(
                currentDesignation.getDenominationUsuelle(),
                currentDesignation.getEnseigne()
        );
    }

    public void resetDesignation() {
        currentDesignation = new Designation(
                new DenominationUsuelle("Arolla SAS"),
                new Enseigne("Arolla")
        );
    }
}
