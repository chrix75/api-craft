package fr.arolla.api.craft.etablissement.domain;

import fr.arolla.api.craft.etablissement.domain.exception.InvalidDomainValueException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class ValidationSirenTest {
    @Test
    void validSiren() {
        Siren siren = new Siren("123456789");
        assertThat(siren.getValue())
                .isEqualTo("123456789");
    }

    @Test
    void emptySiren() {
        assertThatThrownBy(() -> new Siren(""))
                .isInstanceOf(InvalidDomainValueException.class)
                .hasFieldOrPropertyWithValue("code", "siren.empty");
    }

    @Test
    void shortSiren() {
        Siren siren = new Siren("123456");
        assertThat(siren.getValue())
                .isEqualTo("000123456");
    }

    @Test
    void tooLongSiren() {
        assertThatThrownBy(() -> new Siren("1234567890"))
                .isInstanceOf(InvalidDomainValueException.class)
                .hasFieldOrPropertyWithValue("code", "siren.invalid");
    }

    @Test
    void tooShortSiren() {
        assertThatThrownBy(() -> new Siren("12345"))
                .isInstanceOf(InvalidDomainValueException.class)
                .hasFieldOrPropertyWithValue("code", "siren.invalid");
    }

    @Test
    void invalidSiren() {
        assertThatThrownBy(() -> new Siren("ABCDEFGHI"))
                .isInstanceOf(InvalidDomainValueException.class)
                .hasFieldOrPropertyWithValue("code", "siren.invalid");
    }
}