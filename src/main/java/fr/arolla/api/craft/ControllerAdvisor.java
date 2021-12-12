package fr.arolla.api.craft;

import fr.arolla.api.craft.etablissement.domain.exception.InvalidDomainValueException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvisor {
    @ExceptionHandler(InvalidDomainValueException.class)
    public ResponseEntity<ErrorDomainMessage> handleInvalidDenominationUsuelle(InvalidDomainValueException ex) {
        return ResponseEntity.badRequest()
                             .body(new ErrorDomainMessage(ex.getCode()));
    }
}
