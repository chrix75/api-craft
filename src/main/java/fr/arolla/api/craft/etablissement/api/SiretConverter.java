package fr.arolla.api.craft.etablissement.api;

import fr.arolla.api.craft.etablissement.domain.Nic;
import fr.arolla.api.craft.etablissement.domain.Siren;
import fr.arolla.api.craft.etablissement.domain.Siret;
import fr.arolla.api.craft.etablissement.domain.exception.InvalidDomainValueException;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class SiretConverter implements Converter<String, Siret> {
    @Override
    public Siret convert(String source) {
        SiretInfoExtraction extraction = new SiretInfoExtraction(source);
        try {
            Nic nic = extraction.nic();
            Siren siren = extraction.siren();
            return new Siret(siren, nic);
        } catch (InvalidDomainValueException ex) {
            throw new InvalidDomainValueException("siret.invalid", ex);
        }

    }
}
