package fr.arolla.api.craft.etablissement.domain;

import fr.arolla.api.craft.etablissement.domain.exception.InvalidDomainValueException;
import lombok.Getter;

import java.util.Objects;
import java.util.StringJoiner;
import java.util.regex.Pattern;

@Getter
public class Nic {
    private final Pattern pattern = Pattern.compile("\\d+");

    private final String value;

    public Nic(String value) {
        String trimmed = value.trim();
        if (trimmed.isEmpty()) {
            throw new InvalidDomainValueException("nic.empty");
        }
        if (!acceptableNic(trimmed)) {
            throw new InvalidDomainValueException("nic.invalid");
        }

        this.value = trimmed;
    }

    private boolean acceptableNic(String value) {
        return value.length() == 5 &&
                pattern.matcher(value).matches();
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Nic.class.getSimpleName() + "[", "]")
                .add("value='" + value + "'")
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Nic nic = (Nic) o;
        return value.equals(nic.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
