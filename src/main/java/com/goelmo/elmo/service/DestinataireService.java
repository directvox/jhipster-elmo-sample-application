package com.goelmo.elmo.service;

import com.goelmo.elmo.domain.Destinataire;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link Destinataire}.
 */
public interface DestinataireService {

    /**
     * Save a destinataire.
     *
     * @param destinataire the entity to save.
     * @return the persisted entity.
     */
    Destinataire save(Destinataire destinataire);

    /**
     * Get all the destinataires.
     *
     * @return the list of entities.
     */
    List<Destinataire> findAll();

    /**
     * Get all the destinataires with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    Page<Destinataire> findAllWithEagerRelationships(Pageable pageable);
    
    /**
     * Get the "id" destinataire.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Destinataire> findOne(Long id);

    /**
     * Delete the "id" destinataire.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
