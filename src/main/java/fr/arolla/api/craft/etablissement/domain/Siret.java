package fr.arolla.api.craft.etablissement.domain;

import java.util.StringJoiner;

public class Siret {
    private final Siren siren;
    private final Nic nic;


    public Siret(Siren siren, Nic nic) {
        this.siren = siren;
        this.nic = nic;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Siret.class.getSimpleName() + "[", "]")
                .add("siren='" + siren + "'")
                .add("nic='" + nic + "'")
                .toString();
    }
}
