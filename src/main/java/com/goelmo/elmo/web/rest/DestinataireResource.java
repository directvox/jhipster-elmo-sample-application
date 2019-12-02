package com.goelmo.elmo.web.rest;

import com.goelmo.elmo.domain.Destinataire;
import com.goelmo.elmo.service.DestinataireService;
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
 * REST controller for managing {@link com.goelmo.elmo.domain.Destinataire}.
 */
@RestController
@RequestMapping("/api")
public class DestinataireResource {

    private final Logger log = LoggerFactory.getLogger(DestinataireResource.class);

    private static final String ENTITY_NAME = "destinataire";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DestinataireService destinataireService;

    public DestinataireResource(DestinataireService destinataireService) {
        this.destinataireService = destinataireService;
    }

    /**
     * {@code POST  /destinataires} : Create a new destinataire.
     *
     * @param destinataire the destinataire to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new destinataire, or with status {@code 400 (Bad Request)} if the destinataire has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/destinataires")
    public ResponseEntity<Destinataire> createDestinataire(@RequestBody Destinataire destinataire) throws URISyntaxException {
        log.debug("REST request to save Destinataire : {}", destinataire);
        if (destinataire.getId() != null) {
            throw new BadRequestAlertException("A new destinataire cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Destinataire result = destinataireService.save(destinataire);
        return ResponseEntity.created(new URI("/api/destinataires/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /destinataires} : Updates an existing destinataire.
     *
     * @param destinataire the destinataire to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated destinataire,
     * or with status {@code 400 (Bad Request)} if the destinataire is not valid,
     * or with status {@code 500 (Internal Server Error)} if the destinataire couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/destinataires")
    public ResponseEntity<Destinataire> updateDestinataire(@RequestBody Destinataire destinataire) throws URISyntaxException {
        log.debug("REST request to update Destinataire : {}", destinataire);
        if (destinataire.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Destinataire result = destinataireService.save(destinataire);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, destinataire.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /destinataires} : get all the destinataires.
     *
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of destinataires in body.
     */
    @GetMapping("/destinataires")
    public List<Destinataire> getAllDestinataires(@RequestParam(required = false, defaultValue = "false") boolean eagerload) {
        log.debug("REST request to get all Destinataires");
        return destinataireService.findAll();
    }

    /**
     * {@code GET  /destinataires/:id} : get the "id" destinataire.
     *
     * @param id the id of the destinataire to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the destinataire, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/destinataires/{id}")
    public ResponseEntity<Destinataire> getDestinataire(@PathVariable Long id) {
        log.debug("REST request to get Destinataire : {}", id);
        Optional<Destinataire> destinataire = destinataireService.findOne(id);
        return ResponseUtil.wrapOrNotFound(destinataire);
    }

    /**
     * {@code DELETE  /destinataires/:id} : delete the "id" destinataire.
     *
     * @param id the id of the destinataire to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/destinataires/{id}")
    public ResponseEntity<Void> deleteDestinataire(@PathVariable Long id) {
        log.debug("REST request to delete Destinataire : {}", id);
        destinataireService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
