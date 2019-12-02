package com.goelmo.elmo.web.rest;

import com.goelmo.elmo.domain.FormulaireConsentement;
import com.goelmo.elmo.service.FormulaireConsentementService;
import com.goelmo.elmo.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.goelmo.elmo.domain.FormulaireConsentement}.
 */
@RestController
@RequestMapping("/api")
public class FormulaireConsentementResource {

    private final Logger log = LoggerFactory.getLogger(FormulaireConsentementResource.class);

    private static final String ENTITY_NAME = "formulaireConsentement";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FormulaireConsentementService formulaireConsentementService;

    public FormulaireConsentementResource(FormulaireConsentementService formulaireConsentementService) {
        this.formulaireConsentementService = formulaireConsentementService;
    }

    /**
     * {@code POST  /formulaire-consentements} : Create a new formulaireConsentement.
     *
     * @param formulaireConsentement the formulaireConsentement to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new formulaireConsentement, or with status {@code 400 (Bad Request)} if the formulaireConsentement has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/formulaire-consentements")
    public ResponseEntity<FormulaireConsentement> createFormulaireConsentement(@RequestBody FormulaireConsentement formulaireConsentement) throws URISyntaxException {
        log.debug("REST request to save FormulaireConsentement : {}", formulaireConsentement);
        if (formulaireConsentement.getId() != null) {
            throw new BadRequestAlertException("A new formulaireConsentement cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FormulaireConsentement result = formulaireConsentementService.save(formulaireConsentement);
        return ResponseEntity.created(new URI("/api/formulaire-consentements/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /formulaire-consentements} : Updates an existing formulaireConsentement.
     *
     * @param formulaireConsentement the formulaireConsentement to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated formulaireConsentement,
     * or with status {@code 400 (Bad Request)} if the formulaireConsentement is not valid,
     * or with status {@code 500 (Internal Server Error)} if the formulaireConsentement couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/formulaire-consentements")
    public ResponseEntity<FormulaireConsentement> updateFormulaireConsentement(@RequestBody FormulaireConsentement formulaireConsentement) throws URISyntaxException {
        log.debug("REST request to update FormulaireConsentement : {}", formulaireConsentement);
        if (formulaireConsentement.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        FormulaireConsentement result = formulaireConsentementService.save(formulaireConsentement);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, formulaireConsentement.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /formulaire-consentements} : get all the formulaireConsentements.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of formulaireConsentements in body.
     */
    @GetMapping("/formulaire-consentements")
    public List<FormulaireConsentement> getAllFormulaireConsentements() {
        log.debug("REST request to get all FormulaireConsentements");
        return formulaireConsentementService.findAll();
    }

    /**
     * {@code GET  /formulaire-consentements/:id} : get the "id" formulaireConsentement.
     *
     * @param id the id of the formulaireConsentement to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the formulaireConsentement, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/formulaire-consentements/{id}")
    public ResponseEntity<FormulaireConsentement> getFormulaireConsentement(@PathVariable Long id) {
        log.debug("REST request to get FormulaireConsentement : {}", id);
        Optional<FormulaireConsentement> formulaireConsentement = formulaireConsentementService.findOne(id);
        return ResponseUtil.wrapOrNotFound(formulaireConsentement);
    }

    /**
     * {@code DELETE  /formulaire-consentements/:id} : delete the "id" formulaireConsentement.
     *
     * @param id the id of the formulaireConsentement to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/formulaire-consentements/{id}")
    public ResponseEntity<Void> deleteFormulaireConsentement(@PathVariable Long id) {
        log.debug("REST request to delete FormulaireConsentement : {}", id);
        formulaireConsentementService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
