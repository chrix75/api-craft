package fr.arolla.api.craft.etablissement.domain;

import fr.arolla.api.craft.etablissement.domain.exception.InvalidDomainValueException;
import lombok.Getter;

import java.util.Objects;
import java.util.StringJoiner;
import java.util.regex.Pattern;

@Getter
public class Siren {
    private static final int minLength = 6;
    private static final int maxLength = 9;
    private final Pattern pattern = Pattern.compile("\\d+");

    private final String value;

    public Siren(String value) {
        String trimmed = value.trim();
        if (trimmed.isEmpty()) {
            throw new InvalidDomainValueException("siren.empty");
        }
        if (!acceptableSiren(trimmed)) {
            throw new InvalidDomainValueException("siren.invalid");
        }

        this.value = String.format("%9s", trimmed)
                           .replace(' ', '0');
    }

    private boolean acceptableSiren(String value) {
        return value.length() >= minLength &&
                value.length() <= maxLength &&
                pattern.matcher(value).matches();
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Siren.class.getSimpleName() + "[", "]")
                .add("value='" + value + "'")
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Siren siren = (Siren) o;
        return value.equals(siren.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
