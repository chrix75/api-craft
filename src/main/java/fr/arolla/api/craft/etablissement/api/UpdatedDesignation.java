package fr.arolla.api.craft.etablissement.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import fr.arolla.api.craft.etablissement.domain.Designation;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@JsonInclude(Include.NON_NULL)
public class UpdatedDesignation {
    public final String denominationUsuelle;
    public final String enseigne;

    public static UpdatedDesignation from(Designation source) {
        return new UpdatedDesignation(
                source.getDenominationUsuelle().getValue(),
                source.getEnseigne().isEmpty() ? null : source.getEnseigne().getValue()
        );
    }
}
