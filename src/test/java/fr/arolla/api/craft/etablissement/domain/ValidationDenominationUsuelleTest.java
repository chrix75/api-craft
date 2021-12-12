package fr.arolla.api.craft.etablissement.domain;

import fr.arolla.api.craft.etablissement.domain.exception.InvalidDomainValueException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class ValidationDenominationUsuelleTest {
    @Test
    void validDenominationUsuelle() {
        DenominationUsuelle denominationUsuelle = new DenominationUsuelle("Arolla SAS");
        assertThat(denominationUsuelle.getValue())
                .isEqualTo("Arolla SAS");
    }

    @Test
    void emptyDenominationUsuelle() {
        assertThatThrownBy(() -> new DenominationUsuelle(""))
                .isInstanceOf(InvalidDomainValueException.class)
                .hasFieldOrPropertyWithValue("code", "denominationUsuelle.empty");
    }

    @Test
    void tooLongDenominationUsuelle() {
        String denimonation = "A".repeat(101);
        assertThatThrownBy(() -> new DenominationUsuelle(denimonation))
                .isInstanceOf(InvalidDomainValueException.class)
                .hasFieldOrPropertyWithValue("code", "denominationUsuelle.invalid");
    }

    @Test
    void replaceAccentedChars() {
        DenominationUsuelle denominationUsuelle = new DenominationUsuelle("Léo");
        assertThat(denominationUsuelle.getValue())
                .isEqualTo("Leo");
    }
}