package fr.arolla.api.craft.etablissement.domain;

import fr.arolla.api.craft.etablissement.domain.exception.InvalidDomainValueException;
import lombok.*;

import java.util.StringJoiner;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.CASE_INSENSITIVE;

@Getter
public class Enseigne {
    private static final int maxLength = 50;
    private final Pattern pattern = Pattern.compile("[A-Z0-9 _+É-]*", CASE_INSENSITIVE | Pattern.UNICODE_CASE);

    private final String value;

    public Enseigne(String value) {
        if (!acceptableEnseigne(value)) {
            throw new InvalidDomainValueException("enseigne.invalid");
        }
        this.value = cleanValue(value);
    }

    private String cleanValue(String source) {
        return source.replace('é', 'e');
    }

    private boolean acceptableEnseigne(String value) {
        return value.length() <= maxLength && pattern.matcher(value).matches();
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Enseigne.class.getSimpleName() + "[", "]")
                .add("value='" + value + "'")
                .toString();
    }

    public boolean isEmpty() {
        return value.isEmpty();
    }
}
