package com.goelmo.elmo.web.rest;

import com.goelmo.elmo.domain.ReponseDemandeDeService;
import com.goelmo.elmo.service.ReponseDemandeDeServiceService;
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
 * REST controller for managing {@link com.goelmo.elmo.domain.ReponseDemandeDeService}.
 */
@RestController
@RequestMapping("/api")
public class ReponseDemandeDeServiceResource {

    private final Logger log = LoggerFactory.getLogger(ReponseDemandeDeServiceResource.class);

    private static final String ENTITY_NAME = "reponseDemandeDeService";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ReponseDemandeDeServiceService reponseDemandeDeServiceService;

    public ReponseDemandeDeServiceResource(ReponseDemandeDeServiceService reponseDemandeDeServiceService) {
        this.reponseDemandeDeServiceService = reponseDemandeDeServiceService;
    }

    /**
     * {@code POST  /reponse-demande-de-services} : Create a new reponseDemandeDeService.
     *
     * @param reponseDemandeDeService the reponseDemandeDeService to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new reponseDemandeDeService, or with status {@code 400 (Bad Request)} if the reponseDemandeDeService has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/reponse-demande-de-services")
    public ResponseEntity<ReponseDemandeDeService> createReponseDemandeDeService(@Valid @RequestBody ReponseDemandeDeService reponseDemandeDeService) throws URISyntaxException {
        log.debug("REST request to save ReponseDemandeDeService : {}", reponseDemandeDeService);
        if (reponseDemandeDeService.getId() != null) {
            throw new BadRequestAlertException("A new reponseDemandeDeService cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ReponseDemandeDeService result = reponseDemandeDeServiceService.save(reponseDemandeDeService);
        return ResponseEntity.created(new URI("/api/reponse-demande-de-services/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /reponse-demande-de-services} : Updates an existing reponseDemandeDeService.
     *
     * @param reponseDemandeDeService the reponseDemandeDeService to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated reponseDemandeDeService,
     * or with status {@code 400 (Bad Request)} if the reponseDemandeDeService is not valid,
     * or with status {@code 500 (Internal Server Error)} if the reponseDemandeDeService couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/reponse-demande-de-services")
    public ResponseEntity<ReponseDemandeDeService> updateReponseDemandeDeService(@Valid @RequestBody ReponseDemandeDeService reponseDemandeDeService) throws URISyntaxException {
        log.debug("REST request to update ReponseDemandeDeService : {}", reponseDemandeDeService);
        if (reponseDemandeDeService.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ReponseDemandeDeService result = reponseDemandeDeServiceService.save(reponseDemandeDeService);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, reponseDemandeDeService.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /reponse-demande-de-services} : get all the reponseDemandeDeServices.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of reponseDemandeDeServices in body.
     */
    @GetMapping("/reponse-demande-de-services")
    public List<ReponseDemandeDeService> getAllReponseDemandeDeServices() {
        log.debug("REST request to get all ReponseDemandeDeServices");
        return reponseDemandeDeServiceService.findAll();
    }

    /**
     * {@code GET  /reponse-demande-de-services/:id} : get the "id" reponseDemandeDeService.
     *
     * @param id the id of the reponseDemandeDeService to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the reponseDemandeDeService, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/reponse-demande-de-services/{id}")
    public ResponseEntity<ReponseDemandeDeService> getReponseDemandeDeService(@PathVariable Long id) {
        log.debug("REST request to get ReponseDemandeDeService : {}", id);
        Optional<ReponseDemandeDeService> reponseDemandeDeService = reponseDemandeDeServiceService.findOne(id);
        return ResponseUtil.wrapOrNotFound(reponseDemandeDeService);
    }

    /**
     * {@code DELETE  /reponse-demande-de-services/:id} : delete the "id" reponseDemandeDeService.
     *
     * @param id the id of the reponseDemandeDeService to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/reponse-demande-de-services/{id}")
    public ResponseEntity<Void> deleteReponseDemandeDeService(@PathVariable Long id) {
        log.debug("REST request to delete ReponseDemandeDeService : {}", id);
        reponseDemandeDeServiceService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
