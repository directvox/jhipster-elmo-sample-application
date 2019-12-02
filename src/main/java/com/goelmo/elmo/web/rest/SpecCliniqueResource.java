package com.goelmo.elmo.web.rest;

import com.goelmo.elmo.domain.SpecClinique;
import com.goelmo.elmo.service.SpecCliniqueService;
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
 * REST controller for managing {@link com.goelmo.elmo.domain.SpecClinique}.
 */
@RestController
@RequestMapping("/api")
public class SpecCliniqueResource {

    private final Logger log = LoggerFactory.getLogger(SpecCliniqueResource.class);

    private static final String ENTITY_NAME = "specClinique";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SpecCliniqueService specCliniqueService;

    public SpecCliniqueResource(SpecCliniqueService specCliniqueService) {
        this.specCliniqueService = specCliniqueService;
    }

    /**
     * {@code POST  /spec-cliniques} : Create a new specClinique.
     *
     * @param specClinique the specClinique to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new specClinique, or with status {@code 400 (Bad Request)} if the specClinique has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/spec-cliniques")
    public ResponseEntity<SpecClinique> createSpecClinique(@Valid @RequestBody SpecClinique specClinique) throws URISyntaxException {
        log.debug("REST request to save SpecClinique : {}", specClinique);
        if (specClinique.getId() != null) {
            throw new BadRequestAlertException("A new specClinique cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SpecClinique result = specCliniqueService.save(specClinique);
        return ResponseEntity.created(new URI("/api/spec-cliniques/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /spec-cliniques} : Updates an existing specClinique.
     *
     * @param specClinique the specClinique to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated specClinique,
     * or with status {@code 400 (Bad Request)} if the specClinique is not valid,
     * or with status {@code 500 (Internal Server Error)} if the specClinique couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/spec-cliniques")
    public ResponseEntity<SpecClinique> updateSpecClinique(@Valid @RequestBody SpecClinique specClinique) throws URISyntaxException {
        log.debug("REST request to update SpecClinique : {}", specClinique);
        if (specClinique.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SpecClinique result = specCliniqueService.save(specClinique);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, specClinique.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /spec-cliniques} : get all the specCliniques.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of specCliniques in body.
     */
    @GetMapping("/spec-cliniques")
    public ResponseEntity<List<SpecClinique>> getAllSpecCliniques(Pageable pageable) {
        log.debug("REST request to get a page of SpecCliniques");
        Page<SpecClinique> page = specCliniqueService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /spec-cliniques/:id} : get the "id" specClinique.
     *
     * @param id the id of the specClinique to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the specClinique, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/spec-cliniques/{id}")
    public ResponseEntity<SpecClinique> getSpecClinique(@PathVariable Long id) {
        log.debug("REST request to get SpecClinique : {}", id);
        Optional<SpecClinique> specClinique = specCliniqueService.findOne(id);
        return ResponseUtil.wrapOrNotFound(specClinique);
    }

    /**
     * {@code DELETE  /spec-cliniques/:id} : delete the "id" specClinique.
     *
     * @param id the id of the specClinique to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/spec-cliniques/{id}")
    public ResponseEntity<Void> deleteSpecClinique(@PathVariable Long id) {
        log.debug("REST request to delete SpecClinique : {}", id);
        specCliniqueService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
