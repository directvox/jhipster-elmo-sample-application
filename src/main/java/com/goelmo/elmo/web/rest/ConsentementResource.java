package com.goelmo.elmo.web.rest;

import com.goelmo.elmo.domain.Consentement;
import com.goelmo.elmo.service.ConsentementService;
import com.goelmo.elmo.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.goelmo.elmo.domain.Consentement}.
 */
@RestController
@RequestMapping("/api")
public class ConsentementResource {

    private final Logger log = LoggerFactory.getLogger(ConsentementResource.class);

    private static final String ENTITY_NAME = "consentement";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ConsentementService consentementService;

    public ConsentementResource(ConsentementService consentementService) {
        this.consentementService = consentementService;
    }

    /**
     * {@code POST  /consentements} : Create a new consentement.
     *
     * @param consentement the consentement to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new consentement, or with status {@code 400 (Bad Request)} if the consentement has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/consentements")
    public ResponseEntity<Consentement> createConsentement(@Valid @RequestBody Consentement consentement) throws URISyntaxException {
        log.debug("REST request to save Consentement : {}", consentement);
        if (consentement.getId() != null) {
            throw new BadRequestAlertException("A new consentement cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Consentement result = consentementService.save(consentement);
        return ResponseEntity.created(new URI("/api/consentements/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /consentements} : Updates an existing consentement.
     *
     * @param consentement the consentement to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated consentement,
     * or with status {@code 400 (Bad Request)} if the consentement is not valid,
     * or with status {@code 500 (Internal Server Error)} if the consentement couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/consentements")
    public ResponseEntity<Consentement> updateConsentement(@Valid @RequestBody Consentement consentement) throws URISyntaxException {
        log.debug("REST request to update Consentement : {}", consentement);
        if (consentement.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Consentement result = consentementService.save(consentement);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, consentement.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /consentements} : get all the consentements.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of consentements in body.
     */
    @GetMapping("/consentements")
    public List<Consentement> getAllConsentements() {
        log.debug("REST request to get all Consentements");
        return consentementService.findAll();
    }

    /**
     * {@code GET  /consentements/:id} : get the "id" consentement.
     *
     * @param id the id of the consentement to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the consentement, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/consentements/{id}")
    public ResponseEntity<Consentement> getConsentement(@PathVariable Long id) {
        log.debug("REST request to get Consentement : {}", id);
        Optional<Consentement> consentement = consentementService.findOne(id);
        return ResponseUtil.wrapOrNotFound(consentement);
    }

    /**
     * {@code DELETE  /consentements/:id} : delete the "id" consentement.
     *
     * @param id the id of the consentement to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/consentements/{id}")
    public ResponseEntity<Void> deleteConsentement(@PathVariable Long id) {
        log.debug("REST request to delete Consentement : {}", id);
        consentementService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
