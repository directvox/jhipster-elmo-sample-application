package com.goelmo.elmo.web.rest;

import com.goelmo.elmo.domain.DemandeDeService;
import com.goelmo.elmo.service.DemandeDeServiceService;
import com.goelmo.elmo.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.goelmo.elmo.domain.DemandeDeService}.
 */
@RestController
@RequestMapping("/api")
public class DemandeDeServiceResource {

    private final Logger log = LoggerFactory.getLogger(DemandeDeServiceResource.class);

    private static final String ENTITY_NAME = "demandeDeService";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DemandeDeServiceService demandeDeServiceService;

    public DemandeDeServiceResource(DemandeDeServiceService demandeDeServiceService) {
        this.demandeDeServiceService = demandeDeServiceService;
    }

    /**
     * {@code POST  /demande-de-services} : Create a new demandeDeService.
     *
     * @param demandeDeService the demandeDeService to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new demandeDeService, or with status {@code 400 (Bad Request)} if the demandeDeService has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/demande-de-services")
    public ResponseEntity<DemandeDeService> createDemandeDeService(@Valid @RequestBody DemandeDeService demandeDeService) throws URISyntaxException {
        log.debug("REST request to save DemandeDeService : {}", demandeDeService);
        if (demandeDeService.getId() != null) {
            throw new BadRequestAlertException("A new demandeDeService cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DemandeDeService result = demandeDeServiceService.save(demandeDeService);
        return ResponseEntity.created(new URI("/api/demande-de-services/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /demande-de-services} : Updates an existing demandeDeService.
     *
     * @param demandeDeService the demandeDeService to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated demandeDeService,
     * or with status {@code 400 (Bad Request)} if the demandeDeService is not valid,
     * or with status {@code 500 (Internal Server Error)} if the demandeDeService couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/demande-de-services")
    public ResponseEntity<DemandeDeService> updateDemandeDeService(@Valid @RequestBody DemandeDeService demandeDeService) throws URISyntaxException {
        log.debug("REST request to update DemandeDeService : {}", demandeDeService);
        if (demandeDeService.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DemandeDeService result = demandeDeServiceService.save(demandeDeService);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, demandeDeService.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /demande-de-services} : get all the demandeDeServices.
     *

     * @param pageable the pagination information.
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of demandeDeServices in body.
     */
    @GetMapping("/demande-de-services")
    public ResponseEntity<List<DemandeDeService>> getAllDemandeDeServices(Pageable pageable, @RequestParam(required = false, defaultValue = "false") boolean eagerload) {
        log.debug("REST request to get a page of DemandeDeServices");
        Page<DemandeDeService> page;
        if (eagerload) {
            page = demandeDeServiceService.findAllWithEagerRelationships(pageable);
        } else {
            page = demandeDeServiceService.findAll(pageable);
        }
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /demande-de-services/:id} : get the "id" demandeDeService.
     *
     * @param id the id of the demandeDeService to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the demandeDeService, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/demande-de-services/{id}")
    public ResponseEntity<DemandeDeService> getDemandeDeService(@PathVariable Long id) {
        log.debug("REST request to get DemandeDeService : {}", id);
        Optional<DemandeDeService> demandeDeService = demandeDeServiceService.findOne(id);
        return ResponseUtil.wrapOrNotFound(demandeDeService);
    }

    /**
     * {@code DELETE  /demande-de-services/:id} : delete the "id" demandeDeService.
     *
     * @param id the id of the demandeDeService to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/demande-de-services/{id}")
    public ResponseEntity<Void> deleteDemandeDeService(@PathVariable Long id) {
        log.debug("REST request to delete DemandeDeService : {}", id);
        demandeDeServiceService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
