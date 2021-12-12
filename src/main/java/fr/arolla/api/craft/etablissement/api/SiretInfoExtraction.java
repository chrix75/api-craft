package fr.arolla.api.craft.etablissement.api;

import fr.arolla.api.craft.etablissement.domain.Nic;
import fr.arolla.api.craft.etablissement.domain.Siren;

public class SiretInfoExtraction {
    private final String source;
    private final int nicIndex;

    public SiretInfoExtraction(String source) {
        this.source = source;
        this.nicIndex = source.length() - 5;
    }

    public Nic nic() {
        if (nicIndex > 0) {
            return new Nic(source.substring(nicIndex));
        }
        return new Nic("");
    }

    public Siren siren() {
        if (nicIndex > 0) {
            return new Siren(source.substring(0, nicIndex));
        }
        return new Siren("");
    }
}
