package fr.arolla.api.craft.etablissement.api;

import fr.arolla.api.craft.etablissement.domain.Designation;
import fr.arolla.api.craft.etablissement.domain.Siret;
import fr.arolla.api.craft.etablissement.domain.service.EtablissementService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
public class UpdateDesignationController {

    private final EtablissementService etablissementService;

    @PatchMapping(path = "/etablissement/{siret}/denominations")
    public ResponseEntity<UpdatedDesignation> updateDenominations(@PathVariable Siret siret,
                                                                  @RequestBody Designation designation) {

        log.info("SIRET: {}", siret);
        log.info("Designation: {}", designation);

        Designation updated = etablissementService.updateDesignation(siret, designation);

        return ResponseEntity.ok(UpdatedDesignation.from(updated));
    }
}
