package fr.arolla.api.craft.etablissement.domain;

import fr.arolla.api.craft.etablissement.domain.exception.InvalidDomainValueException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class ValidationNicTest {
    @Test
    void validNic() {
        Nic nic = new Nic("12345");
        assertThat(nic.getValue())
                .isEqualTo("12345");
    }

    @Test
    void emptyNic() {
        assertThatThrownBy(() -> new Nic(""))
                .isInstanceOf(InvalidDomainValueException.class)
                .hasFieldOrPropertyWithValue("code", "nic.empty");
    }

    @Test
    void tooLongNic() {
        assertThatThrownBy(() -> new Nic("123456"))
                .isInstanceOf(InvalidDomainValueException.class)
                .hasFieldOrPropertyWithValue("code", "nic.invalid");
    }

    @Test
    void tooShortNic() {
        assertThatThrownBy(() -> new Nic("1234"))
                .isInstanceOf(InvalidDomainValueException.class)
                .hasFieldOrPropertyWithValue("code", "nic.invalid");
    }

    @Test
    void invalidNicValue() {
        assertThatThrownBy(() -> new Nic("ABCDE"))
                .isInstanceOf(InvalidDomainValueException.class)
                .hasFieldOrPropertyWithValue("code", "nic.invalid");
    }
}