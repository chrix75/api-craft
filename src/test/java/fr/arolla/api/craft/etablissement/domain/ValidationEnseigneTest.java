package fr.arolla.api.craft.etablissement.domain;

import fr.arolla.api.craft.etablissement.domain.exception.InvalidDomainValueException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class ValidationEnseigneTest {

    @Test
    void validEnseigne() {
        Enseigne enseigne = new Enseigne("Arolla");
        assertThat(enseigne.getValue())
                .isEqualTo("Arolla");
    }

    @Test
    void emptyEnseigne() {
        Enseigne enseigne = new Enseigne("");
        assertThat(enseigne.getValue()).isEmpty();
    }

    @Test
    void tooLongEnseigne() {
        String value = "A".repeat(51);
        assertThatThrownBy(() -> new Enseigne(value))
                .isInstanceOf(InvalidDomainValueException.class)
                .hasFieldOrPropertyWithValue("code", "enseigne.invalid");
    }

    @Test
    void replaceAccentedChars() {
        Enseigne enseigne = new Enseigne("Léo");
        assertThat(enseigne.getValue())
                .isEqualTo("Leo");
    }
}