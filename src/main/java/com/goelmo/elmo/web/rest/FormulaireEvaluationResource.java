package com.goelmo.elmo.web.rest;

import com.goelmo.elmo.domain.FormulaireEvaluation;
import com.goelmo.elmo.service.FormulaireEvaluationService;
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
 * REST controller for managing {@link com.goelmo.elmo.domain.FormulaireEvaluation}.
 */
@RestController
@RequestMapping("/api")
public class FormulaireEvaluationResource {

    private final Logger log = LoggerFactory.getLogger(FormulaireEvaluationResource.class);

    private static final String ENTITY_NAME = "formulaireEvaluation";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FormulaireEvaluationService formulaireEvaluationService;

    public FormulaireEvaluationResource(FormulaireEvaluationService formulaireEvaluationService) {
        this.formulaireEvaluationService = formulaireEvaluationService;
    }

    /**
     * {@code POST  /formulaire-evaluations} : Create a new formulaireEvaluation.
     *
     * @param formulaireEvaluation the formulaireEvaluation to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new formulaireEvaluation, or with status {@code 400 (Bad Request)} if the formulaireEvaluation has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/formulaire-evaluations")
    public ResponseEntity<FormulaireEvaluation> createFormulaireEvaluation(@RequestBody FormulaireEvaluation formulaireEvaluation) throws URISyntaxException {
        log.debug("REST request to save FormulaireEvaluation : {}", formulaireEvaluation);
        if (formulaireEvaluation.getId() != null) {
            throw new BadRequestAlertException("A new formulaireEvaluation cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FormulaireEvaluation result = formulaireEvaluationService.save(formulaireEvaluation);
        return ResponseEntity.created(new URI("/api/formulaire-evaluations/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /formulaire-evaluations} : Updates an existing formulaireEvaluation.
     *
     * @param formulaireEvaluation the formulaireEvaluation to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated formulaireEvaluation,
     * or with status {@code 400 (Bad Request)} if the formulaireEvaluation is not valid,
     * or with status {@code 500 (Internal Server Error)} if the formulaireEvaluation couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/formulaire-evaluations")
    public ResponseEntity<FormulaireEvaluation> updateFormulaireEvaluation(@RequestBody FormulaireEvaluation formulaireEvaluation) throws URISyntaxException {
        log.debug("REST request to update FormulaireEvaluation : {}", formulaireEvaluation);
        if (formulaireEvaluation.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        FormulaireEvaluation result = formulaireEvaluationService.save(formulaireEvaluation);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, formulaireEvaluation.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /formulaire-evaluations} : get all the formulaireEvaluations.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of formulaireEvaluations in body.
     */
    @GetMapping("/formulaire-evaluations")
    public List<FormulaireEvaluation> getAllFormulaireEvaluations() {
        log.debug("REST request to get all FormulaireEvaluations");
        return formulaireEvaluationService.findAll();
    }

    /**
     * {@code GET  /formulaire-evaluations/:id} : get the "id" formulaireEvaluation.
     *
     * @param id the id of the formulaireEvaluation to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the formulaireEvaluation, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/formulaire-evaluations/{id}")
    public ResponseEntity<FormulaireEvaluation> getFormulaireEvaluation(@PathVariable Long id) {
        log.debug("REST request to get FormulaireEvaluation : {}", id);
        Optional<FormulaireEvaluation> formulaireEvaluation = formulaireEvaluationService.findOne(id);
        return ResponseUtil.wrapOrNotFound(formulaireEvaluation);
    }

    /**
     * {@code DELETE  /formulaire-evaluations/:id} : delete the "id" formulaireEvaluation.
     *
     * @param id the id of the formulaireEvaluation to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/formulaire-evaluations/{id}")
    public ResponseEntity<Void> deleteFormulaireEvaluation(@PathVariable Long id) {
        log.debug("REST request to delete FormulaireEvaluation : {}", id);
        formulaireEvaluationService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
