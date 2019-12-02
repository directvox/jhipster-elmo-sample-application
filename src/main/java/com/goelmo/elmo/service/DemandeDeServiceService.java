package com.goelmo.elmo.service;

import com.goelmo.elmo.domain.DemandeDeService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link DemandeDeService}.
 */
public interface DemandeDeServiceService {

    /**
     * Save a demandeDeService.
     *
     * @param demandeDeService the entity to save.
     * @return the persisted entity.
     */
    DemandeDeService save(DemandeDeService demandeDeService);

    /**
     * Get all the demandeDeServices.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<DemandeDeService> findAll(Pageable pageable);

    /**
     * Get all the demandeDeServices with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    Page<DemandeDeService> findAllWithEagerRelationships(Pageable pageable);
    
    /**
     * Get the "id" demandeDeService.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DemandeDeService> findOne(Long id);

    /**
     * Delete the "id" demandeDeService.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
