package fr.arolla.api.craft.etablissement.domain.exception;

import lombok.Value;

@Value
public class InvalidDomainValueException extends RuntimeException {
    private final String code;

    public InvalidDomainValueException(String code) {
        this.code = code;
    }

    public InvalidDomainValueException(String code, Exception cause) {
        super(cause);
        this.code = code;
    }
}
