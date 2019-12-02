package com.goelmo.elmo.service;

import com.goelmo.elmo.domain.SpecClinique;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link SpecClinique}.
 */
public interface SpecCliniqueService {

    /**
     * Save a specClinique.
     *
     * @param specClinique the entity to save.
     * @return the persisted entity.
     */
    SpecClinique save(SpecClinique specClinique);

    /**
     * Get all the specCliniques.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<SpecClinique> findAll(Pageable pageable);


    /**
     * Get the "id" specClinique.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<SpecClinique> findOne(Long id);

    /**
     * Delete the "id" specClinique.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
