package fr.arolla.api.craft.etablissement.domain;

import lombok.*;

import java.util.StringJoiner;

@AllArgsConstructor
@Getter
@Setter
public class Designation {
    private DenominationUsuelle denominationUsuelle;
    private Enseigne enseigne;

    @Override
    public String toString() {
        return new StringJoiner(", ", Designation.class.getSimpleName() + "[", "]")
                .add("denominationUsuelle=" + denominationUsuelle)
                .add("enseigne=" + enseigne)
                .toString();
    }
}
