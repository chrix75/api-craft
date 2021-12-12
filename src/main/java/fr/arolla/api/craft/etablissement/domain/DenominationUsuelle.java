package fr.arolla.api.craft.etablissement.domain;

import fr.arolla.api.craft.etablissement.domain.exception.InvalidDomainValueException;
import lombok.Getter;

import java.util.StringJoiner;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.CASE_INSENSITIVE;

@Getter
public class DenominationUsuelle {
    private static final int minLength = 1;
    private static final int maxLength = 100;
    private final Pattern pattern = Pattern.compile("[A-Z0-9 _+É-]+", CASE_INSENSITIVE | Pattern.UNICODE_CASE);

    private final String value;

    public DenominationUsuelle(String value) {
        String trimmed = value.trim();
        if (trimmed.isEmpty()) {
            throw new InvalidDomainValueException("denominationUsuelle.empty");
        }
        if (!acceptableDenominationUsuelle(trimmed)) {
            throw new InvalidDomainValueException("denominationUsuelle.invalid");
        }

        this.value = cleanValue(trimmed);
    }

    private String cleanValue(String source) {
        return source.replace('é', 'e');
    }

    private boolean acceptableDenominationUsuelle(String value) {
        return value.length() >= minLength && value.length() <= maxLength && pattern.matcher(value).matches();
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", DenominationUsuelle.class.getSimpleName() + "[", "]")
                .add("value='" + value + "'")
                .toString();
    }
}
