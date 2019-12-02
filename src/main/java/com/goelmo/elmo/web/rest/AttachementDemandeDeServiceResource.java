package com.goelmo.elmo.web.rest;

import com.goelmo.elmo.domain.AttachementDemandeDeService;
import com.goelmo.elmo.service.AttachementDemandeDeServiceService;
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
 * REST controller for managing {@link com.goelmo.elmo.domain.AttachementDemandeDeService}.
 */
@RestController
@RequestMapping("/api")
public class AttachementDemandeDeServiceResource {

    private final Logger log = LoggerFactory.getLogger(AttachementDemandeDeServiceResource.class);

    private static final String ENTITY_NAME = "attachementDemandeDeService";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AttachementDemandeDeServiceService attachementDemandeDeServiceService;

    public AttachementDemandeDeServiceResource(AttachementDemandeDeServiceService attachementDemandeDeServiceService) {
        this.attachementDemandeDeServiceService = attachementDemandeDeServiceService;
    }

    /**
     * {@code POST  /attachement-demande-de-services} : Create a new attachementDemandeDeService.
     *
     * @param attachementDemandeDeService the attachementDemandeDeService to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new attachementDemandeDeService, or with status {@code 400 (Bad Request)} if the attachementDemandeDeService has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/attachement-demande-de-services")
    public ResponseEntity<AttachementDemandeDeService> createAttachementDemandeDeService(@RequestBody AttachementDemandeDeService attachementDemandeDeService) throws URISyntaxException {
        log.debug("REST request to save AttachementDemandeDeService : {}", attachementDemandeDeService);
        if (attachementDemandeDeService.getId() != null) {
            throw new BadRequestAlertException("A new attachementDemandeDeService cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AttachementDemandeDeService result = attachementDemandeDeServiceService.save(attachementDemandeDeService);
        return ResponseEntity.created(new URI("/api/attachement-demande-de-services/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /attachement-demande-de-services} : Updates an existing attachementDemandeDeService.
     *
     * @param attachementDemandeDeService the attachementDemandeDeService to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated attachementDemandeDeService,
     * or with status {@code 400 (Bad Request)} if the attachementDemandeDeService is not valid,
     * or with status {@code 500 (Internal Server Error)} if the attachementDemandeDeService couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/attachement-demande-de-services")
    public ResponseEntity<AttachementDemandeDeService> updateAttachementDemandeDeService(@RequestBody AttachementDemandeDeService attachementDemandeDeService) throws URISyntaxException {
        log.debug("REST request to update AttachementDemandeDeService : {}", attachementDemandeDeService);
        if (attachementDemandeDeService.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AttachementDemandeDeService result = attachementDemandeDeServiceService.save(attachementDemandeDeService);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, attachementDemandeDeService.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /attachement-demande-de-services} : get all the attachementDemandeDeServices.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of attachementDemandeDeServices in body.
     */
    @GetMapping("/attachement-demande-de-services")
    public List<AttachementDemandeDeService> getAllAttachementDemandeDeServices() {
        log.debug("REST request to get all AttachementDemandeDeServices");
        return attachementDemandeDeServiceService.findAll();
    }

    /**
     * {@code GET  /attachement-demande-de-services/:id} : get the "id" attachementDemandeDeService.
     *
     * @param id the id of the attachementDemandeDeService to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the attachementDemandeDeService, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/attachement-demande-de-services/{id}")
    public ResponseEntity<AttachementDemandeDeService> getAttachementDemandeDeService(@PathVariable Long id) {
        log.debug("REST request to get AttachementDemandeDeService : {}", id);
        Optional<AttachementDemandeDeService> attachementDemandeDeService = attachementDemandeDeServiceService.findOne(id);
        return ResponseUtil.wrapOrNotFound(attachementDemandeDeService);
    }

    /**
     * {@code DELETE  /attachement-demande-de-services/:id} : delete the "id" attachementDemandeDeService.
     *
     * @param id the id of the attachementDemandeDeService to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/attachement-demande-de-services/{id}")
    public ResponseEntity<Void> deleteAttachementDemandeDeService(@PathVariable Long id) {
        log.debug("REST request to delete AttachementDemandeDeService : {}", id);
        attachementDemandeDeServiceService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
