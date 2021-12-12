package fr.arolla.api.craft.etablissement;

import fr.arolla.api.craft.etablissement.api.SiretInfoExtraction;
import fr.arolla.api.craft.etablissement.domain.Nic;
import fr.arolla.api.craft.etablissement.domain.Siren;
import fr.arolla.api.craft.etablissement.domain.exception.InvalidDomainValueException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class SiretInfoExtractionTest {

    @Test
    void extractSiretInfo() {
        String source = "12345678912345";
        SiretInfoExtraction extraction = new SiretInfoExtraction(source);

        assertThat(extraction.siren())
                .isEqualTo(new Siren("123456789"));

        assertThat(extraction.nic())
                .isEqualTo(new Nic("12345"));
    }

    @Test
    void shortSiret() {
        String source = "12345612345";
        SiretInfoExtraction extraction = new SiretInfoExtraction(source);

        assertThat(extraction.siren())
                .isEqualTo(new Siren("123456"));

        assertThat(extraction.nic())
                .isEqualTo(new Nic("12345"));
    }

    @Test
    void tooShortSiret() {
        String source = "1234512345";
        SiretInfoExtraction extraction = new SiretInfoExtraction(source);

        assertThatThrownBy(extraction::siren)
                .isInstanceOf(InvalidDomainValueException.class)
                .hasFieldOrPropertyWithValue("code", "siren.invalid");
    }

    @Test
    void emptySiret() {
        String source = "";
        SiretInfoExtraction extraction = new SiretInfoExtraction(source);

        assertThatThrownBy(extraction::siren)
                .isInstanceOf(InvalidDomainValueException.class)
                .hasFieldOrPropertyWithValue("code", "siren.empty");

        assertThatThrownBy(extraction::nic)
                .isInstanceOf(InvalidDomainValueException.class)
                .hasFieldOrPropertyWithValue("code", "nic.empty");
    }
}