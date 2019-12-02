package com.goelmo.elmo.web.rest;

import com.goelmo.elmo.domain.Intervenant;
import com.goelmo.elmo.service.IntervenantService;
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
 * REST controller for managing {@link com.goelmo.elmo.domain.Intervenant}.
 */
@RestController
@RequestMapping("/api")
public class IntervenantResource {

    private final Logger log = LoggerFactory.getLogger(IntervenantResource.class);

    private static final String ENTITY_NAME = "intervenant";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final IntervenantService intervenantService;

    public IntervenantResource(IntervenantService intervenantService) {
        this.intervenantService = intervenantService;
    }

    /**
     * {@code POST  /intervenants} : Create a new intervenant.
     *
     * @param intervenant the intervenant to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new intervenant, or with status {@code 400 (Bad Request)} if the intervenant has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/intervenants")
    public ResponseEntity<Intervenant> createIntervenant(@RequestBody Intervenant intervenant) throws URISyntaxException {
        log.debug("REST request to save Intervenant : {}", intervenant);
        if (intervenant.getId() != null) {
            throw new BadRequestAlertException("A new intervenant cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Intervenant result = intervenantService.save(intervenant);
        return ResponseEntity.created(new URI("/api/intervenants/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /intervenants} : Updates an existing intervenant.
     *
     * @param intervenant the intervenant to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated intervenant,
     * or with status {@code 400 (Bad Request)} if the intervenant is not valid,
     * or with status {@code 500 (Internal Server Error)} if the intervenant couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/intervenants")
    public ResponseEntity<Intervenant> updateIntervenant(@RequestBody Intervenant intervenant) throws URISyntaxException {
        log.debug("REST request to update Intervenant : {}", intervenant);
        if (intervenant.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Intervenant result = intervenantService.save(intervenant);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, intervenant.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /intervenants} : get all the intervenants.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of intervenants in body.
     */
    @GetMapping("/intervenants")
    public List<Intervenant> getAllIntervenants() {
        log.debug("REST request to get all Intervenants");
        return intervenantService.findAll();
    }

    /**
     * {@code GET  /intervenants/:id} : get the "id" intervenant.
     *
     * @param id the id of the intervenant to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the intervenant, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/intervenants/{id}")
    public ResponseEntity<Intervenant> getIntervenant(@PathVariable Long id) {
        log.debug("REST request to get Intervenant : {}", id);
        Optional<Intervenant> intervenant = intervenantService.findOne(id);
        return ResponseUtil.wrapOrNotFound(intervenant);
    }

    /**
     * {@code DELETE  /intervenants/:id} : delete the "id" intervenant.
     *
     * @param id the id of the intervenant to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/intervenants/{id}")
    public ResponseEntity<Void> deleteIntervenant(@PathVariable Long id) {
        log.debug("REST request to delete Intervenant : {}", id);
        intervenantService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
